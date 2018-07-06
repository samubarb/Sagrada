package it.polimi.ingsw.controller.Server;


import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.Server.Rmi.rmiStartServer;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.*;
import it.polimi.ingsw.model.toolCards.iTool;

import java.beans.Transient;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static sun.audio.AudioPlayer.player;

/**
 * Main server class that extends .
 * This class contains the main method to launch the server.
 * It represent the game server.
 */
public class ServerLauncher {
    private static final int DICEPLACE = 1;
    private static final int TOOLCARD = 2;
    private static final int DONOTHING = 3;


    public static final int MAXPLAYER = 4;
    public static final int MINPLAYERS = 2;
    public static final long TIMETOWAITINROOM = 10000;
    public static final long START_IMMEDIATELY = 0;
    public static final boolean CONNECTED = true;
    public static final boolean DISCONNECTED = false;
    private Timer mainTimer;
    private int round;
    private final int MAXNUMBEROFROUND = 5;
    private final long TURNTIME = 10000;
    private Game game;
    private Timer turnTimer;
    private boolean canJoin = true;
    private CountDownLatch startLatch;
    private CountDownLatch turnLatch;
    private Object countDownMutex;
    private Player currentPlayer;
    private boolean dicePlaced = false;
    private boolean toolCardUsed = false;
    private boolean nothingToDo = false;


    /**
     * SocketClient port.
     */
    private static final int SOCKET_PORT = 4241;

    /**
     * RMI port.
     */
    private static final int RMI_PORT = new ServerSettings().setFromJSON().getPort();

    /**
     * Mutex object to handle concurrency between users during loginPlayer.
     */
    public static final Object LOGIN_MUTEX = new Object();
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
    /*public ServerLauncher() {
        rmiServer = new rmiStartServer();
        //socketServer = new SocketServerAbstract(this);
        nicknames = new ArrayList<User>();
        offlineNicknames = new ArrayList<User>();
        //configure();
        startLatch = new CountDownLatch(1);
        game = new Game();
        turnLatch = new CountDownLatch(1);
        turnTimer = new Timer();
    }*/

    public ServerLauncher() {
        this.mainTimer = new Timer();
        this.round = 0;
        this.game = new Game();
        this.turnTimer = new Timer();
        this.canJoin = true;
        this.startLatch = new CountDownLatch(1);
        this.turnLatch = new CountDownLatch(1);
        this.countDownMutex = new Object();
        this.dicePlaced = false;
        this.toolCardUsed = false;
        this.nothingToDo = false;
        this.rmiServer = new rmiStartServer();
        this.nicknames = new ArrayList<User>();
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Object getLoginMutex() {
        return LOGIN_MUTEX;
    }

    public void disableUser(User user) {
        synchronized (LOGIN_MUTEX) {
            for(User serverUser : nicknames){
                if(serverUser.getUsername().equals(user.getUsername())){
                    serverUser.setOnline(false);
                    nothingToDo = true;
                }
            }
            notifyDisconnection(user);
        }
    }

    public void notifyDisconnection(User user) {
        synchronized (LOGIN_MUTEX) {
            for (User username : nicknames) {
                try {
                    username.getPlayerInterface().notifyUserDisconnection(user.getUsername());
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
            //reconnection
            if(this.nicknames.size()>0){
                for(User user : nicknames){//reconnection of a user
                    if(!user.isOnline && user.getUsername().equals(username)){
                        user.setOnline(CONNECTED);
                        user.setPlayerInterface(clientPlayer);
                        try {
                            clientPlayer.notifyUserReconnection(username);
                            for (User user2 : serverLauncher.getNicknames()) {
                                if(!user2.getUsername().equals(username))
                                    user2.getPlayerInterface().notifyReconnection(username);
                            }
                        } catch (RemoteException e) {
                            try {
                                clientPlayer.notifyError(e);
                            } catch (RemoteException e1) {
                                e1.printStackTrace();
                            }
                        }
                        try {
                            clientPlayer.setClientGameHide(game);
                        } catch (RemoteException e) {
                            try {
                                clientPlayer.notifyError(e);
                            } catch (RemoteException e1) {
                                e1.printStackTrace();
                            }
                        }
                        return true;
                    }
                }
            }
            if(canJoin){
                //max player reached error check
                if(nicknames.size()>= MAXPLAYER){
                    canJoin = false;
                    try {
                        clientPlayer.notifyMaxPlayerReached();
                    } catch (RemoteException e) {
                        try {
                            clientPlayer.notifyError(e);
                        } catch (RemoteException e1) {
                            e1.printStackTrace();
                        }
                    }
                    return false;
                }
                //Name already in use
                else if (nicknames.size()>0){
                    for(User user : nicknames){
                        if (user.getUsername().equals(username)) {
                            try {
                                clientPlayer.notifyNameInUse();
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            return false;
                        }
                    }

                }
                try {
                    clientPlayer.notifyUserLogged(username);
                    //clientPlayer.onRegister("User logged as: " + username);
                    //clientPlayer.onRegister("welcome");
                    for (User user : serverLauncher.getNicknames()) {
                        user.getPlayerInterface().notifyConnection(username);
                    }
                    //System.out.println("User logged as: " + username);
                } catch (RemoteException e) {
                    return false;
                }
                nicknames.add(new User(username, clientPlayer));
                if (nicknames.size() == MAXPLAYER) {
                    canJoin = false;
                    cancelTimer();
                    startCountDownTimer(START_IMMEDIATELY);
                } else if (nicknames.size() == MINPLAYERS) {
                    startCountDownTimer(TIMETOWAITINROOM);
                }
                return true;

            } else{
                try {
                    clientPlayer.notifyGameStarted();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return false;
            }
            /*if (this.offlineNicknames.size() > 0) {
                //reconnection of already registered player
                for (User user : offlineNicknames) {
                    if (user.getUsername().equals(username)) {

                        this.nicknames.add(new User(username, clientPlayer));
                        this.offlineNicknames.remove(user);
                        try {
                            clientPlayer.onRegister("Welcome again reconnected user: " + username);
                            System.out.println("User logged again: " + username);
                            //rmiServer.executeCheckConnectionThread();
                            //clientPlayer.setClientGame(game);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        try {
                            clientPlayer.setClientGame(game);
                        } catch (RemoteException e) {
                            try {
                                clientPlayer.notifyError(e);
                            } catch (RemoteException e1) {
                                e1.printStackTrace();
                            }
                        }
                        return true;
                    }
                }
            }*/
            //if(canJoin) {
             /*   if ((this.nicknames.size() + this.offlineNicknames.size()) >= MAXPLAYER&&canJoin) {
                    System.out.println("max player reached");
                    canJoin = false;
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
                                //clientPlayer.setClientGame(game);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            try {
                                clientPlayer.setClientGame(game);
                            } catch (RemoteException e) {
                                try {
                                    clientPlayer.notifyError(e);
                                } catch (RemoteException e1) {
                                    e1.printStackTrace();
                                }
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
                    canJoin = false;
                    cancelTimer();
                    startCountDownTimer(START_IMMEDIATELY);
                } else if (nicknames.size() == MINPLAYERS) {
                    startCountDownTimer(TIMETOWAITINROOM);
                }
                return true;*/
            //}
            /*else {
                try {
                    clientPlayer.printaaa("game already started");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return false;
            //}*/
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
        private CountDownLatch turnLatch;
        private CountDownLatch countDownLatch;

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
            //setupPlayerPatternChoice();
            //synchronized (LOGIN_MUTEX) {
                chooseWindowPattern();
            //}
            //while (game.getRound() <= MAXNUMBEROFROUND) {
            do {
                currentPlayer = game.getCurrentPlayer();
                updateGameSessionHide();
                if(game.getRound()<=MAXNUMBEROFROUND) {
                    String currentPlayerName = currentPlayer.getName();
                    for (User user : serverLauncher.getNicknames()) {
                        if(user.isOnline()) {
                            if (user.getUsername().equals(currentPlayer.getName())) {
                                try {
                                    user.getPlayerInterface().notifyTurn(user.getUsername());
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                                synchronized (TURN_MUTEX) {
                                    startTurn(user.getPlayerInterface(), currentPlayer);
                                    //updateGameSession();
                                }
                            }
                        }
                    }
                }
            } while (game.getRound() <= MAXNUMBEROFROUND);
            calculateFinalScore(game);
            notifyWinnerAndLoosers(game);
            //selectWinner();
            //notifyWinner();
        }

        /*
        public void setupPlayerPatternChoice(){
            for (User user : nicknames){
                user.getPlayerInterface().choosePattern();
            }
        }*/
        public void chooseWindowPattern() {
            for (User user : getNicknames()) {
                WindowPattern[] windowPattern = null;
                int index = 0;
                try {
                    windowPattern = game.getWindowPatternForAPlayer();
                    index = user.getPlayerInterface().chooseWindowPattern(windowPattern);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                for (Player player : game.getPlayers()) {
                    if (player.getName().equals(user.getUsername())) {
                        player.setWindowPattern(windowPattern[index]);
                    }
                }
            }
        }

        public void startTurn(PlayerInterface playerInterface, Player player) {
            //startTimer(TURNTIME, playerInterface);
            nothingToDo = false;
            toolCardUsed = false;
            dicePlaced = false;
            while (!(nothingToDo || (toolCardUsed && dicePlaced))) {
                try {
                    playerInterface.setMyTurn(true);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                //startTurnCountDown((int)TURNTIME);
                //start turn timer;
                //startTurnCountDownTimer(TURNTIME, playerInterface);
                //startTimer(TURNTIME, playerInterface);
                updateGameSession();
                getMoves(playerInterface, player);
                //updateGameSessionHide(); //this update update the change of the inner turn current dice

                //getDiceAndPlace(playerInterface, player);
                //dice vado nel client e faccio partire turn che chiede mossa:o dice o cartautensile o nulla
        /*
        if cartautensile usa cartautensile chiede cosa vuole la carta utensile se serve poi richiede
        if posiziona dado posiziona dado poi richiede IL SOLO DA IMPLEMENTARE dopo non richiede ma termina il turno
        if nulla end turn
         */
                endturn(playerInterface);

            }
            //teo}

        }

        public void getMoves(PlayerInterface playerInterface, Player player) {
            //chiedo la mossa 0, 1 ,2, 3
            int moves = 0;
            try {
                moves = playerInterface.getMoves();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            if (moves == DICEPLACE) {
                if (!dicePlaced) {
                    getDiceAndPlace(playerInterface, player);
                }
            } else if (moves == TOOLCARD) {
                if (!toolCardUsed) {
                    int toolCard = 0;
                    try {
                        toolCard = playerInterface.getToolcard();
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                    useToolcard(toolCard, playerInterface, player);
                }
            } else if (moves == DONOTHING) {
                nothingToDo = true;
            }

        }

        public void useToolcard(int i, PlayerInterface playerInterface, Player player) {
            switch (i) {
                case 0:
                    useGrozingPliers(i, playerInterface, player);
                    break;
                case 1:
                    useEnglimiseBrush(i, playerInterface, player);
                    break;
                case 2:
                    useCopperFoilBurnisher(i, playerInterface, player);
                    break;
                case 3:
                    useLathekin(i, playerInterface, player);
                    break;
                case 4:
                    useLensCutter(i, playerInterface, player);
                    break;
                case 5:
                    useFluxBrush(i, playerInterface, player);
                    break;
                case 6:
                    useGlazingHammer(i, playerInterface, player);
                    break;
                case 7:
                    useRunningPliers(i, playerInterface, player);
                    break;
                case 8:
                    useCorkBackedStraightedge(i, playerInterface, player);
                    break;
                case 9:
                    useGrindingStone(i, playerInterface, player);
                    break;
                case 10:
                    useFluxRemover(i, playerInterface, player);
                    break;
                case 11:
                    useTapWheel(i, playerInterface, player);
                    break;
            }
        }

        public void useGrozingPliers(int i, PlayerInterface playerInterface, Player player) {

            try {
                player.checkFavorTokenPlayer(game.getToolCards()[i]);
            } catch (FavorTokenException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            int dice = 0;
            try {
                dice = playerInterface.getDiceFromReserve();
            } catch (RemoteException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
            try {

                player.setChosenNut(game.getDiceFromCurrentDice(dice));
                Action action = playerInterface.getTypeOfAction();
                game.getToolCards()[i].useTool(player, action);
                game.restoreDice(player, dice);
                toolCardUsed = true;
            } catch (NutChosenWrongException e) {
                e.printStackTrace();
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                //problem with nutchosen cant change 1 to 6 or 6 to 1
                restoreFavorToken(player, i);
                game.restoreDice(player, dice);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                restoreFavorToken(player, i);
                return;
            }
        }

        public void useEnglimiseBrush(int i, PlayerInterface playerInterface, Player player) {
            oneDiceMoveToolUsage(i, playerInterface, player);
        }


        public void useCopperFoilBurnisher(int i, PlayerInterface playerInterface, Player player) {
            oneDiceMoveToolUsage(i, playerInterface, player);
        }


        public void useLathekin(int i, PlayerInterface playerInterface, Player player) {
            try {
                player.checkFavorTokenPlayer(game.getToolCards()[i]);
            } catch (FavorTokenException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            Coordinates initialCoordinates1 = null;
            Coordinates initialCoordinates2 = null;
            Coordinates finalCoordinates1 = null;
            Coordinates finalCoordinates2 = null;
            try {
                initialCoordinates1 = playerInterface.getDiceToBeMoved(1);
                finalCoordinates1 = playerInterface.getDiceDestination(1);
                initialCoordinates2 = playerInterface.getDiceToBeMoved(2);
                finalCoordinates2 = playerInterface.getDiceDestination(2);
                twoDiceToolUsage(i, playerInterface, player, initialCoordinates1, finalCoordinates1, initialCoordinates2, finalCoordinates2);
            } catch (RemoteException e) {
                e.printStackTrace();
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                restoreFavorToken(player, i);
                //toolCardUsed = false;
                return;
            }


            return;

        }

        public void useLensCutter(int i, PlayerInterface playerInterface, Player player) {
            try {
                player.checkFavorTokenPlayer(game.getToolCards()[i]);
            } catch (FavorTokenException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            int dice = 0;
            try {
                dice = playerInterface.getDiceFromReserve();
            } catch (RemoteException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
            Coordinates roundDice = null;
            try {

                player.setChosenNut((game.getDiceFromCurrentDice(dice)));
                roundDice = playerInterface.getRoundDiceToBeSwapped();
                game.getToolCards()[i].useTool(player, roundDice);
                game.restoreDice(player, dice);
                toolCardUsed = true;
            } catch (RemoteException e) {
                e.printStackTrace();
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                restoreFavorToken(player, i);
                //toolCardUsed = false;
                return;
            } catch (IllegalArgumentException e){
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                restoreFavorToken(player, i);
                game.restoreDice(player, dice);
                return;
            }

        }

        public void useFluxBrush(int i, PlayerInterface playerInterface, Player player) {
            try {
                player.checkFavorTokenPlayer(game.getToolCards()[i]);
            } catch (FavorTokenException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                return;
            }

            int dice = 0;
            try {
                dice = playerInterface.getDiceFromReserve();
            } catch (RemoteException e) {
                e.printStackTrace();
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                restoreFavorToken(player, i);
                //toolCardUsed = false;
                return;
            }
            player.setChosenNut(game.getDiceFromCurrentDice(dice));
            game.getToolCards()[i].useTool(player);
            game.restoreDice(player, dice);
            toolCardUsed = true;
        }

        public void useGlazingHammer(int i, PlayerInterface playerInterface, Player player) {
            try {
                player.checkFavorTokenPlayer(game.getToolCards()[i]);
            } catch (FavorTokenException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            if (!dicePlaced&&game.isSecondTurn()) {
                game.getToolCards()[i].useTool(player);
                toolCardUsed = true;
            } else {
                restoreFavorToken(player, i);
                try {
                    playerInterface.notifyError(new IllegalArgumentException());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                return;
            }
        }

        public void useRunningPliers(int i, PlayerInterface playerInterface, Player player) {
            try {
                player.checkFavorTokenPlayer(game.getToolCards()[i]);
            } catch (FavorTokenException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            game.getToolCards()[i].useTool(player);
            toolCardUsed = true;
        }

        public void useCorkBackedStraightedge(int i, PlayerInterface playerInterface, Player player) {
            try {
                player.checkFavorTokenPlayer(game.getToolCards()[i]);
            } catch (FavorTokenException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            int dice = 0;
            if(dicePlaced == true)
                return;
            try {
                dice = playerInterface.getDiceFromReserve();
                Coordinates finalCoordinates = playerInterface.getDiceDestination();
                player.setChosenNut(game.getDiceFromCurrentDice(dice));
                game.getToolCards()[i].useTool(player, finalCoordinates);
                toolCardUsed = true;
                dicePlaced = true;
            } catch (RemoteException e) {
                e.printStackTrace();
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                restoreFavorToken(player, i);
                game.restoreDice(player, dice);
                return;
            } catch (IllegalArgumentException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                restoreFavorToken(player, i);
                game.restoreDice(player, dice);
                return;
            }
        }

        public void useGrindingStone(int i, PlayerInterface playerInterface, Player player) {
            try {
                player.checkFavorTokenPlayer(game.getToolCards()[i]);
            } catch (FavorTokenException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            int dice = 0;
            try {
                dice = playerInterface.getDiceFromReserve();
                player.setChosenNut(game.getDiceFromCurrentDice(dice));
                game.getToolCards()[i].useTool(player);
                game.restoreDice(player, dice);
                toolCardUsed = true;
            } catch (RemoteException e) {
                e.printStackTrace();
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                restoreFavorToken(player, i);
                return;

            }
        }

        public void useFluxRemover(int i, PlayerInterface playerInterface, Player player) {
            try {
                player.checkFavorTokenPlayer(game.getToolCards()[i]);
            } catch (FavorTokenException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            int dice = 0;
            try {
                dice = playerInterface.getDiceFromReserve();
                player.setChosenNut(game.getDiceFromCurrentDice(dice));
                game.getToolCards()[i].useTool(player);
                int value = playerInterface.getDiceValue(player.getChosenNut());
                player.getChosenNut().setValue(value);
                game.restoreDice(player, dice);
                toolCardUsed = true;
            } catch (RemoteException e) {
                e.printStackTrace();
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                restoreFavorToken(player, i);
                return;
            }
        }

        public void useTapWheel(int i, PlayerInterface playerInterface, Player player) {
            try {
                player.checkFavorTokenPlayer(game.getToolCards()[i]);
            } catch (FavorTokenException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            int dice;
            Coordinates roundDice = null;
            try {

                Coordinates initialCoordinates1 = null;
                Coordinates initialCoordinates2 = null;
                Coordinates finalCoordinates1 = null;
                Coordinates finalCoordinates2 = null;

                initialCoordinates1 = playerInterface.getDiceToBeMoved(1);
                finalCoordinates1 = playerInterface.getDiceDestination(1);
                initialCoordinates2 = playerInterface.getDiceToBeMoved(2);
                finalCoordinates2 = playerInterface.getDiceDestination(2);

                twoDiceToolUsage(i, playerInterface, player, initialCoordinates1, finalCoordinates1, initialCoordinates2, finalCoordinates2);
            } catch (RemoteException e) {
                e.printStackTrace();
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                restoreFavorToken(player, i);
                return;
            }
        }
        /*
        private void startTurnCountDownTimer(long waitingTime, PlayerInterface playerInterface) {
            turnTimer = new Timer();
            turnTimer.schedule(new TurnCountDownTask(), waitingTime);
        }

        private void cancelTurnTimer(PlayerInterface playerInterface) {
                if (turnTimer != null) {
                    turnTimer.cancel();
                    turnTimer.purge();
                }

        }*/

        public void getDiceAndPlace(PlayerInterface playerInterface, Player player) {
            int position = 0;
            try {
                position = playerInterface.getDiceToBePlaced();
            } catch (RemoteException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            player.setChosenNut(game.getDiceFromCurrentDice(position));
            Coordinates coordinates = null;
            try {
                coordinates = playerInterface.getDiceFinalPosition();

            } catch (RemoteException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            try {
                player.positionDice(player.getChosenNut(), coordinates);
            } catch (AdjacentDiceException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                game.restoreDice(player, position);
                dicePlaced = false;
                return;
            } catch (WindowPatternColorException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                game.restoreDice(player, position);
                dicePlaced = false;
                return;
            } catch (WindowPatternValueException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                game.restoreDice(player, position);
                dicePlaced = false;
                return;
            } catch (FrameValueAndColorException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                game.restoreDice(player, position);
                dicePlaced = false;
                return;
            } catch (BusyPositionException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                game.restoreDice(player, position);
                dicePlaced = false;
                return;
            }
            dicePlaced = true;
            return;
        }

        public void endturn(PlayerInterface playerInterface) {
            //stopTimer();
            try {
                playerInterface.setMyTurn(false);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        public void calculateFinalScore(Game game) {
            for (Player player : game.getPlayers()) {
                getFinalPointsFromPublicObjectives(game, player);
                getFinalPointsFromPrivateObjective(game, player);
                getFinalPointsFromFrame(game, player);
                getFinalPointsFromFavorToken(game, player);
                updateGameSession();
            }
        }

        public void getFinalPointsFromPublicObjectives(Game game, Player player) {
            for (PublicObjective publicObjective : game.getPublicObjectives()) {
                int finalScore = publicObjective.calculateScore(player);
                player.addFinalPoints(finalScore);
            }
        }

        public void getFinalPointsFromPrivateObjective(Game game, Player player) {
            int privatePoint = player.getPrivateObjective().calculateScore(player);
            player.addFinalPoints(privatePoint);
        }

        public void getFinalPointsFromFrame(Game game, Player player) {
            int framePoints = player.playerFramePoints();
            player.addFinalPoints(framePoints);
        }

        public void getFinalPointsFromFavorToken(Game game, Player player) {
            int favorPoints = player.getFavorTokens();
            player.addFinalPoints(favorPoints);
        }

        public void notifyWinnerAndLoosers(Game game) {
            String winner = null;
            //calculate classifica
            for (User user : getNicknames()) {
                try {
                    user.getPlayerInterface().notifyScoreBoard();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            winner = calculateWinner(game);
            for (User user: getNicknames()){
                if(user.getUsername().equals(winner)) {
                    try {
                        user.getPlayerInterface().notifyWinner();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        try {
                            user.getPlayerInterface().notifyError(e);
                        } catch (RemoteException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                else {
                    try {
                        user.getPlayerInterface().notifyLoosers();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        try {
                            user.getPlayerInterface().notifyError(e);
                        } catch (RemoteException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }


        }
        public String calculateWinner(Game game){
            String winner = null;
            int winnerPoints = -100;
            for (Player player: game.getPlayers()){
                if(player.getFinalPoints()>winnerPoints){
                    winner = player.getName();
                    winnerPoints = player.getFinalPoints();
                }
            }
            return winner;
        }

        public void twoDiceToolUsage(int i, PlayerInterface playerInterface, Player player, Coordinates initialCoordinates1, Coordinates finalCoordinates1, Coordinates initialCoordinates2, Coordinates finalCoordinates2) {
            try {
                game.getToolCards()[i].useTool(player, initialCoordinates1, finalCoordinates1, initialCoordinates2, finalCoordinates2);
                toolCardUsed = true;
            } catch (WindowPatternColorException e) {
                try {
                    playerInterface.notifyError(e);

                } catch (RemoteException e1) {
                    e1.printStackTrace();
                    try {
                        playerInterface.notifyError(e1);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }

                restoreFavorToken(player, i);
                return;
            } catch (WindowPatternValueException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                    try {
                        playerInterface.notifyError(e1);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
                restoreFavorToken(player, i);
                return;
            } catch (FrameValueAndColorException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    try {
                        playerInterface.notifyError(e1);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                    e1.printStackTrace();
                }
                restoreFavorToken(player, i);
                return;
            } catch (BusyPositionException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                    try {
                        playerInterface.notifyError(e1);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
                restoreFavorToken(player, i);
                return;
            } catch (AdjacentDiceException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                    try {
                        playerInterface.notifyError(e1);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
                restoreFavorToken(player, i);
                return;
            } catch (IllegalArgumentException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                restoreFavorToken(player, i);
                return;
            }
        }

        public void oneDiceMoveToolUsage(int i, PlayerInterface playerInterface, Player player) {
            try {
                player.checkFavorTokenPlayer(game.getToolCards()[i]);
                Coordinates initialCoordinates = playerInterface.getDiceToBeMoved();
                Coordinates finalCoordinates = playerInterface.getDiceDestination();
                game.getToolCards()[i].useTool(player, initialCoordinates, finalCoordinates);
                toolCardUsed = true;
            } catch (IllegalArgumentException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                restoreFavorToken(player, i);
                return;
            } catch (FavorTokenException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                return;
            } catch (RemoteException e) {
                try {
                    playerInterface.notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                restoreFavorToken(player, i);
                return;
            }

        }

        public void restoreFavorToken(Player player, int i) {
            player.restoreFavorTokenPlayer(game.getToolCards()[i]);
            toolCardUsed = false;
        }

        private void closeRoomSafely() {
            synchronized (LOGIN_MUTEX) {
                canJoin = false;
            }
        }

        private void createGameSession() {
            for (User user : nicknames) {
                game.setAddPlayer(new Player(user.getUsername()));
            }
            new GameConfigurator(game);

        }

        private void updateGameSession() {
            for (User user : nicknames) {
                try {
                    user.getPlayerInterface().setClientGame(game);
                    //user.getPlayerInterface().printPlayersFrame();
                } catch (RemoteException e) {

                }
            }
        }

        private void updateGameSessionHide(){
            for (User user : nicknames) {
                try {
                    user.getPlayerInterface().setClientGameHide(game);
                    //user.getPlayerInterface().printPlayersFrame();
                } catch (RemoteException e) {

                }
            }
        }


        private void dispatchGameSession() {
            //Server.debug("[ROOM] Game ready, dispatching base game to all players");
            for (User user : getNicknames()) {
                try {
                    user.getPlayerInterface().setClientGameHide(game);

                } catch (RemoteException e) {
                    //Server.error("problem with set player game", e);
                }
            }
        }

        private void notifyAllTurn(String username) {
            for (User user : nicknames) {
                try {
                    user.getPlayerInterface().notifyTurn(username);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        void startTimer(long moveWaitingTime, PlayerInterface playerInterface) {
            int time = (int) (moveWaitingTime / 1000);
            this.turnLatch = new CountDownLatch(time);
            turnTimer.scheduleAtFixedRate(new TurnCountDownTask(), 1000, 1000);
            try {
                turnLatch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        void stopTimer() {
            resetTimer();
            while (countDownLatch.getCount() > 0)
                countDownLatch.countDown();
        }

        private void resetTimer() {
            turnTimer.cancel();
            turnTimer.purge();
        }
    }


    private class TurnCountDownTask extends TimerTask {

        @Override
        public void run() {
            synchronized (countDownMutex) {
                if (turnLatch.getCount() > 0) {
                    if (turnLatch.getCount() == 1) {
                        turnTimer.cancel();
                        turnTimer.purge();
                    }
                    turnLatch.countDown();
                }
            }

        }
    }
}



