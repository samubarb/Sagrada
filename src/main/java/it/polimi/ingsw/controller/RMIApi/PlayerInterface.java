package it.polimi.ingsw.controller.RMIApi;

import it.polimi.ingsw.controller.Server.User;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.*;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote, Serializable {

    /**
     * This metjod is used to verify if a player is connected yet
     * @return a string, no matter what it is
     * @throws RemoteException Connection problem
     */
    public String ping () throws RemoteException;

    /**
     * This method is used to notify players in game that a player has disconnected
     * @param user the user that has gone offline
     * @throws RemoteException Connection problem
     */
    public void notifyDisconnection(User user) throws  RemoteException;

    /**
     * This method is used to notify players in game that a player has reconnected
     * @param username player username
     * @throws RemoteException Connection problem
     */
    public void notifyReconnection(String username) throws  RemoteException;

    /**
     * This method is used to notify player already connected that a player has connected
     * @param username is te name of the player connected
     * @throws RemoteException Connection problem
     */
    public void notifyConnection (String username) throws RemoteException;

    /**
     * This method notify players the current player playing
     * @param username player playing's name
     * @throws RemoteException Connection problem
     */
    public void notifyTurn (String username) throws RemoteException;

    /**
     * This method is a general output
     * @param string string to output
     * @throws RemoteException Connection problem
     */
    public void printaaa (String string) throws RemoteException;

    /**
     * This method is a general print used during registration of a player
     * @param string string to be printed
     * @throws RemoteException Connection problem
     */
    public void onRegister(String string) throws  RemoteException;

    /**
     * This method set the turn to the current player
     * @param isMyTurn true if player has to play false otherwise
     * @throws RemoteException Connection problem
     */
    public void setMyTurn(boolean isMyTurn) throws  RemoteException;

    /**
     * This method asks the dice from the current dice to be placed
     * @return the index of the dice
     * @throws RemoteException Connection problem
     */
    public int getDiceToBePlaced() throws  RemoteException;

    /**
     * This method asks the final coordinates of the dice to be placed or moved
     * @return final coordinates of the dice
     * @throws RemoteException Connection problem
     */
    public Coordinates getDiceFinalPosition() throws RemoteException;

    /**
     * Sets the game on the client side, it also visualize the board on the client side
     * @param game game that has to be set
     * @throws RemoteException Connection problem
     */
    public void setClientGame(Game game) throws  RemoteException;

    /**
     * Set the game on the client side but it doesn't show the board
     * @param game game that has to be set
     * @throws RemoteException Connection problem
     */
    public void setClientGameHide(Game game) throws  RemoteException;

    /**
     * Method that gets which type of moves the player wants to play
     * @return 1 place dice, 2 use toolcard, 3 end turn
     * @throws RemoteException Connection problem
     */
    public int getMoves() throws  RemoteException;

    /**
     * Method that gets the toolcad that the player want to use
     * @return an integer from 0 to 11 that represents the toolcard
     * @throws RemoteException Connection problem
     */
    public int getToolcard() throws  RemoteException;



    /**
     * Asks the player which dice he wants to pick up from the current dice
     * @return an integer from 0 to 2*numberOfPlayer representing the index of the dice
     * @throws RemoteException Connection problem
     */
    public int getDiceFromReserve() throws  RemoteException;

    /**
     * Asks the player the type of action, used for a toolcard, it can increase or decrease
     * @return increase or decrease
     * @throws RemoteException Connection problem
     */
    public Action getTypeOfAction() throws  RemoteException;

    /**
     * Asks the player the coordinates of the dice he wants to move
     * @return initials dice coordinates
     * @throws RemoteException Connection problem
     */
    public Coordinates getDiceToBeMoved() throws RemoteException;

    /**
     * Asks the player where he wants to put the dice
     * @return final coordinates of the dice
     * @throws RemoteException Connection problem
     */
    public Coordinates getDiceDestination() throws RemoteException;

    /**
     * Asks the player the coordinates of the dice he wants to move, it has a parameter that represents the ordinal numer of the dice
     * Used by toolcards that move more than on dice
     * @param i ordinal number
     * @return initials coordinates of the dice
     * @throws RemoteException Connection problem
     */
    public Coordinates getDiceToBeMoved(int i) throws RemoteException;

    /**
     * Asks the player where he wants to put the dice, it has a parameter that represents the ordinal numer of the dice
     * Used by toolcards that move more than on dice
     * @param i ordinal number
     * @return final coordinates of the dice
     * @throws RemoteException Connection problem
     */
    public Coordinates getDiceDestination(int i) throws RemoteException;

    /**
     * Asks the player  which dice on the round track he wants to swap
     * @return the coordinates of the round trac's dice
     * @throws RemoteException Connection problem
     */
    public Coordinates getRoundDiceToBeSwapped() throws RemoteException;


    /**
     * Asks the player the value of a dice
     * @param dice the dice used to represents its colour
     * @return an integer representing the value of the dice
     * @throws RemoteException Connection problem
     */
    public int getDiceValue(Dice dice) throws RemoteException;

    /**
     * Displays on the client side the final scoreboard
     * @throws RemoteException Connection problem
     */
    public void notifyScoreBoard() throws  RemoteException;

    /**
     * Displays on the client side the error
     * @param e Connection problem
     * @throws RemoteException Connection problem
     */
    public void notifyError(RemoteException e) throws  RemoteException;

    /**
     * Displays on the client side the error
     * @param e Not enough favor token to use a tool card
     * @throws RemoteException Connection Problem
     */
    public void notifyError(FavorTokenException e) throws  RemoteException;

    /**
     * Displays on the client side the error
     * @param e dice choosen from the current dice is not available
     * @throws RemoteException Connection Problem
     */
    public void notifyError(NutChosenWrongException e) throws  RemoteException;

    /**
     * Displays on the client side the error
     * @param e the input of the toolcard doest't respect the input restraint, reinsert it
     * @throws RemoteException Connection Problem
     */
    public void notifyError(IllegalArgumentException e) throws  RemoteException;

    /**
     * Displays on the client side the error
     * @param e Problem with a color restriction in the window pattern card
     * @throws RemoteException Connection Problem
     */
    public void notifyError(WindowPatternColorException e) throws  RemoteException;

    /**
     * Displays on the client side the error
     * @param e Problem with a value restriction in the window pattern card
     * @throws RemoteException Connection Problem
     */
    public void notifyError(WindowPatternValueException e) throws  RemoteException;

    /**
     * Displays on the client side the error
     * @param e Problem with and adjacent restriction, it can be value or color, in the frame
     * @throws RemoteException Connection Problem
     */
    public void notifyError(FrameValueAndColorException e) throws  RemoteException;

    /**
     * Displays on the client side the error
     * @param e the final position of the dice is busy
     * @throws RemoteException Connection Problem
     */
    public void notifyError(BusyPositionException e) throws  RemoteException;

    /**
     * Displays on the client side the error
     * @param e The dice is not adjacentto any other dice
     * @throws RemoteException Connection Problem
     */
    public void notifyError(AdjacentDiceException e) throws  RemoteException;

    /**
     * Displays on the client side the window pattern from which he can choose
     * @param windowPattern array of the player's window pattern from which he can choose
     * @return an int representing the window pattern, from 0 to 3
     * @throws RemoteException Connection Problem
     */
    public int chooseWindowPattern(WindowPattern windowPattern[]) throws RemoteException;

    /**
     * Notify the player that he is the winner
     * @throws RemoteException Connection Problem
     */
    public void notifyWinner() throws  RemoteException;

    /**
     * Notify the player that he is a looser
     * @throws RemoteException Connection Problem
     */
    public void notifyLoosers() throws RemoteException;

    /**
     * Notify the player that an user has reconnected
     * @param username username of the reconnected user
     * @throws RemoteException Connection Problem
     */
    public void notifyUserReconnection(String username) throws  RemoteException;

    /**
     * Notify the player that re room is full
     * @throws RemoteException Connection Problem
     */
    public void notifyMaxPlayerReached() throws  RemoteException;

    /**
     * Notify the player that the coosen name is already in use
     * @throws RemoteException Connection Problem
     */
    public void notifyNameInUse() throws  RemoteException;

    /**
     * Notify that a player has logged successfully
     * @param username username of the player logged
     * @throws RemoteException Connection Problem
     */
    public void notifyUserLogged(String username) throws  RemoteException;

    /**
     * Notify player that the game has started
     * @throws RemoteException Connection Problem
     */
    public void notifyGameStarted() throws  RemoteException;

    /**
     * Notify that a player has disconnected
     * @param username username of the disconnected player
     * @throws RemoteException Connection Problem
     */
    public void notifyUserDisconnection(String username) throws RemoteException;







}
