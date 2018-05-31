package it.polimi.ingsw.controller.client.rmiClient;

import it.polimi.ingsw.controller.Adapter;
import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.RMIApi.ServerInterface;
import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.controller.Server.Socket.Connect;
import it.polimi.ingsw.controller.Server.User;
import it.polimi.ingsw.controller.client.ClientLauncher;
import it.polimi.ingsw.model.Coordinates;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.view.CLI;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.exceptions.InvalidPositionException;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.Timer;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class RMIClientLauncher implements  PlayerInterface, Serializable {
    public static final String RMI_SERVER_INTERFACE = "ServerInterface";
    public static final String RMI_CLIENT_INTERFACE = "ClientInterface";

    private static String address;
    private static Player clientPlayer;
    private static PlayerInterface playerInterface;
    private static int port;
    private static ServerInterface server;
    private static String username;
    private static String name;
    private static Scanner input;
    private static boolean isMyTurn;
    private static boolean gameStarted;
    private static ClientLauncher clientLauncher;
    private static Registry clientRegistry;
    private static Registry serverRegistry;
    private static Game game;
    private static View view;

    //debug timer
    static int interval = 7;
    static Timer timer;
    static Connect client;

    public void startRMIClient(){
        view = new CLI();
        input = new Scanner(System.in);
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ipAddr = addr.getHostAddress();
        System.out.println(ipAddr);
        try {
            System.out.println( InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        address = getIpServer(input);
        port = getPortServer(input);
        if(!connect(address, port)){
            System.out.println("Restart Client, connection to server error");
            return;
        }



        username = getUsername(input);
        playerInterface = this;
        registerPlayerOnServer(username, playerInterface);
        isMyTurn = false;
        gameStarted = true;
        while(gameStarted) {
            while (isMyTurn) {
                System.out.println("cosa vuoi fare:\n" + "1 numero giocatori registrati");
                int response = Integer.parseInt(input.next());
                if (response == 1) {
                    int playerNumber = 0;
                    try {
                        playerNumber = server.getNumberOfPlayer();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    System.out.println(playerNumber);
                }
                if (response == 2) {
                    String activeInactivePLayer = null;
                    try {
                        activeInactivePLayer = server.getNumberOfPlayerActiveInactive();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    System.out.println(activeInactivePLayer);
                }
            }
        }
    }

    /*
   This method set the address as the ip server typed by the player
    */
    public String getIpServer(Scanner input){
        System.out.println("Scrivi l'ip del server");
        return input.next();
    }
    /*
    This method set the port as the port number typed by the player
     */
    public int getPortServer(Scanner input){
        System.out.println("Scrivi la porta del server");
        return Integer.parseInt(input.next());
    }
    /*
    This methond establishes the connection to the server
    Returns false in case of any problems, true otherwise
     */
    public boolean connect(String address, int port) {
        try {
            System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());
            serverRegistry = LocateRegistry.getRegistry(address,port);
            server = (ServerInterface) serverRegistry.lookup(RMI_SERVER_INTERFACE);
            playerInterface = (PlayerInterface) UnicastRemoteObject.exportObject(this, 0);

            //serverRegistry.rebind(RMI_CLIENT_INTERFACE, this);
        } catch (Exception e) {
            System.err.println("Error: Connection to the server");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /*
    This method gets the username from the player, from the input scanner and set client name as the keyboard's input
     */
    public String getUsername(Scanner input){
        System.out.println("Scrivi username");
        return input.next();
    }
    public static void registerPlayerOnServer(String username, PlayerInterface playerInterface){
        System.out.println("Try to login user with nickname: " + username);
        try {
            server.register(playerInterface, username);
        } catch (RemoteException e) {
            try {
                server.print("Problem with the comunication with the server"+ e);
            } catch (RemoteException e1) {
                e1.printStackTrace();
                System.out.println("errore remote");
            }
        }
    }



    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        RMIClientLauncher.address = address;
    }

    public static Player getClientPlayer() {
        return clientPlayer;
    }

    public static void setClientPlayer(Player clientPlayer) {
        RMIClientLauncher.clientPlayer = clientPlayer;
    }

    public static PlayerInterface getPlayerInterface() {
        return playerInterface;
    }

    public static void setPlayerInterface(PlayerInterface playerInterface) {
        RMIClientLauncher.playerInterface = playerInterface;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        RMIClientLauncher.port = port;
    }

    public static ServerInterface getServer() {
        return server;
    }

    public static void setServer(ServerInterface server) {
        RMIClientLauncher.server = server;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        RMIClientLauncher.username = username;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        RMIClientLauncher.name = name;
    }

    public static Scanner getInput() {
        return input;
    }

    public static void setInput(Scanner input) {
        RMIClientLauncher.input = input;
    }

    public static boolean isIsMyTurn() {
        return isMyTurn;
    }

    public static void setIsMyTurn(boolean isMyTurn) {
        RMIClientLauncher.isMyTurn = isMyTurn;
    }

    public static boolean isGameStarted() {
        return gameStarted;
    }

    public static void setGameStarted(boolean gameStarted) {
        RMIClientLauncher.gameStarted = gameStarted;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        RMIClientLauncher.game = game;
    }

    public static ClientLauncher getClientLauncher() {
        return clientLauncher;
    }

    public static void setClientLauncher(ClientLauncher clientLauncher) {
        RMIClientLauncher.clientLauncher = clientLauncher;
    }

    public static Registry getClientRegistry() {
        return clientRegistry;
    }

    public static void setClientRegistry(Registry clientRegistry) {
        RMIClientLauncher.clientRegistry = clientRegistry;
    }

    public static Registry getServerRegistry() {
        return serverRegistry;
    }

    public static void setServerRegistry(Registry serverRegistry) {
        RMIClientLauncher.serverRegistry = serverRegistry;
    }

    @Override
    public String ping() {
        return username;
    }

    @Override
    public void notifyDisconnection(User user) throws RemoteException {
        System.out.println("User "+user.getUsername()+" is disconnected");
    }

    @Override
    public void notifyReconnection(User user) throws RemoteException {
        System.out.println("User "+user.getUsername()+" is reconnected");
    }

    @Override
    public void printaaa(String string) throws RemoteException {
        System.out.println(string);
    }

    @Override
    public void onRegister(String string) throws RemoteException {
        System.out.println(string);
    }

    @Override
    public void notifyConnection(String username) throws RemoteException {
        System.out.println("Nuovo user connesso: "+ username);
    }

    @Override
    public void notifyTurn(String username) throws RemoteException {
        System.out.println("è il turno di:"+username);
    }

    @Override
    public void setMyTurn(boolean isMyTurn) throws RemoteException {
        System.out.println("è il tuo turno");
        this.setIsMyTurn(isMyTurn);
    }

    @Override
    public int getDiceToBePlaced() throws RemoteException {
        println("Array da cui scegliere");
        try {
            println(new AdapterCLI().currentDiceToView(game.getCurrentDice()).toString());
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        return view.askDice();

    }

    @Override
    public Coordinates getDiceFinalPosition() throws RemoteException {
        println(new AdapterCLI().frameToView(game.getPlayerByUsername(username).getFrame()).toString());
        println(new AdapterCLI().patternToView(game.getPlayerByUsername(username).getWindowPattern()).toString());
        return new AdapterCLI().coordinatesToModel(view.askCoordinates());
               // new Coordinates(0,2);
    }

    @Override
    public void setClientGame(Game game) throws RemoteException {
        this.setGame(game);
    }

    @Override
    public void printPlayersFrame() throws RemoteException {
        for(Player player: game.getPlayers()){
            println(player.getName());
            println(new AdapterCLI().frameToView(game.getPlayerByUsername(player.getName()).getFrame()).toString());
            println(new AdapterCLI().patternToView(game.getPlayerByUsername(player.getName()).getWindowPattern()).toString());
        }
    }
}
