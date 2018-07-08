package it.polimi.ingsw.controller.client.rmiClient;


import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.RMIApi.ServerInterface;
import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.controller.Server.ServerSettings;
import it.polimi.ingsw.controller.Server.User;
import it.polimi.ingsw.controller.client.ClientSettings;
import it.polimi.ingsw.inputoutput.IOManager;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.*;
import it.polimi.ingsw.view.CLI;
import it.polimi.ingsw.view.GUI;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.other_elements.VConnectionStatus;
import it.polimi.ingsw.view.other_elements.VError;


import java.io.Serializable;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;




public class RMIClientLauncher implements  PlayerInterface, Serializable {
    /**
     * This define the name for registry lookup
     */
    private static final String RMI_SERVER_INTERFACE = "ServerInterface";

    /**
     * Server addres used for connection
     */
    private static String address;

    /**
     * Player interface that allows to comunicate with server
     */
    private static PlayerInterface playerInterface;
    /**
     * Port for the connection to the server
     */
    private static int port;
    /**
     * Server interface to comunicate to the server
     */
    private static ServerInterface server;
    /**
     * Username choosen by the user
     */
    private static String username;
    /**
     * Initial input scannet to read username and type of user interface
     */
    private static Scanner input;
    /**
     * Boolean that represents if it's players turn
     */
    private static boolean isMyTurn;
    /**
     *  Boolean that represents if the game is started
     */
    private static boolean gameStarted;
    /**
     * Server registry for comunication with server
     */
    private static Registry serverRegistry;
    /**
     * Client copy of the game
     */
    private static Game game;
    /**
     * Choosen type of user interface, cli or gui
     */
    private static View view;


    /**
     * This metod starts the RMI client, it sets the type of user interface, address and port from file, player's username and
     * make the connection with the server
     */
    public void startRMIClient(){
        input = new Scanner(System.in);
        int viewType = chooseViewType();
        address = new ServerSettings().setFromJSON().getServerAddress();//getIpServer(input);
        port = new ServerSettings().setFromJSON().getPort();//getPortServer(input);
        if(!connect(address, port)){
            System.out.println("Restart Client, connection to server error");
            return;
        }
        boolean isRegistered;
        do{
            username = getUsername(input);
            if(viewType == 1) {
                view = new CLI(username);
            } else if(viewType == 2){
                view = new GUI(username);
            }
            view.splash();
            playerInterface = this;
            isRegistered = registerPlayerOnServer(username, playerInterface);
        } while(!isRegistered);
        isMyTurn = false;
        gameStarted = true;
    }

    /**
     * This method permits the choose of the user interface
     * @return 1 for cli, 2 for gui
     */
   public int chooseViewType(){
       System.out.println("Seleziona l'interfaccia utente");
       System.out.println("(1) Command line interface");
       System.out.println("(2) Graphical interface");
       return new IOManager().getInt(1,2);
   }

    /**
     * This methond establishes the connection to the server
     *     Returns false in case of any problems, true otherwise
     * @param address server address
     * @param port server port
     * @return false if connection fails tru otherwise
     */
    public boolean connect(String address, int port) {
        try {
            System.setProperty("java.rmi.server.hostname", new ClientSettings().setFromJSON().getClientAddress());
            serverRegistry = LocateRegistry.getRegistry(address,port);
            server = (ServerInterface) serverRegistry.lookup(RMI_SERVER_INTERFACE);
            playerInterface = (PlayerInterface) UnicastRemoteObject.exportObject(this, 0);
        } catch (Exception e) {
            System.err.println("Error: Connection to the server");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method gets the username from the player, from the input scanner and set client name as the keyboard's input
     * @param input scanner used for reading keyboard input
     * @return a string representing the player name
     */
    public String getUsername(Scanner input){
        System.out.println("Scrivi username");
        return input.next();
    }

    /**
     * This method registers the player on the server
     * @param username player's username
     * @param playerInterface player's interface for comunication
     * @return false if registration fails, true otherwise
     */
    public static boolean registerPlayerOnServer(String username, PlayerInterface playerInterface){
        boolean registered = false;
        try {
            registered =  server.register(playerInterface, username);
        } catch (RemoteException e) {
            view.notifyError(VError.CONNECTION);
        }
        return registered;
    }



    /**
     * Getter of server interface
     * @return server interface
     */
    public static ServerInterface getServer() {
        return server;
    }

    /**
     * Setter of server interface
     * @param server server interface
     */
    public static void setServer(ServerInterface server) {
        RMIClientLauncher.server = server;
    }

    /**
     * Getter of server address
     * @return server address
     */
    public static String getAddress() {
        return address;
    }

    /**
     * Setter of server address
     * @param address server address
     */
    public static void setAddress(String address) {
        RMIClientLauncher.address = address;
    }

    /**
     * Getter of server port
     * @return server port
     */
    public static int getPort() {
        return port;
    }

    /**
     * Setter of server port
     * @param port server port
     */
    public static void setPort(int port) {
        RMIClientLauncher.port = port;
    }

    /**
     * getter of isMyTurn
     * @return value of isMyTurn
     */
    public static boolean isIsMyTurn() {
        return isMyTurn;
    }


    /**
     * Getter of game started
     * @return value of game started
     */
    public static boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * Setter of game started
     * @param gameStarted value of game started
     */
    public static void setGameStarted(boolean gameStarted) {
        RMIClientLauncher.gameStarted = gameStarted;
    }

    /**
     * Getter of player username
     * @return player username
     */
    public static String getUsername() {
        return username;
    }

    /**
     * Setter of player username
     * @param username player username
     */
    public static void setUsername(String username) {
        RMIClientLauncher.username = username;
    }

    /**
     * Setter of is my turn
     * @param isMyTurn true if is player turn, false otherwise
     */
    public static void setIsMyTurn(boolean isMyTurn) {
        RMIClientLauncher.isMyTurn = isMyTurn;
    }

    /**
     * Getter of the player copy of the game
     * @return player copy of the game
     */
    public static Game getGame() {
        return game;
    }

    /**
     * Setter of the player copy of the game
     * @param game player copy of the gmae
     */
    public static void setGame(Game game) {
        RMIClientLauncher.game = game;
    }

    /**
     * Getter of the user interface
     * @return user interface
     */
    public static View getView() {
        return view;
    }

    /**
     * Setter of user interface
     * @param view user interface
     */
    public static void setView(View view) {
        RMIClientLauncher.view = view;
    }

    /**
     * Method used to verify if player is still online
     * @return player username used to verify if the player is online
     */
    @Override
    public String ping() {
        return username;
    }

    /**
     * Notify thet a player has disconnected
     * @param user the user that has gone offline
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyDisconnection(User user) throws RemoteException {
        System.out.println("User "+user.getUsername()+" is disconnected");
    }

    /**
     * notify that a player has reconnected
     * @param username player username
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyReconnection(String username) throws RemoteException {
        view.notifyConnectionStatus(username, VConnectionStatus.CONNECTED);
    }

    /**
     * General output print used for debugging
     * @param string string to output
     * @throws RemoteException Connection Problem
     */
    @Override
    public void printaaa(String string) throws RemoteException {
        System.out.println(string);
    }

    /**
     * Output print used for debugging, during user registration
     * @param string string to be printed
     * @throws RemoteException Connection Problem
     */
    @Override
    public void onRegister(String string) throws RemoteException {
        System.out.println(string);
    }

    /**
     * Notify that a player has reconnected
     * @param username is te name of the player connected
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyConnection(String username) throws RemoteException {
        view.notifyConnectionStatus(username, VConnectionStatus.CONNECTED);
    }

    /**
     * Notify that is the player's turn
     * @param username player playing's name
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyTurn(String username) throws RemoteException {
        //System.out.println("è il turno di:"+username);
    }

    /**
     * Sets is my turn
     * @param isMyTurn true if player has to play false otherwise
     * @throws RemoteException Connection Problem
     */
    @Override
    public void setMyTurn(boolean isMyTurn) throws RemoteException {
        this.setIsMyTurn(isMyTurn);
    }

    /**
     * Get the dice that the player wants to place from the current dice
     * @return index of the choosen dice of the current dice
     * @throws RemoteException Connection Problem
     */
    @Override
    public int getDiceToBePlaced() throws RemoteException {
        return view.askDice();

    }

    /**
     * Get the dice final coordinates
     * @return the final coordinates
     * @throws RemoteException Connection Problem
     */
    @Override
    public Coordinates getDiceFinalPosition() throws RemoteException {
        return new AdapterCLI().coordinatesToModel(view.askCoordinates());
    }

    /**
     * Set the client game and displays the board
     * @param game game that has to be set
     * @throws RemoteException Connection Problem
     */
    @Override
    public void setClientGame(Game game) throws RemoteException {
        this.setGame(game);
        this.view.updateState(new AdapterCLI().gameToView(game));
    }

    /**
     * Set the client game but doesn't display it
     * @param game game that has to be set
     * @throws RemoteException Connection Problem
     */
    @Override
    public void setClientGameHide(Game game) throws RemoteException {
        this.setGame(game);
    }


    /**
     * Get the move from the player
     * @return an integer representing the move, 0 place dice, 1 use toolcar, 2 endturn
     */
    @Override
    public int getMoves() {
        int moves = view.askMove();
        return moves;
    }

    /**
     * Get the toolcar that the player wants to use
     * @return an integer from 0 to 11 that represents the choosen toolcard
     * @throws RemoteException Connection Problem
     */
    @Override
    public int getToolcard() throws RemoteException {
        int toolCard = view.askToolCard();
        return toolCard;
    }

    /**
     * Get the choosen dice from the current dice
     * @return an integer representing the index of the dice
     * @throws RemoteException Connection Problem
     */
    @Override
    public int getDiceFromReserve() throws RemoteException {
        return view.askDice();
    }

    /**
     * Get the player choice between increase or decrease
     * @return increase or decrease
     * @throws RemoteException Connection Problem
     */
    @Override
    public Action getTypeOfAction() throws RemoteException {
        return new AdapterCLI().booleanToAction(view.askAction());
    }

    /**
     * Get the dice that the player wants to move
     * @return the initial coordinates of the dice
     * @throws RemoteException Connection Problem
     */
    @Override
    public Coordinates getDiceToBeMoved() throws RemoteException {
        return new AdapterCLI().coordinatesToModel(view.askCoordinates());
    }

    /**
     * get the final destination of a dice
     * @return final destination of a dice
     * @throws RemoteException Connection Problem
     */
    @Override
    public Coordinates getDiceDestination() throws RemoteException {
        return new AdapterCLI().coordinatesToModel(view.askCoordinates());
    }

    /**
     * Get the initial coordinates of a dice, more than one, represented by the ordinal number
     * @param i ordinal number
     * @return initial coordinates of the dice
     * @throws RemoteException Connection Problem
     */
    @Override
    public Coordinates getDiceToBeMoved(int i) throws RemoteException {
        return new AdapterCLI().coordinatesToModel(view.askCoordinates(i));
    }

    /**get the final destination of a dice, more than one, represented by the ordinal number
     * @param i ordinal number
     * @return final destination of the dice
     * @throws RemoteException Connection Problem
     */
    @Override
    public Coordinates getDiceDestination(int i) throws RemoteException {
        return new AdapterCLI().coordinatesToModel(view.askCoordinates(i));
    }

    /**
     * Get the dice from the round dice that the player wants to swap
     * @return coordinates of the dice on the round track
     * @throws RemoteException Connection Problem
     */
    @Override
    public Coordinates getRoundDiceToBeSwapped() throws RemoteException {
        return new AdapterCLI().intToCoordinates(view.askToPickFromRoundTrack());
    }


    /**
     * Get the value of a rerolled dice
     * @param dice the dice used to represents its colour
     * @return the value of the dice
     * @throws RemoteException Connection Problem
     */
    @Override
    public int getDiceValue(Dice dice) throws RemoteException {
         return view.askDiceNumber(new AdapterCLI().diceToView(dice));

    }

    /**
     * Print on screen the final scoreboard
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyScoreBoard() throws RemoteException {
        view.notifyScore();
        return;
    }

    /**
     * Displays the error on screen
     * @param e Connection problem
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyError(RemoteException e) throws RemoteException {
        view.notifyError(VError.CONNECTION);
    }

    /** Displays error on screen
     * @param e Not enough favor token to use a tool card
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyError(FavorTokenException e) throws RemoteException {
        view.notifyError(VError.FAVOR_TOKEN);
    }

    /**
     * Displays the error on screen
     * @param e dice choosen from the current dice is not available
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyError(NutChosenWrongException e) throws RemoteException {
        view.notifyError(VError.NUT_CHOSEN_WRONG);
    }

    /**
     * Displays the error on screen
     * @param e the input of the toolcard doest't respect the input restraint, reinsert it
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyError(IllegalArgumentException e) throws RemoteException {
        view.notifyError(VError.ILLEGAL_MOVE);
    }

    /**
     * Displays the error on screen
     * @param e Problem with a color restriction in the window pattern card
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyError(WindowPatternColorException e) throws RemoteException {
        view.notifyError(VError.WP_COLOR);
    }

    /**
     * Displays the error on screen
     * @param e Problem with a value restriction in the window pattern card
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyError(WindowPatternValueException e) throws RemoteException {
        view.notifyError(VError.WP_VALUE);
    }

    /**
     * Displays the error on screen
     * @param e Problem with and adjacent restriction, it can be value or color, in the frame
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyError(FrameValueAndColorException e) throws RemoteException {
        view.notifyError(VError.FRAME_VALUE_AND_COLOR);
    }

    /**
     * Displays the error on screen
     * @param e the final position of the dice is busy
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyError(BusyPositionException e) throws RemoteException {
        view.notifyError(VError.BUSY_POSITION);
    }

    /**
     * Displays the error on screen
     * @param e The dice is not adjacentto any other dice
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyError(AdjacentDiceException e) throws RemoteException {
        view.notifyError(VError.ADIACENT_DICE);
    }

    /**
     * Asks the player to choose a window pattern among 4
     * @param windowPattern array of the player's window pattern from which he can choose
     * @return the index of the coosen window pattern
     * @throws RemoteException Connection Problem
     */
    @Override
    public int chooseWindowPattern(WindowPattern[] windowPattern) throws RemoteException {
        return view.askWindowPattern(new AdapterCLI().patternToView(windowPattern));
    }

    /**
     * Displays if the player is a winner
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyWinner() throws RemoteException {
        view.notifyWin();
    }

    /**
     * Displays if the player is a loosers
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyLoosers() throws RemoteException {
        view.notifyLose();
    }

    /** Display that a player has reconnected
     * @param username username of the reconnected user
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyUserReconnection(String username) throws RemoteException {
        view.notifyConnectionStatus(username, VConnectionStatus.RECONNECTED);
        view.notifyGreetings();
    }

    /**
     * Notify thet the room is full
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyMaxPlayerReached() throws RemoteException {
        view.notifyError(VError.GAME_FULL);
    }

    /**
     * Notify that the name is already in use
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyNameInUse() throws RemoteException {
        view.notifyError(VError.USERNAME_DUPLICATE);
    }

    /**
     * Notify that the player has logged
     * @param username username of the player logged
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyUserLogged(String username) throws RemoteException {
        view.notifyConnectionStatus(username, VConnectionStatus.CONNECTED);
        view.notifyGreetings();
    }

    /**
     * Notify that the game has started
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyGameStarted() throws RemoteException {
        view.notifyError(VError.GAME_ALREADY_STARTED);
    }

    /** Notify that a user has disconnecred
     * @param username username of the disconnected player
     * @throws RemoteException Connection Problem
     */
    @Override
    public void notifyUserDisconnection(String username) throws RemoteException {
        view.notifyConnectionStatus(username, VConnectionStatus.DISCONNECTED);
    }
}
