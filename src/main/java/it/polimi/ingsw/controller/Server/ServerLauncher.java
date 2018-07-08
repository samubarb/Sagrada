package it.polimi.ingsw.controller.Server;


import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.Server.Rmi.rmiStartServer;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

/**
 * Main server class that extends .
 * This class contains the main method to launch the server.
 * It represent the game server.
 */
public class ServerLauncher {
    /**
     * Define representing the type of move, to place a dice
     */
    private static final int DICEPLACE = 1;
    /**
     * Define representing the type of moves, to use a toolcard
     */
    private static final int TOOLCARD = 2;
    /**
     * Define representing the tipe of moves, finish turn
     */
    private static final int DONOTHING = 3;
    /**
     * Maximum number of player
     */
    private static final int MAXPLAYER = 4;
    /**
     * Minimum number of player to start a game
     */
    private static final int MINPLAYERS = 2;
    /**
     * Time to wait in room waiting other player after reaching MINPLAYERS
     */
    private static final long TIMETOWAITINROOM = 5000;
    /**
     * Time to wait in room afer reaching MAXPLAYER, it start immediately the game
     */
    private static final long START_IMMEDIATELY = 0;
    /**
     * Represents the status of a player, in this case connected
     */
    private static final boolean CONNECTED = true;
    /**
     * Represents the status of a player, in this case disconnected
     */
    public static final boolean DISCONNECTED = false;
    /**
     * It is the timer that start in the waiting room, during registration of players
     */
    private Timer mainTimer;
    /**
     * Represents the round of the game
     */
    private int round;
    /**
     * Represents the number of round of the game
     */
    private static final int MAXNUMBEROFROUND = 510;
    /**
     * Represents the time that a player has to make his moves
     */
    private static final long TURNTIME = 10000;
    /**
     * it's turn time bit converted as int
     */
    private static  final int INTTURNTIME = (int)TURNTIME/1000;
    /**
     * Represent the value of empty array list
     */
    private static final int EMPTYARRAYLIST = 0;
    /**
     * It's the current game
     */
    private Game game;
    /**
     * It's the timer that start during every turn
     */
    private Timer turnTimer;
    /**
     * timer thet start for handling the thread thad handle the turn timer
     */
    private Timer turnHandlerTimer;
    /**
     * represent the flag that permits players to login, true before game staer, false after that
     */
    private boolean canJoin = true;
    /**
     * Latch tht is used to make a countdown for turn concurrency
     */
    private static CountDownLatch turnLatch;
    /**
     * Represent the player that is actually playing
     */
    private Player currentPlayer;
    /**
     * It's the interface of the current player
     */
    private static PlayerInterface currentPlayerInterface;
    /**
     * Represents if the current player has already placed a dice or not
     */
    private boolean dicePlaced = false;
    /**
     * Represents if the current player has already used a tool card or not
     */
    private boolean toolCardUsed = false;
    /**
     * Represents if the player wants to pass the turn or not
     */
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
    /**
     * Mutex object to handle concurrency between users during turn
     */
    private static final Object TURN_MUTEX = new Object();


    /**
     * RMI server.
     */
    private rmiStartServer rmiServer;


    /**
     * Server launcher
     */
    private static ServerLauncher serverLauncher;
    /**
     * Map of all logged and active players.
     */
    private ArrayList<User> nicknames;


    /**
     * Class constructor.
     */
    public ServerLauncher() {
        this.mainTimer = new Timer();
        this.round = 0;
        this.game = new Game();
        this.turnTimer = new Timer();
        this.turnHandlerTimer = new Timer();
        this.canJoin = true;
        this.dicePlaced = false;
        this.toolCardUsed = false;
        this.nothingToDo = false;
        this.rmiServer = new rmiStartServer();
        this.nicknames = new ArrayList<User>();
    }

    /**
     * Main method that start the rmi server to handle connection and the game
     * @param args
     */
    public static void main(String[] args) {
        try {
            serverLauncher = new ServerLauncher();
            serverLauncher.startSocketRMIServer(SOCKET_PORT, RMI_PORT);
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
    public void startSocketRMIServer(int socketPort, int rmiPort) throws Exception {
        rmiServer.setServerLauncher(serverLauncher);
        rmiServer.startServer(rmiPort);
    }

    /**
     * Getter of the registered players
     * @return the arraylist of registered players
     */
    public ArrayList<User> getNicknames() {
        return nicknames;
    }

    /**
     * Getter of the current player
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * getter of login mutex
     * @return the login mutex
     */
    public Object getLoginMutex() {
        return LOGIN_MUTEX;
    }

    /**
     * Getter of turn latch
     * @returnthe turn latch
     */
    public static CountDownLatch getTurnLatch() {
        return turnLatch;
    }

    /**
     * This method set a player as disconnected and notify other players of the disconnection
     * @param user player that has gone offline
     */
    public void disableUser(User user) {
        synchronized (LOGIN_MUTEX) {
            for(User serverUser : nicknames){
                if(serverUser.getUsername().equals(user.getUsername())){
                    serverUser.setOnline(DISCONNECTED);
                    nothingToDo = true;
                }
            }
            notifyDisconnection(user);
        }
    }

    /**
     * Notify the siconnection of a user
     * @param user User that has disconnected
     */
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


    /**
     * Method that permits user registration
     * @param clientPlayer client interface that permits comunication with the client
     * @param username username of the player
     * @return false if the register fails, true otherwise
     */
    public boolean registerUser(PlayerInterface clientPlayer, String username) {

        synchronized (LOGIN_MUTEX) {
            if(this.nicknames.size()>EMPTYARRAYLIST){
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
                else if (nicknames.size()>EMPTYARRAYLIST){
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
                    for (User user : serverLauncher.getNicknames()) {
                        user.getPlayerInterface().notifyConnection(username);
                    }
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
        }
    }

    /**
     * Getter of current player interface
     * @return current player interface
     */
    public static PlayerInterface getCurrentPlayerInterface() {
        return currentPlayerInterface;
    }

    /**
     * Setter of the current
     * @param currentPlayerInterface
     */
    public static void setCurrentPlayerInterface(PlayerInterface currentPlayerInterface) {
        ServerLauncher.currentPlayerInterface = currentPlayerInterface;
    }

    /**
     * Start the timer for waiting room
     * @param waitingTime time to wait in room
     */
    private void startCountDownTimer(long waitingTime) {
        mainTimer = new Timer();
        mainTimer.schedule(new GameHandler(), waitingTime);
    }

    /**
     * Cancel a timer
     */
    private void cancelTimer() {
        if (mainTimer != null) {
            mainTimer.cancel();
            mainTimer.purge();
        }
    }

    /**
     * The class that handle the game workflow
     */
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
            chooseWindowPattern();
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
                                    currentPlayerInterface = user.getPlayerInterface();
                                    startTurn(user.getPlayerInterface(), currentPlayer);
                                }
                            }
                        }
                    }
                }
            } while (game.getRound() <= MAXNUMBEROFROUND);
            calculateFinalScore(game);
            notifyWinnerAndLoosers(game);
        }


        /**
         * This method is used to permits player to choose among window pattern
         */
        public void chooseWindowPattern() {
            for (User user : getNicknames()) {
                WindowPattern[] windowPattern = null;
                int index = 0;
                currentPlayerInterface = user.getPlayerInterface();
                try {
                    windowPattern = game.getWindowPatternForAPlayer();
                    startTurnCountDownTimer(START_IMMEDIATELY);
                    index = user.getPlayerInterface().chooseWindowPattern(windowPattern);
                    stopTimer();
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

        /**
         * This method handle a single turn
         * @param playerInterface player interface of the player that has to play the turn
         * @param player Player thet has to play
         */
        public void startTurn(PlayerInterface playerInterface, Player player) {
            nothingToDo = false;
            toolCardUsed = false;
            dicePlaced = false;
            startTurnCountDownTimer(START_IMMEDIATELY);
            while (!(nothingToDo || (toolCardUsed && dicePlaced))) {
                try {
                    playerInterface.setMyTurn(true);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                updateGameSession();
                getMoves(playerInterface, player);
                endturn(playerInterface);
            }
        }

        /**
         * Start the countdown timer of the turn
         * @param waitingTime time of the countdown
         */
        public void startTurnCountDownTimer(long waitingTime){
            turnHandlerTimer.schedule(new TurnTimerHandler(), waitingTime);
        }

        /**
         * Method that permits the player to choose the moves
         * @param playerInterface player interface of the player that has to play
         * @param player player thet has to play
         */
        public void getMoves(PlayerInterface playerInterface, Player player) {
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

        /**
         * Method that is used to choose between toolcards
         * @param i integer that represent the index of the toolcard
         * @param playerInterface player interface of the playing player
         * @param player playing player
         */
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

        /**
         * Method that invoke a toolcard
         * @param i index of the toolcard
         * @param playerInterface player interface of the player playing
         * @param player player playing
         */
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

        /**
         * Method that invoke a toolcard
         * @param i index of the toolcard
         * @param playerInterface player interface of the player playing
         * @param player player playing
         */
        public void useEnglimiseBrush(int i, PlayerInterface playerInterface, Player player) {
            oneDiceMoveToolUsage(i, playerInterface, player);
        }


        /**
         * Method that invoke a toolcard
         * @param i index of the toolcard
         * @param playerInterface player interface of the player playing
         * @param player player playing
         */
        public void useCopperFoilBurnisher(int i, PlayerInterface playerInterface, Player player) {
            oneDiceMoveToolUsage(i, playerInterface, player);
        }


        /**
         * Method that invoke a toolcard
         * @param i index of the toolcard
         * @param playerInterface player interface of the player playing
         * @param player player playing
         */
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

        /**
         * Method that invoke a toolcard
         * @param i index of the toolcard
         * @param playerInterface player interface of the player playing
         * @param player player playing
         */
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
                toolCardUsed = false;
                return;
            }

        }

        /**
         * Method that invoke a toolcard
         * @param i index of the toolcard
         * @param playerInterface player interface of the player playing
         * @param player player playing
         */
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
                toolCardUsed = false;
                return;
            }
            player.setChosenNut(game.getDiceFromCurrentDice(dice));
            game.getToolCards()[i].useTool(player);
            game.restoreDice(player, dice);
            toolCardUsed = true;
        }

        /**
         * Method that invoke a toolcard
         * @param i index of the toolcard
         * @param playerInterface player interface of the player playing
         * @param player player playing
         */
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

        /**
         * Method that invoke a toolcard
         * @param i index of the toolcard
         * @param playerInterface player interface of the player playing
         * @param player player playing
         */
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

        /**
         * Method that invoke a toolcard
         * @param i index of the toolcard
         * @param playerInterface player interface of the player playing
         * @param player player playing
         */
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

        /**
         * Method that invoke a toolcard
         * @param i index of the toolcard
         * @param playerInterface player interface of the player playing
         * @param player player playing
         */
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

        /**
         * Method that invoke a toolcard
         * @param i index of the toolcard
         * @param playerInterface player interface of the player playing
         * @param player player playing
         */
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

        /**
         * Method that invoke a toolcard
         * @param i index of the toolcard
         * @param playerInterface player interface of the player playing
         * @param player player playing
         */
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

        /**
         * Method that permits the player to place a dice
         * @param playerInterface plyer interface of the player playing
         * @param player player playing
         */
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

        /**
         * Method that ends player turn
         * @param playerInterface player that want or has to end the turn
         */
        public void endturn(PlayerInterface playerInterface) {
            try {
                playerInterface.setMyTurn(false);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            stopTimer();

        }

        /**
         * Method that calculate the final score for the players
         * @param game the current game playing
         */
        public void calculateFinalScore(Game game) {
            for (Player player : game.getPlayers()) {
                getFinalPointsFromPublicObjectives(game, player);
                getFinalPointsFromPrivateObjective(game, player);
                getFinalPointsFromFrame(game, player);
                getFinalPointsFromFavorToken(game, player);
                updateGameSession();
            }
        }

        /**
         * Method that gets the final score from public objectives
         * @param game current game playing
         * @param player adding the score to this player
         */
        public void getFinalPointsFromPublicObjectives(Game game, Player player) {
            for (PublicObjective publicObjective : game.getPublicObjectives()) {
                int finalScore = publicObjective.calculateScore(player);
                player.addFinalPoints(finalScore);
            }
        }

        /**
         * Method that gets the final score from private objective
         * @param game current game playing
         * @param player adding the score to the player
         */
        public void getFinalPointsFromPrivateObjective(Game game, Player player) {
            int privatePoint = player.getPrivateObjective().calculateScore(player);
            player.addFinalPoints(privatePoint);
        }

        /**
         * Method that gets the final score from the empty part from the frame
         * @param game current game playing
         * @param player adding the negative score to this player
         */
        public void getFinalPointsFromFrame(Game game, Player player) {
            int framePoints = player.playerFramePoints();
            player.addFinalPoints(framePoints);
        }

        /**
         * Method that gets the final score from the remaining favor token
         * @param game current game playing
         * @param player adding the score to this player
         */
        public void getFinalPointsFromFavorToken(Game game, Player player) {
            int favorPoints = player.getFavorTokens();
            player.addFinalPoints(favorPoints);
        }

        /**
         * This method notify the players if they are a winner or a looser
         * @param game game currently playing
         */
        public void notifyWinnerAndLoosers(Game game) {
            String winner = null;
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

        /**Method that calculate the winner
         * @param game game currently playing
         * @return the username of the winner
         */
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

        /**
         * Method that is used in some toolcard that moves two dice
         * @param i the index of the toolcard
         * @param playerInterface player interface of the player playing
         * @param player player playing
         * @param initialCoordinates1 initial coordinates of the first dice
         * @param finalCoordinates1 final coordinates of the firs dice
         * @param initialCoordinates2 initial coordinates of the second dice
         * @param finalCoordinates2 final coordinates of the second dice
         */
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

        /**
         * Method that is used in some toolcard that move a single dice
         * @param i index of the toolcard
         * @param playerInterface player interface of the player playing
         * @param player player playing
         */
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

        /**
         * Method that restore the used favor token
         * @param player restore the favor token to this player
         * @param i index of the toolcard
         */
        public void restoreFavorToken(Player player, int i) {
            player.restoreFavorTokenPlayer(game.getToolCards()[i]);
            toolCardUsed = false;
        }

        /**
         * Method that close the waiting room safely, without concurrent acces
         */
        private void closeRoomSafely() {
            synchronized (LOGIN_MUTEX) {
                canJoin = false;
            }
        }

        /**
         * Create the game session, it calls the game configurator
         */
        private void createGameSession() {
            for (User user : nicknames) {
                game.setAddPlayer(new Player(user.getUsername()));
            }
            new GameConfigurator(game);

        }

        /**
         * It update clients game session and displays it on screen
         */
        private void updateGameSession() {
            for (User user : nicknames) {
                try {
                    user.getPlayerInterface().setClientGame(game);
                } catch (RemoteException e) {

                }
            }
        }

        /**
         * Only update clients game session
         */
        private void updateGameSessionHide(){
            for (User user : nicknames) {
                try {
                    user.getPlayerInterface().setClientGameHide(game);
                } catch (RemoteException e) {

                }
            }
        }


        /**
         * send the game to all players/clients
         */
        private void dispatchGameSession() {
            for (User user : getNicknames()) {
                try {
                    user.getPlayerInterface().setClientGameHide(game);

                } catch (RemoteException e) {
                }
            }
        }


        /**
         * Stop a timer
         */
        void stopTimer() {
            resetTimer();
            while (getTurnLatch().getCount() > 0)
                getTurnLatch().countDown();
        }

        /**
         * RESET A TIMER
         */
        private void resetTimer() {
            turnTimer.cancel();
            turnTimer.purge();
        }
    }


    /**
     * Class that handle the count down
     */
    private class TurnCountDownTask extends TimerTask {

        /**
         * Method that handle the count down and set the player as not in his turn
         */
        @Override
        public void run() {
                if (turnLatch.getCount() > 0) {
                    if (turnLatch.getCount() == 1) {
                        try {
                            getCurrentPlayerInterface().setMyTurn(false);
                        } catch (RemoteException e) {
                            try {
                                getCurrentPlayerInterface().notifyError(e);
                            } catch (RemoteException e1) {
                                e1.printStackTrace();
                            }
                        }
                        stopTimer();

                    }
                    turnLatch.countDown();
                }
        }

        /**
         * Stops a timer
         */
        void stopTimer() {
            resetTimer();
            while (turnLatch.getCount() > 0)
                turnLatch.countDown();
        }

        /**
         * Reset a timer
         */
        private void resetTimer() {
            turnTimer.cancel();
            turnTimer.purge();
        }
    }

    /**
     * Class that handle the turn timer
     */
    private class TurnTimerHandler extends  TimerTask{
        /**
         * Method thet handle the turn timer, and starts it
         */
        @Override
        public void run() {
            startTimer(TURNTIME, getCurrentPlayerInterface());

        }

        /**
         * Method that start the timer of the turn
         * @param moveWaitingTime time that the player has to play his turn
         * @param playerInterface player interface of the player playing
         */
        void startTimer(long moveWaitingTime, PlayerInterface playerInterface) {
            int time = (int) (moveWaitingTime / 1000);
            turnLatch = new CountDownLatch(INTTURNTIME);
            turnTimer = new Timer();
            turnTimer.schedule(new TurnCountDownTask(), 1000, 1000);
            try {
                turnLatch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            try {
                getCurrentPlayerInterface().setMyTurn(false);
            } catch (RemoteException e) {
                try {
                    getCurrentPlayerInterface().notifyError(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
            nothingToDo = true;
            resetTimer();
        }

        /**
         * Reset a timer
         */
        private void resetTimer() {
            turnTimer.cancel();
            turnTimer.purge();
        }
    }
}



