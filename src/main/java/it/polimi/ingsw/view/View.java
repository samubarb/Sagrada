package it.polimi.ingsw.view;

import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.view.cards.VToolCard;
import it.polimi.ingsw.view.cards.VWindowPattern;
import it.polimi.ingsw.view.exceptions.UsernameTooShortException;
import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.game_elements.VGame;
import it.polimi.ingsw.view.game_elements.VPlayer;
import it.polimi.ingsw.view.other_elements.VCoordinates;

public interface View {

    // Methods to ask something to the Client
    int askDice();
    int askDice(int i); // as askDice(), but personalized in case of multiple dice choose
    VCoordinates askCoordinates();
    VCoordinates askCoordinates(int i); // as askCoordinates, but personalized in case of multiple dice choose
    int askMove(); // returns 1 for a dice placing, 2 for use a toolcard, 3 to pass the turn
    int askWindowPattern(); // returns the position of the used card
    int askToolCard(); // tool cards are taken from Game
    boolean askAction(); // true for increase, false for decrease, use with booleanToAction() adapter
    int askToPickFromRoundTrack();
    boolean askConfirmDice(VDice dice);

    // Methods to notify something to the Client
    void updateState(VGame game);

    // new user to sign in
    String askNewUsername() throws UsernameTooShortException;
    String askNewPassword();
    String chooseAnotherUsername(String user /*username already taken*/) throws UsernameTooShortException; // if the username already exists let the player choose another one

    // DEPRECATED
    /*
    void setTurn(VPlayer player);
    VMove askMove(VPlayer player); // use by the server to interrogate the client and let the user input his move
    VMove reAskMove(VPlayer player); // in case of a bad placement, send an error message and re-ask a new move
    String askUsername();
    String askPassword();
    void loggedIn(String player); // invoked by the server to permit login
     */


    /*
    // chiede se va bene dopo il reroll

    gestione errore
    nomedelmetodo(Errore);

    una print che visualizza i punteggi void printPunteggi(array[]); o cose simili
    una print per visualizzare al vincitore HAI VINTO void youWin();
    */
}
