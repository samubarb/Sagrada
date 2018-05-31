package it.polimi.ingsw.controller.Server;


import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.Server.Rmi.rmiStartServer;
import it.polimi.ingsw.model.Coordinates;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameConfigurator;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.exceptions.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Main server class that extends .
 * This class contains the main method to launch the server.
 * It represent the game server.
 */
public class ServerLauncher {
    public static final int MAXPLAYER = 4;
    public static final int MINPLAYERS = 2;
    public static final long TIMETOWAITINROOM = 5000;
    public static final long START_IMMEDIATELY = 0;
    private Timer mainTimer;
    private int round;
    private static final int MAXNUMBEROFROUND = 2;
    private static final long TURNTIME = 10000;
    private Game game;
    private Timer turnTimer;
    private boolean canJoin;
    private CountDownLatch startLatch;
    private CountDownLatch turnLatch;
    private static Player currentPlayer;


    /**
     * SocketClient port.
     */
    private static final int SOCKET_PORT = 4241;

    /**
     * RMI port.
     */
    private static final int RMI_PORT = 4242;

    /**
     * Mutex object to handle concurrency between users during loginPlayer.
     */
    private static final Object LOGIN_MUTEX = new Object();
    private static final Object TURN_MUTEX = new Object();


    /**
     * RMI server.
     */
    private rmiStartServer rmiServer;

    /**
     * SocketClient server.
     */
    //private SocketServerAbstract socketServer;

    private static ServerLauncher serverLauncher;
    /**
     * Map of all logged and active players.
     */
    private ArrayList<User> nicknames;

    private ArrayList<User> offlineNicknames;

    /**
     * Class constructor.
     */
    public ServerLauncher() {
        rmiServer = new rmiStartServer();
        //socketServer = new SocketServerAbstract(this);
        nicknames = new ArrayList<User>();
        offlineNicknames = new ArrayList<User>();
        //configure();
        startLatch = new CountDownLatch(1);
        game = new Game();
        turnLatch = new CountDownLatch(1);
        turnTimer = new Timer();
    }


    public static void main(String[] args) {
        try {
            serverLauncher = new ServerLauncher();
            serverLauncher.startSocketRMIServer(SOCKET_PORT, RMI_PORT);

            //System.out.println("Socket server ready.");
            System.out.println("RMI server ready.");
        } catch (Exception e) {
            System.out.println("Server.java" + "Error while starting the server." + e);
        }
    }

    /**
     * Method to initialize and start socket server and RMI server.
     *
     * @param socketPort of socket server.
     * @param rmiPort    of RMI server.
     * @throws Exception if errors occur during initialization.
     */
    private void startSocketRMIServer(int socketPort, int rmiPort) throws Exception {
        //socketServer.startServer(socketPort);
        rmiServer.setServerLauncher(serverLauncher);
        rmiServer.startServer(rmiPort);
    }

    public ArrayList<User> getNicknames() {
        return nicknames;
    }

    public ArrayList<User> getOfflineNicknames() {
        return offlineNicknames;
    }

    public static Object getLoginMutex() {
        return LOGIN_MUTEX;
    }

    public void disableUser(User user) {
        synchronized (LOGIN_MUTEX) {
            offlineNicknames.add(user);
            nicknames.remove(user);
            notifyDisconnection(user);
        }
    }

    public void notifyDisconnection(User user) {
        synchronized (LOGIN_MUTEX) {
            for (User username : nicknames) {
                try {
                    username.getPlayerInterface().notifyDisconnection(user);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void notifyReconnection(User user) {
        synchronized (LOGIN_MUTEX) {
            for (User username : nicknames) {
                try {
                    username.getPlayerInterface().notifyDisconnection(user);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean registerUser(PlayerInterface clientPlayer, String username) {

        synchronized (LOGIN_MUTEX) {
            if ((this.nicknames.size() + this.offlineNicknames.size()) >= MAXPLAYER) {
                System.out.println("max player reached");
                try {
                    clientPlayer.onRegister("Max player reached");
                    System.out.println("Max player reached");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return false;
            }
            if (this.offlineNicknames.size() > 0) {
                //reconnection of already registered player
                for (User user : offlineNicknames) {
                    if (user.getUsername().equals(username)) {

                        this.nicknames.add(new User(username, clientPlayer));
                        this.offlineNicknames.remove(user);
                        try {
                            clientPlayer.onRegister("Welcome again reconnected user: " + username);
                            System.out.println("User logged again: " + username);
                            //rmiServer.executeCheckConnectionThread();

                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        //ridò il game al giocatore
                        return true;
                    }
                }
            } else if (nicknames.size() > 0) {
                for (User user : nicknames) {
                    if (user.getUsername().equals(username)) {
                        try {
                            clientPlayer.printaaa("Name already in use");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                }
            }
            try {
                clientPlayer.onRegister("User logged as: " + username);
                clientPlayer.onRegister("welcome");
                for (User user : serverLauncher.getNicknames()) {
                    user.getPlayerInterface().notifyConnection(username);
                }
                System.out.println("User logged as: " + username);
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
            nicknames.add(new User(username, clientPlayer));
            if (nicknames.size() == MAXPLAYER) {
                //canJoin = false;
                cancelTimer();
                startCountDownTimer(START_IMMEDIATELY);
            }
            else if (nicknames.size() == 2) {
                startCountDownTimer(TIMETOWAITINROOM);
            }
            return true;
        }
    }

    public boolean contains(String username, ArrayList<User> userArrayList) {
        for (User user : userArrayList) {
            if (user.getUsername().equals(username))
                return true;
            else
                return false;
        }

        return false;
    }

    public void getClientInterface(String username) {

    }

    private void startCountDownTimer(long waitingTime) {
        mainTimer = new Timer();
        mainTimer.schedule(new GameHandler(), waitingTime);
    }

    private void cancelTimer() {
        if (mainTimer != null) {
            mainTimer.cancel();
            mainTimer.purge();
        }
    }

    private class GameHandler extends TimerTask {

        /**
         * This method is executed when the time is expired. At first, it closes the room.
         * Then start the game.
         */
        @Override
        public void run() {
            System.out.println("avviando il gioco");
            closeRoomSafely();
            System.out.println("login state closed");
            createGameSession();
            System.out.println("creata la game session");
            dispatchGameSession();
            System.out.println("ldistribuita la game session");
            //configureGame();
            while (game.getRound() <= MAXNUMBEROFROUND) {
                currentPlayer = game.getCurrentPlayer();
                String currentPlayerName = currentPlayer.getName();
                for (User user : serverLauncher.getNicknames()) {
                    if (user.getUsername().equals(currentPlayer.getName())) {
                        try {
                            user.getPlayerInterface().notifyTurn(user.getUsername());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        updateGameSession();
                        startTurn(user.getPlayerInterface(), currentPlayer);
                        updateGameSession();
                    }
                }
            }
            //selectWinner();
            //notifyWinner();
        }

        public void startTurn(PlayerInterface playerInterface, Player player) {
            synchronized (TURN_MUTEX) {
                try {
                    playerInterface.setMyTurn(true);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                //startTurnCountDown((int)TURNTIME);
                //start turn timer;
                startTurnCountDownTimer(TURNTIME);
                getDiceAndPlace(playerInterface, player);
                //dice vado nel client e faccio partire turn che chiede mossa:o dice o cartautensile o nulla
        /*
        if cartautensile usa cartautensile chiede cosa vuole la carta utensile se serve poi richiede
        if posiziona dado posiziona dado poi richiede IL SOLO DA IMPLEMENTARE dopo non richiede ma termina il turno
        if nulla end turn
         */
                endturn();
            }

        }
        private void startTurnCountDownTimer(long waitingTime) {
            turnTimer = new Timer();
            turnTimer.schedule(new TurnCountDownTask(), waitingTime);
        }

        private void cancelTurnTimer() {
            synchronized (TURN_MUTEX) {
                if (turnTimer != null) {
                    turnTimer.cancel();
                    turnTimer.purge();
                }
            }
        }

        public void getDiceAndPlace(PlayerInterface playerInterface, Player player) {
            int position = 0;
            try {
                position = playerInterface.getDiceToBePlaced();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            player.setChosenNut(game.getDiceFromCurrentDice(position));
            Coordinates coordinates = null;
            try {
                coordinates = playerInterface.getDiceFinalPosition();

            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                player.positionDice(player.getChosenNut(), coordinates);
            } catch (WindowPatternColorException e) {
                try {
                    playerInterface.printaaa("Non è rispettato il colore del dado");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            } catch (WindowPatternValueException e) {
                try {
                    playerInterface.printaaa("Non è rispettato il valore del dado");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            } catch (FrameValueAndColorException e) {
                try {
                    playerInterface.printaaa("Non è rispettato il controllo dei dadi ortogonali adiacenti");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            } catch (BusyPositionException e) {
                try {
                    playerInterface.printaaa("c'è già un dado");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            } catch (AdjacentDiceException e) {
                try {
                    playerInterface.printaaa("Non ci sono dadi adiacenti alla posizione indicata");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
            return;
        }

        public void endturn() {
            cancelTurnTimer();

        }


        private void closeRoomSafely() {
            synchronized (LOGIN_MUTEX) {
                canJoin = false;
            }
        }

        private void createGameSession(){
            for (User user: nicknames){
                game.setAddPlayer(new Player(user.getUsername()));
            }
            new GameConfigurator(game);

        }
        private void updateGameSession(){
            for(User user : nicknames){
                try{
                    user.getPlayerInterface().setClientGame(game);
                    user.getPlayerInterface().printPlayersFrame();
                }
                catch(RemoteException e){

                }
            }
        }

        private void dispatchGameSession() {
            //Server.debug("[ROOM] Game ready, dispatching base game to all players");
            for (User user : getNicknames()) {
                try {
                    user.getPlayerInterface().setClientGame(game);

                } catch (RemoteException e) {
                    //Server.error("problem with set player game", e);
                }
            }
        }
        /*
        public void startTurnCountDown(int MAXTIMETURN) {
            turnLatch = new CountDownLatch(MAXTIMETURN);
            turnTimer.scheduleAtFixedRate(new TurnCountDownTask(), 1000, 1000);
            try {
                turnLatch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                //Server.error(e);
            }
        }
        private void cancelTurnTimer() {
            synchronized (TURN_MUTEX) {
                if (turnTimer != null) {
                    turnTimer.cancel();
                    turnTimer.purge();
                }
            }
        }*/

        private void notifyAllTurn(String username){
            for(User user : nicknames){
                try {
                    user.getPlayerInterface().notifyTurn(username);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private class TurnCountDownTask extends TimerTask {
        /**
         * Code executed by the timer every seconds on a secondary thread.
         */
        @Override
        public void run() {
            synchronized (TURN_MUTEX) {
                //endturn();
                //if (turnLatch.getCount() > 0) {
                    //onUpdateCountdown((int) turnLatch.getCount() - 1);
                    //if (turnLatch.getCount() == 1) {
                        //turnTimer.cancel();
                        //turnTimer.purge();
                    //}
                    //turnLatch.countDown();
                }
            }
        }

        public void onUpdateCountdown(int remainingTime){
            return;
        }

        /*
         * This method verify every second if the player is connected If the
         * player disconnect it will be setted isOnline as false and then
         * notified to all players that the player went offline It takes as
         * input the player to monitor and the remaining time of the timer
         * It's a void method
         *//*
        public void onUpdateCountdown(String currentPlayer, int remainingTime) {
            try {
                for (Player player : players) {
                    if (!(player.getName().equals(currentPlayer))) {
                    } else {
                        player.getPlayerInterface().onUpdateTurnCountdown(remainingTime);
                    }
                }
            } catch (RemoteException e) {
                Server.error("player disconnected", e);
                for (Player player : players) {
                    if (!(player.getName().equals(currentPlayer))) {
                    } else {
                        player.setOnline(false);
                    }
                }

            }*/
    }



