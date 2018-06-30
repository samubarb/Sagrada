package it.polimi.ingsw.controller.RMIApi;

import it.polimi.ingsw.controller.Server.User;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.*;

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
    public void setClientGameHide(Game game) throws  RemoteException;
    public void printPlayersFrame() throws  RemoteException;
    public int getMoves() throws  RemoteException;
    public int getToolcard() throws  RemoteException;



    /**
     * Richiede un dado dalla riserva di dadi (currentDice)
     * @return
     * @throws RemoteException
     */
    public int getDiceFromReserve() throws  RemoteException;

    /**
     * Richiede il tipo di azione ( incremento o decremento)
     * @return
     * @throws RemoteException
     */
    public Action getTypeOfAction() throws  RemoteException;

    /**
     * Richiede il dado del frame da spostare
     * @return
     * @throws RemoteException
     */
    public Coordinates getDiceToBeMoved() throws RemoteException;

    /**
     * Richiede la posizione finale nel frame
     * @return
     * @throws RemoteException
     */
    public Coordinates getDiceDestination() throws RemoteException;

    /**
     * overloading del metodo sopra citato la i indica se il primo o il secondo dado
     * @param i ordinal number
     * @return
     * @throws RemoteException
     */
    public Coordinates getDiceToBeMoved(int i) throws RemoteException;

    /**
     * come sopra
     * @param i
     * @return
     * @throws RemoteException
     */
    public Coordinates getDiceDestination(int i) throws RemoteException;

    /**
     * chiede un dado dal tracciato dei round sarà da settare solo la x delle coordinate
     * @return
     * @throws RemoteException
     */
    public Coordinates getRoundDiceToBeSwapped() throws RemoteException;

    /**
     * serve per chiedere all'utente se vuole piazzare il dado dopo che è stato tirato nuovamente
     * @return
     * @throws RemoteException
     */
    public boolean doYouWantToPlace() throws RemoteException;

    public int getDiceValue(Dice dice) throws RemoteException;
    public void notifyScoreBoard() throws  RemoteException;

    public void notifyError(RemoteException e) throws  RemoteException;
    public void notifyError(FavorTokenException e) throws  RemoteException;
    public void notifyError(NutChosenWrongException e) throws  RemoteException;
    public void notifyError(IllegalArgumentException e) throws  RemoteException;
    public void notifyError(WindowPatternColorException e) throws  RemoteException;
    public void notifyError(WindowPatternValueException e) throws  RemoteException;
    public void notifyError(FrameValueAndColorException e) throws  RemoteException;
    public void notifyError(BusyPositionException e) throws  RemoteException;
    public void notifyError(AdjacentDiceException e) throws  RemoteException;
    public int chooseWindowPattern(WindowPattern windowPattern[]) throws RemoteException;
    public void notifyWinner() throws  RemoteException;
    public void notifyLoosers() throws RemoteException;






}
