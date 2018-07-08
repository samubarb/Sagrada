package it.polimi.ingsw.controller.Server.Rmi;

import it.polimi.ingsw.controller.RMIApi.ServerInterface;
import it.polimi.ingsw.controller.Server.ServerLauncher;
import it.polimi.ingsw.controller.Server.ServerSettings;
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
     * Server launcher
     */
        private ServerLauncher serverLauncher;
    /**
     * Executor that checks if clients are still online
     */
        private ScheduledExecutorService executor;


    /**
     * Start the rmi server
     * @param port the port on which the server will be listening on
     */
        public void startServer(int port) /*throws RemoteException */{
            // First, create the real object which will do the requested function.
            System.setProperty("java.rmi.server.hostname", new ServerSettings().setFromJSON().getServerAddress());
            ServerInterface implementation = new ServerInterfaceImpl();
            ((ServerInterfaceImpl) implementation).setServerLauncher(serverLauncher);

            try {
                ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(implementation, 0);
                Registry registry = LocateRegistry.createRegistry(port);
                registry.bind("ServerInterface", stub);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
            executeCheckConnectionThread();
            System.out.println("Bound!");
            System.out.println("Server will wait forever for messages.");
        }
        private void executeCheckConnectionThread(){
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(checkClientsConnection, 0, 1, TimeUnit.SECONDS);
        }

    /**
     * Runnable used to check periodically if a RMI client is connected.
     */
    private Runnable checkClientsConnection = () -> {
        synchronized (getServerLauncher().LOGIN_MUTEX) {
            for (User user : serverLauncher.getNicknames()) {
                if (user.isOnline()) {
                    try {
                        user.getPlayerInterface().ping();
                    } catch (RemoteException e) {
                        synchronized (serverLauncher.getLoginMutex()) {
                            try {
                                user.getPlayerInterface().notifyUserDisconnection(user.getUsername());
                            } catch (RemoteException e1) {
                                try {
                                    user.getPlayerInterface().notifyError(e);
                                } catch (RemoteException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            System.out.println("Connection with the client is down. " + user.getUsername());
                            getServerLauncher().disableUser(user);
                        }
                    }
                }
            }
        }

    };

    /**
     * Getter of Server launcher
     * @return server launcher
     */
    public ServerLauncher getServerLauncher() {
        return serverLauncher;
    }

    /**
     * Setter of the server launcher
     * @param serverLauncher server launcher
     */
    public void setServerLauncher(ServerLauncher serverLauncher) {
        this.serverLauncher = serverLauncher;
    }

    /**
     * Getter of the executor
     * @return the executor
     */
    public ScheduledExecutorService getExecutor() {
        return executor;
    }

    /**
     * Setter of the executor
     * @param executor the executor
     */
    public void setExecutor(ScheduledExecutorService executor) {
        this.executor = executor;
    }
}

