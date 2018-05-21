package it.polimi.ingsw.controller.RMIApi;

import it.polimi.ingsw.model.Player;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ServerInterface extends Remote, Serializable {

    public int print (String string) throws RemoteException;
    public boolean register (PlayerInterface clientPlayer, String stringa) throws RemoteException;
    public int getNumberOfPlayer() throws RemoteException;


}
