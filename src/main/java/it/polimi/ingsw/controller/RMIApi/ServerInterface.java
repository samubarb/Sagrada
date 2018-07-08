package it.polimi.ingsw.controller.RMIApi;


import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ServerInterface extends Remote, Serializable {

    /**
     * Generic print used for debugging
     * @param string string to be printed
     * @return an integer representing the good or bad esecution of the method
     * @throws RemoteException Connection problem
     */
    public int print (String string) throws RemoteException;

    /**
     * This method permits client to register on the server
     * @param clientPlayer represents the interface of the player
     * @param stringa represents the name of the player
     * @return false if register fails, true otherwise
     * @throws RemoteException Connection problem
     */
    public boolean register (PlayerInterface clientPlayer, String stringa) throws RemoteException;




}
