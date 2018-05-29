package it.polimi.ingsw.controller.Server;


import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.Server.Rmi.rmiStartServer;
import it.polimi.ingsw.model.Coordinates;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameConfigurator;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.exceptions.BusyPositionException;
import it.polimi.ingsw.model.exceptions.FrameValueAndColorException;
import it.polimi.ingsw.model.exceptions.WindowPatternColorException;
import it.polimi.ingsw.model.exceptions.WindowPatternValueException;

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
    public static final long TIMETOWAITINROOM = 60;
    public static final long START_IMMEDIATELY = 0;
    private Timer mainTimer;
    private int round;
    private static final int MAXNUMBEROFROUND = 10;
    private static final long TURNTIME = 30;
    private Game game;
    private Timer turnTimer;
    private boolean canJoin;
    private CountDownLatch startLatch;
    private CountDownLatch turnLatch;


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
                if (nicknames.size() == 2) {
                    startCountDownTimer(TIMETOWAITINROOM);
                }
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
            } else if (nicknames.size() == MINPLAYERS) {
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
            closeRoomSafely();
            //createGameSession();
            dispatchGameSession();
            //configureGame();
            while (game.getRound() <= MAXNUMBEROFROUND) {
                Player currentPlayer = game.getCurrentPlayer();
                String currentPlayerName = currentPlayer.getName();
                for (User user : serverLauncher.getNicknames()) {
                    if (user.getUsername().equals(currentPlayer.getName())) {

                        startTurn(user.getPlayerInterface(), currentPlayer);
                    }
                }
            }
            //selectWinner();
            //notifyWinner();
        }

        public void startTurn(PlayerInterface playerInterface, Player player) {
            try {
                playerInterface.setMyTurn(true);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            startTurnCountDown((int)TURNTIME);
            //start turn timer;
            getDiceAndPlace(playerInterface, player);
            //dice vado nel client e faccio partire turn che chiede mossa:o dice o cartautensile o nulla
        /*
        if cartautensile usa cartautensile chiede cosa vuole la carta utensile se serve poi richiede
        if posiziona dado posiziona dado poi richiede IL SOLO DA IMPLEMENTARE dopo non richiede ma termina il turno
        if nulla end turn
         */
            endturn();

        }

        public void getDiceAndPlace(PlayerInterface playerInterface, Player player) {
            int position = 0;
            try {
                position = playerInterface.getDiceToBePlaced();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            player.setChosenNut(game.getDiceFromCurrentDice(position));
            /*Coordinates coordinates = playerInterface.getDiceFinalPosition();
            try {
                player.positionDice(player.getChosenNut(), coordinates);
            } catch (WindowPatternColorException e) {
                e.printStackTrace();
            } catch (WindowPatternValueException e) {
                e.printStackTrace();
            } catch (FrameValueAndColorException e) {
                e.printStackTrace();
            } catch (BusyPositionException e) {
                e.printStackTrace();
            }*/
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
            if (turnTimer != null) {
                turnTimer.cancel();
                turnTimer.purge();
            }
        }
    }
    private class TurnCountDownTask extends TimerTask {
        /**
         * Code executed by the timer every seconds on a secondary thread.
         */
        @Override
        public void run() {
            //synchronized (/*TURN_MUTEX*/) {
                if (turnLatch.getCount() > 0) {
                    onUpdateCountdown((int) turnLatch.getCount() - 1);
                    if (turnLatch.getCount() == 1) {
                        turnTimer.cancel();
                        turnTimer.purge();
                    }
                    turnLatch.countDown();
                }
           // }
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


}
