package it.polimi.ingsw.controller.RMIApi;

import it.polimi.ingsw.controller.Server.User;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.*;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote, Serializable {

    /**This metjod is used to verify if a player is connected yet
     * @return a string, no matter what it is
     * @throws RemoteException
     */
    public String ping () throws RemoteException;

    /**This method is used to notify players in game that a player has disconnected
     * @param user the user that has gone offline
     * @throws RemoteException
     */
    public void notifyDisconnection(User user) throws  RemoteException;

    public void notifyReconnection(String username) throws  RemoteException;

    /**This method is used to notify player already connected that a player has connected
     * @param username is te name of the player connected
     * @throws RemoteException
     */
    public void notifyConnection (String username) throws RemoteException;

    /**This method notify players the current player playing
     * @param username player playing's name
     * @throws RemoteException
     */
    public void notifyTurn (String username) throws RemoteException;

    /**This method is a general output
     * @param string string to output
     * @throws RemoteException
     */
    public void printaaa (String string) throws RemoteException;

    /**This method is a general print used during registration of a player
     * @param string string to be printed
     * @throws RemoteException
     */
    public void onRegister(String string) throws  RemoteException;

    /**This method set the turn to the current player
     * @param isMyTurn true if player has to play false otherwise
     * @throws RemoteException
     */
    public void setMyTurn(boolean isMyTurn) throws  RemoteException;

    /**This method asks the dice from the current dice to be placed
     * @return the index of the dice
     * @throws RemoteException
     */
    public int getDiceToBePlaced() throws  RemoteException;

    /**This method asks the final coordinates of the dice to be placed or moved
     * @return final coordinates of the dice
     * @throws RemoteException
     */
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
    //public void notifyEmpityRoundTrack() throws  RemoteException;
    public int chooseWindowPattern(WindowPattern windowPattern[]) throws RemoteException;
    public void notifyWinner() throws  RemoteException;
    public void notifyLoosers() throws RemoteException;
    public void notifyUserReconnection(String username) throws  RemoteException;
    public void notifyMaxPlayerReached() throws  RemoteException;
    public void notifyNameInUse() throws  RemoteException;
    public void notifyUserLogged(String username) throws  RemoteException;
    public void notifyGameStarted() throws  RemoteException;
    public void notifyUserDisconnection(String username) throws RemoteException;







}
