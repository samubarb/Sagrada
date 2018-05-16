package it.polimi.ingsw.controller.Server;

import it.polimi.ingsw.controller.RMIApi.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class StartServer {

        /**
         * RMI server.
         */
        private static ServerInterfaceImpl RMIServer;
        private static final int RMIServerPort = 1098;

        private void StartServer() throws RemoteException {
            RMIServer = new ServerInterfaceImpl();
        }

        public static void main(String[] args) throws RemoteException {
            /*if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }*/
            // First, create the real object which will do the requested function.
            ServerInterface implementation = new ServerInterfaceImpl();

            try {
                // Export the object.
                ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(implementation, 1090);
                Registry registry = LocateRegistry.createRegistry(1090);

                registry.rebind("ServerInterface", stub);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            }
            System.out.println("Bound!");
            System.out.println("Server will wait forever for messages.");
        }
    }

