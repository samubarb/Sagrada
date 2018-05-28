package it.polimi.ingsw.controller.client;

import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.RMIApi.ServerInterface;
import it.polimi.ingsw.controller.Server.Socket.Connect;
import it.polimi.ingsw.controller.Server.User;
import it.polimi.ingsw.controller.client.rmiClient.RMIClientLauncher;
import it.polimi.ingsw.model.Player;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.Timer;

public class ClientLauncher implements Serializable {
    private static String address;
    private static Player clientPlayer;
    private static PlayerInterface playerInterface;
    private static int port = 4343;
    private static ServerInterface server;
    private static String username;
    private static String name;
    private static Scanner input;
    private static String logged;
    private static boolean isMyTurn;
    private static ClientLauncher clientLauncher;
    private static Registry clientRegistry;
    private static RMIClientLauncher rmiClientLauncher;
    //debug timer
    static int interval = 7;
    static Timer timer;
    static Connect client;

    /*public ClientLauncher() {
        clientPlayer = new Player();
        //clientPlayer.setPlayerInterface(playerInterface);
    }*/

    public static void main(String[] args) {
        clientLauncher = new ClientLauncher();
        rmiClientLauncher = new RMIClientLauncher();
        rmiClientLauncher.startRMIClient();

    }
}

