package it.polimi.ingsw.controller.client;


import it.polimi.ingsw.controller.client.rmiClient.RMIClientLauncher;


import java.io.Serializable;


public class ClientLauncher implements Serializable {
    /**
     * Represents the generic client launcher
     */
    private static ClientLauncher clientLauncher;
    /**
     * Represent the rmi client launcher
     */
    private static RMIClientLauncher rmiClientLauncher;




    /**
     * This method starts the client
     * @param args
     */
    public static void main(String[] args) {
        clientLauncher = new ClientLauncher();
        rmiClientLauncher = new RMIClientLauncher();
        rmiClientLauncher.startRMIClient();

    }
}

