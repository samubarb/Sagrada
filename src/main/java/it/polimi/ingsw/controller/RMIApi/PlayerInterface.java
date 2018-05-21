package it.polimi.ingsw.controller.RMIApi;

import it.polimi.ingsw.controller.Server.User;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote, Serializable {

    public boolean ping () throws RemoteException;
    public void notifyDisconnection(User user) throws  RemoteException;
    public void printa (String string) throws RemoteException;
}
