package it.polimi.ingsw.controller.Server;


import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.Server.Rmi.rmiStartServer;
import it.polimi.ingsw.model.Game;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Main server class that extends .
 * This class contains the main method to launch the server.
 * It represent the game server.
 */
public class ServerLauncher {
    public static final int MAXPLAYER = 4;


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

    public void disableUser(User user){
        nicknames.remove(user);
        offlineNicknames.add(user);
        notifyDisconnection(user);
    }

    public void notifyDisconnection(User user){
        for(User username: nicknames){
            try {
                username.getPlayerInterface().notifyDisconnection(user);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean registerUser(PlayerInterface clientPlayer, String username) {

        synchronized (LOGIN_MUTEX) {
            //nicknames.add(new User("saff", clientPlayer));
            if ((this.nicknames.size() + this.offlineNicknames.size()) >= MAXPLAYER)
                return false;
            if(this.offlineNicknames.size()>0) {
                //reconnection of already registered player
                for (User user : offlineNicknames) {
                    if (user.getUsername() == username) {
                        this.nicknames.add(user);
                        this.offlineNicknames.remove(user);
                        //ridò il game al giocatore
                        return true;
                    }
                }
            } else if(nicknames.size()>0) {
                for (User user : nicknames) {
                    if (user.getUsername() == username) {
                        try {
                            clientPlayer.printa("Nome già in uso");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                }
            }
            try {
                clientPlayer.printa("Utente loggato con: " + username);
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
            nicknames.add(new User(username, clientPlayer));
            return true;





        }
    }
}
