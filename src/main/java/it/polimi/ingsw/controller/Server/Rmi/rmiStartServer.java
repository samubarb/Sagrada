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
        private ScheduledExecutorService executor;



        public void startServer(int port) /*throws RemoteException */{
            /*if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }*/
            // First, create the real object which will do the requested function.
            System.setProperty("java.rmi.server.hostname", "192.168.1.165");
            //System.setProperty("java.rmi.server.codebase", "C:\\Users\\Matteo\\Desktop\\Progetto Ingegneria del Software\\src\\main\\java\\it\\polimi\\ingsw\\controller\\Server\\Rmi\\server.policy");
            ServerInterface implementation = new ServerInterfaceImpl();
            ((ServerInterfaceImpl) implementation).setServerLauncher(serverLauncher);

            try {
                //System.setProperty("java.rmi.server.hostname","192.168.1.2");

                // Export the object.
                /* stub = (ServerInterface) UnicastRemoteObject.exportObject(implementation, port);
                Registry registry = LocateRegistry.createRegistry(port);*/



                Registry registry = LocateRegistry.createRegistry(port);
                LocateRegistry.getRegistry(port);
                registry.rebind("ServerInterface", implementation);
                UnicastRemoteObject.exportObject(implementation, port);


                //registry.rebind("ServerInterface", stub);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
            executeCheckConnectionThread();
            /*ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(checkClientsConnection, 0, 1, TimeUnit.SECONDS);*/
            System.out.println("Bound!");
            System.out.println("Server will wait forever for messages.");
        }
        public void executeCheckConnectionThread(){
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(checkClientsConnection, 0, 1, TimeUnit.SECONDS);
        }

    /**
     * Runnable used to check periodically if a RMI client is connected.
     */
    private Runnable checkClientsConnection = () -> {
            for (User user : serverLauncher.getNicknames()) {
                try {
                    user.getPlayerInterface().ping();
                } catch (RemoteException e) {
                    synchronized (serverLauncher.getLoginMutex()) {
                        System.out.println("Connection with the client is down. " + user.getUsername());
                        getServerLauncher().disableUser(user);
                        //activeUsers.remove(pair.getKey());
                    }
                }
            }

    };

    public ServerLauncher getServerLauncher() {
        return serverLauncher;
    }

    public void setServerLauncher(ServerLauncher serverLauncher) {
        this.serverLauncher = serverLauncher;
    }

    public ScheduledExecutorService getExecutor() {
        return executor;
    }

    public void setExecutor(ScheduledExecutorService executor) {
        this.executor = executor;
    }
}

