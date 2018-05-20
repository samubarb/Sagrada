package it.polimi.ingsw.controller.Server;

import it.polimi.ingsw.controller.RMIApi.ServerInterface;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class rmiStartServer {

        /**
         * RMI server.
         */
        private static ServerInterfaceImpl RMIServer;
        private static final int RMIServerPort = 1098;

        public void StartServer() throws RemoteException {
            RMIServer = new ServerInterfaceImpl();
        }

        public static void main(String[] args) throws RemoteException {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            // First, create the real object which will do the requested function.
            ServerInterface implementation = new ServerInterfaceImpl();

            try {
                //System.setProperty("java.rmi.server.hostname","192.168.1.2");

                // Export the object.
                ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(implementation, 1099);
                Registry registry = LocateRegistry.createRegistry(1099);
                //Registry registry = LocateRegistry.getRegistry();
                //registry.bind("ServerInterface", stub);

                registry.rebind("ServerInterface", stub);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Bound!");
            System.out.println("Server will wait forever for messages.");
        }
    }

