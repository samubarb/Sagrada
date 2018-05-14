package it.polimi.ingsw.controller.ProvaServer;

import it.polimi.ingsw.model.Player;

import java.awt.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ClientLauncher implements PlayerInterface, Serializable {
    private static String address;
    private static Player clientPlayer;
    private static PlayerInterface playerInterface;
    private static int port = 1099;
    private static ServerInterface server;
    private static String username;
    private static String name;
    private static Scanner input;

    //debug timer
    static int interval = 7;
    static Timer timer;

    private ClientLauncher() {
        clientPlayer = new Player();
        //clientPlayer.setPlayerInterface(playerInterface);
    }

    public static void main(String[] args) {
        // Set the Security Manager that we want to use.
        // The Security Manager must be set, or it will not work.
        /*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/
        input = new Scanner(System.in);
        address = getIpServer(input);
        port = getPortServer(input);
        if(!connect(address, port)){
            System.out.println("Restart Client, connection to server error");
            return;
        }
        username = getUsername(input);
        //color = getColor(input);
        clientPlayer = new Player();
        registerPlayerOnServer(username);

        System.out.println("starto il primo counter");
        timer = new Timer();
        int delay = 10000;
        int period = 1000;
        //usare per fare partire la partita in ogni caso tra 2 a 4 giocatori
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(setInterval());
            }
        }, delay, period);

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
            ServerInterface server = (ServerInterface) registry.lookup(name);
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
    public static void registerPlayerOnServer(String username){
        Boolean logged = false;
        System.out.println("Try to login user with nickname: " + username);
        try {
            logged = server.register(clientPlayer, username);
        } catch (RemoteException e) {
            try {
                server.print("Problem with the comunication with the server"+ e);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            System.out.println("Nickname is already in use on server");
        }
        if(logged)
            System.out.println("Utente loggato");
        else
            System.out.println("Utente non loggato");
    }
}

