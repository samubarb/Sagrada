package it.polimi.ingsw.controller.Server.Rmi;

import it.polimi.ingsw.controller.RMIApi.ServerInterface;
import it.polimi.ingsw.controller.Server.ServerLauncher;
import it.polimi.ingsw.controller.Server.User;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class rmiStartServer {

        /**
         * RMI server.
         */
        private static ServerInterfaceImpl RMIServer;
        private static final int RMIServerPort = 1098;
        private ServerLauncher serverLauncher;



        public void startServer(int port) /*throws RemoteException */{
            /*if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }*/
            // First, create the real object which will do the requested function.
            ServerInterface implementation = new ServerInterfaceImpl();
            ((ServerInterfaceImpl) implementation).setServerLauncher(serverLauncher);

            try {
                //System.setProperty("java.rmi.server.hostname","192.168.1.2");

                // Export the object.
                ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(implementation, port);
                Registry registry = LocateRegistry.createRegistry(port);
                //Registry registry = LocateRegistry.getRegistry();
                //registry.bind("ServerInterface", stub);

                registry.rebind("ServerInterface", stub);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(checkClientsConnection, 0, 1, TimeUnit.SECONDS);
            System.out.println("Bound!");
            System.out.println("Server will wait forever for messages.");
        }

    /**
     * Runnable used to check periodically if a RMI client is connected.
     */
    private Runnable checkClientsConnection = () -> {
        for (User user : serverLauncher.getNicknames()) {
            try {
                user.getPlayerInterface().ping();
            } catch (RemoteException e) {
                System.out.println("RMIServerAbstract.java Connection with the client is down.");
                getServerLauncher().disableUser(user);
                //activeUsers.remove(pair.getKey());
            }
        }
    };

    public ServerLauncher getServerLauncher() {
        return serverLauncher;
    }

    public void setServerLauncher(ServerLauncher serverLauncher) {
        this.serverLauncher = serverLauncher;
    }
}
