package it.polimi.ingsw.controller.Server.Rmi;

import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.RMIApi.ServerInterface;
import it.polimi.ingsw.controller.Server.ServerLauncher;

import java.rmi.RemoteException;


public class ServerInterfaceImpl implements ServerInterface {


    /**
     * Server launcher
     */
    private ServerLauncher serverLauncher;


    /**
     * General output printer used for debigging
     * @param stringa the string to be displayed
     * @return always 1
     * @throws RemoteException Connection problem
     */
    @Override
    public int print(String stringa) throws RemoteException{
        System.out.println(stringa);
        return 1;
    }

    /**
     * @param clientPlayer represents the interface of the player
     * @param username represents the username of the player that wants to login
     * @return false if the register fails, true otherwise
     * @throws RemoteException Connection problem
     */
    @Override
    public boolean register(PlayerInterface clientPlayer, String username) throws RemoteException {

        return serverLauncher.registerUser(clientPlayer, username);
    }


    /**
     * Setter of server launcher
     * @param serverLauncher server launcher
     */
    public void setServerLauncher(ServerLauncher serverLauncher) {
        this.serverLauncher = serverLauncher;
    }

    /**
     * Getter of server launcher
     * @return server launcher
     */
    public ServerLauncher getServerLauncher() {
        return serverLauncher;
    }

}
