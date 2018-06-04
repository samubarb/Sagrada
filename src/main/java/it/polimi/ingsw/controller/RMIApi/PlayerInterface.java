package it.polimi.ingsw.controller.RMIApi;

import it.polimi.ingsw.controller.Server.User;
import it.polimi.ingsw.model.Action;
import it.polimi.ingsw.model.Coordinates;
import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Game;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote, Serializable {

    public String ping () throws RemoteException;
    public void notifyDisconnection(User user) throws  RemoteException;
    public void notifyReconnection(User user) throws  RemoteException;
    public void notifyConnection (String username) throws RemoteException;
    public void notifyTurn (String username) throws RemoteException;
    public void printaaa (String string) throws RemoteException;
    public void onRegister(String string) throws  RemoteException;
    public void setMyTurn(boolean isMyTurn) throws  RemoteException;
    public int getDiceToBePlaced() throws  RemoteException;
    public Coordinates getDiceFinalPosition() throws RemoteException;
    public void setClientGame(Game game) throws  RemoteException;
    public void printPlayersFrame() throws  RemoteException;
    public int getMoves() throws  RemoteException;
    public int getToolcard() throws  RemoteException;

    public int getDiceFromReserve() throws  RemoteException;
    public Action getTypeOfAction() throws  RemoteException;
    public Coordinates getDiceToBeMoved() throws RemoteException;
    public Coordinates getDiceDestination() throws RemoteException;
    public Coordinates getDiceToBeMoved(int i) throws RemoteException;
    public Coordinates getDiceDestination(int i) throws RemoteException;
    public Coordinates getRoundDiceToBeSwapped() throws RemoteException;
    public boolean doYouWantToPlace() throws RemoteException;




}
