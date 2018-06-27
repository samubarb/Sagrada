package it.polimi.ingsw.view;

import it.polimi.ingsw.view.exceptions.UsernameTooShortException;
import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.game_elements.VGame;
import it.polimi.ingsw.view.game_elements.VWindowPatterns;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import it.polimi.ingsw.view.other_elements.VError;

public interface View {
    // Methods to ask something to the Client
    int askDice(); // ask the user which dice wants to pick up from the CurrentDice[]
    VCoordinates askCoordinates(); // ask the player where to put the previews selected dice
    VCoordinates askCoordinates(int i); // as askCoordinates, but personalized in case of multiple dice choose
    int askMove(); // returns 1 for a dice placing, 2 for use a toolcard, 3 to pass the turn
    int askWindowPattern(VWindowPatterns wpCards); // returns the position of the used card
    int askToolCard(); // tool cards are taken from Game
    boolean askAction(); // true for increase, false for decrease, use with booleanToAction() adapter
    int askToPickFromRoundTrack(); // effect of a toolcard: you can take a dice

    int askDiceNumber(VDice dice); // asks the user with which number wants to re-place a specific dice

    // Methods to notify something to the Client
    void notifyError(VError error); // used to notify and guide the player after a wrong move
    void notifyScore(); // show the final ranking at the end of the game
    void notifyWin(); // notify the victory to the winner
    void notifyLose(); // notify the loose to the loosers
    void updateState(VGame game); // transfer all the changes from the model to the view
    void splash(); // show a beautiful splash screen at the begining of the game
    String askNewUsername() throws UsernameTooShortException; // ask the user to choose a nickname for the game
    String chooseAnotherUsername(String user /*username already taken*/) throws UsernameTooShortException; // if the username already exists let the player choose another one

    // DEPRECATED
    /*
    String askNewPassword(); // ask the user the password to login validation
    void setTurn(VPlayer player);
    VMove askMove(VPlayer player); // use by the server to interrogate the client and let the user input his move
    VMove reAskMove(VPlayer player); // in case of a bad placement, send an error message and re-ask a new move
    String askUsername();
    String askPassword();
    void loggedIn(String player); // invoked by the server to permit login
     */

    /* NEED TO IMPLEMENT
     - show favor tokens of WPcards
     - descrizioni delle carte obiettivo pubblico
     - mostra carte obiettivo privato di ogni player
     - fix the always FALSE returning in the CLI men
     - svuota buffer prima di chiedere input di qualsiasi tipo
    */
}
