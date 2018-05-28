package it.polimi.ingsw.controller.RMIApi;

import it.polimi.ingsw.controller.Server.User;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote, Serializable {

    public String ping () throws RemoteException;
    public void notifyDisconnection(User user) throws  RemoteException;
    public void notifyReconnection(User user) throws  RemoteException;
    public void notifyConnection (String username) throws RemoteException;
    public void printaaa (String string) throws RemoteException;
    public void onRegister(String string) throws  RemoteException;
    public void setTurn(boolean isMyTurn) throws  RemoteException;
}
