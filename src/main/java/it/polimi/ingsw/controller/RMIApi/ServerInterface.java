package it.polimi.ingsw.controller.RMIApi;

import it.polimi.ingsw.model.Player;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ServerInterface extends Remote, Serializable {

    public int print (String stringa) throws RemoteException;
    public boolean register (Player clientPlayer, String stringa) throws RemoteException;
    public int getNumberOfPlayer() throws RemoteException;


}
