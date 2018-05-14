package it.polimi.ingsw.controller.ProvaServer;

import it.polimi.ingsw.model.Player;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ServerInterface extends Remote, Serializable {

    public int print (String stringa) throws RemoteException;
    public Boolean register (Player clientPlayer, String stringa) throws RemoteException;


}
