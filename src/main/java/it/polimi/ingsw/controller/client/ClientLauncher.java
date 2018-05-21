package it.polimi.ingsw.controller.client;

import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.RMIApi.ServerInterface;
import it.polimi.ingsw.controller.Server.Socket.Connect;
import it.polimi.ingsw.controller.Server.User;
import it.polimi.ingsw.model.Player;

import java.io.Serializable;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.Timer;

public class ClientLauncher implements PlayerInterface, Serializable {
    private static String address;
    private static Player clientPlayer;
    private static PlayerInterface playerInterface;
    private static int port = 1099;
    private static ServerInterface server;
    private static String username;
    private static String name;
    private static Scanner input;
    private static String logged;
    private static boolean isMyTurn;
    private static ClientLauncher clientLauncher;
    //debug timer
    static int interval = 7;
    static Timer timer;
    static Connect client;

    /*public ClientLauncher() {
        clientPlayer = new Player();
        //clientPlayer.setPlayerInterface(playerInterface);
    }*/

    public static void main(String[] args) {
        // Set the Security Manager that we want to use.
        // The Security Manager must be set, or it will not work.
        /*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/
        clientLauncher = new ClientLauncher();
        input = new Scanner(System.in);
        address = getIpServer(input);
        port = getPortServer(input);
        if(!connect(address, port)){
            System.out.println("Restart Client, connection to server error");
            return;
        }
        try {
            PlayerInterface stub = (PlayerInterface) UnicastRemoteObject.exportObject(clientLauncher, 0) ;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        username = getUsername(input);
        //color = getColor(input);
        clientPlayer = new Player();
        playerInterface = clientLauncher;

        registerPlayerOnServer(username, playerInterface);
        isMyTurn = true;
        while(isMyTurn){
            System.out.println("cosa vuoi fare:\n"+"1 numero giocatori registrati");
            int response = Integer.parseInt(input.next());
            if(response == 1){
                int playerNumber = 0;
                try {
                    playerNumber = server.getNumberOfPlayer();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                System.out.println(playerNumber);
            }
            if(response == 2) {
                String activeInactivePLayer = null;
                try {
                    activeInactivePLayer = server.getNumberOfPlayerActiveInactive();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                System.out.println(activeInactivePLayer);
            }
        }
        /*System.out.println("starto il primo counter");
        timer = new Timer();
        int delay = 10000;
        int period = 1000;
        //usare per fare partire la partita in ogni caso tra 2 a 4 giocatori
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(setInterval());
            }
        }, delay, period);*/

    }
    private static final int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }
    /*
    This method simply initializes a new input scanner
     */
    public static void createANewInputScanner(Scanner input){
        input = new Scanner(System.in);
    }
    /*
    This method set the address as the ip server typed by the player
     */
    public static String getIpServer(Scanner input){
        System.out.println("Scrivi l'ip del server");
        return input.next();
    }
    /*
    This method set the port as the port number typed by the player
     */
    public static int getPortServer(Scanner input){
        System.out.println("Scrivi la porta del server");
        return Integer.parseInt(input.next());
    }
    /*
    This methond establishes the connection to the server
    Returns false in case of any problems, true otherwise
     */
    public static boolean connect(String address, int port){
        try {
            System.out.println("TentativoDiConnessione");
            String name = "ServerInterface";
            Registry registry = LocateRegistry.getRegistry(address, port);
             server = (ServerInterface) registry.lookup(name);
            //server.print("Connection of "+username);
        }
        catch (Exception e) {
            System.err.println("Error: Connection to the server");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /*
    This method gets the username from the player, from the input scanner and set client name as the keyboard's input
     */
    public static String getUsername(Scanner input){
        System.out.println("Scrivi username");
        return input.next();
    }
    /*

     */
    public static void registerPlayerOnServer(String username, PlayerInterface playerInterface){
        System.out.println("Try to login user with nickname: " + username);
        try {
            server.register(playerInterface, username);
        } catch (RemoteException e) {
            try {
                server.print("Problem with the comunication with the server"+ e);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            //System.out.println("Nickname is already in use on server");
        }
        /*if(logged)
            System.out.println("Utente loggato");
        else
            System.out.println("Utente non loggato");*/
        //System.out.println(logged);
    }

    @Override
    public boolean ping() {
        return true;
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
}

