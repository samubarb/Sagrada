package it.polimi.ingsw.controller.Server;


import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.Server.Rmi.rmiStartServer;
import it.polimi.ingsw.model.Game;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Main server class that extends .
 * This class contains the main method to launch the server.
 * It represent the game server.
 */
public class ServerLauncher {
    public static final int MAXPLAYER = 4;
    public static final int MINPLAYERS = 2;
    public static  final int TIMETOWAITINROOM = 60;
    public static final int START_IMMEDIATELY = 0;
    private Timer mainTimer;


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

    private Game game;
    /**
     * Class constructor.
     */
    public ServerLauncher() {
        rmiServer = new rmiStartServer();
        //socketServer = new SocketServerAbstract(this);
        nicknames = new ArrayList<User>();
        offlineNicknames = new ArrayList<User>();
        //configure();
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

    public void disableUser(User user){
        synchronized (LOGIN_MUTEX) {
            offlineNicknames.add(user);
            nicknames.remove(user);
            notifyDisconnection(user);
        }
    }

    public void notifyDisconnection(User user){
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

    public void notifyReconnection(User user){
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
            if(this.offlineNicknames.size()>0) {
                //reconnection of already registered player
                for (User user : offlineNicknames) {
                    if (user.getUsername().equals(username)) {

                        this.nicknames.add(new User(username, clientPlayer));
                        this.offlineNicknames.remove(user);
                        try {
                            clientPlayer.onRegister("Welcome again reconnected user: "+username);
                            System.out.println("User logged again: "+username);
                            //rmiServer.executeCheckConnectionThread();

                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        //ridò il game al giocatore
                        return true;
                    }
                }
            } else if(nicknames.size()>0) {
                if(nicknames.size()==2){
                    startCountDownTimer(TIMETOWAITINROOM);
                }
                for (User user : nicknames) {
                    if (user.getUsername() .equals(username)) {
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
                for(User user: serverLauncher.getNicknames()){
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

    public boolean contains(String username, ArrayList<User> userArrayList){
        for(User user : userArrayList){
            if(user.getUsername().equals(username))
                return true;
            else
                return false;
        }

        return false;
    }

    public void getClientInterface(String username){

    }

    private void startCountDownTimer(long waitingTime) {
        mainTimer = new Timer();
        //mainTimer.schedule(new GameHandler().startGame(), waitingTime);
    }

    private void cancelTimer() {
        if (mainTimer != null) {
            mainTimer.cancel();
            mainTimer.purge();
        }
    }
}
