package it.polimi.ingsw.view;

import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.game_elements.VGame;
import it.polimi.ingsw.view.game_elements.VWindowPatterns;
import it.polimi.ingsw.view.other_elements.VConnectionStatus;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import it.polimi.ingsw.view.other_elements.VError;

public interface View {
    // Methods to ask something to the Client

    /**
     * guide the player in picking up a dice from the CurrentDice[]
     * @return int index
     */
    int askDice();

    /**
     * guide the player in inputting coordinates on where to put the previews selected dice
     * @return legal coordinates inside the grid
     */
    VCoordinates askCoordinates();

    /**
     * guide the player in inputting coordinates for multiple dices
     * @param i progressive number of asked dice
     * @return legal coordinates inside the grid
     */
    VCoordinates askCoordinates(int i);

    /**
     * guide the player choosing his next move
     * @return 1 for a dice placing, 2 for use a toolcard, 3 to pass the turn
     */
    int askMove();

    /**
     * guide the player in the choice between 4 window pattern cards
     * @param wpCards the windows pattern cards set
     * @return indes of the chosen one
     */
    int askWindowPattern(VWindowPatterns wpCards);

    /**
     * guide the player in the choice of which tool card use
     * @return the chosen toolcard
     */
    int askToolCard();

    /**
     * ask if the player want to increase or decrease a dice value
     * @return true for increase, false for decrease, use with booleanToAction() adapter
     */
    boolean askAction();

    /**
     * guide the player in choosing a dice from the round track
     * @return index of a dice in the roundtrack
     */
    int askToPickFromRoundTrack();

    /**
     * asks the user with which number wants to re-place a specific dice
     * @param dice dice on which apply value change
     * @return new value chosen
     */
    int askDiceNumber(VDice dice);

    // Methods to notify something to the Client

    /**
     * notify the connection status to the client
     * @param userName username whose connection status needs to be notified
     * @param status CONNECTED, DISCONNECTED, RECONNECTED
     */
    void notifyConnectionStatus(String userName, VConnectionStatus status);

    /**
     * notify a welcome message to the player
     */
    void notifyGreetings();

    /**
     * notify an error message to the player, due to a wrong action in game or a game constraint violated
     * @param error from VError enum
     */
    void notifyError(VError error);

    /**
     * show the final ranking at the end of the game
     */
    void notifyScore();

    /**
     * notify the victory to the winner
     */
    void notifyWin();

    /**
     * notify the loose to the losers
     */
    void notifyLose();

    /**
     * update the elements in the game
     * @param game new VGame
     */
    void updateState(VGame game);

    /**
     * show a beautiful splash screen at the begining of the game
     */
    void splash();
}
