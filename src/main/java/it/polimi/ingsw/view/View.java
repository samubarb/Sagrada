package it.polimi.ingsw.view;

import it.polimi.ingsw.view.exceptions.UsernameTooShortException;

public interface View {

    void updateState(VGame game);


    /* Server to Client requests */
    void setTurn(VPlayer player);

    /* Moves handling */
    VMove askMove(VPlayer player); // use by the server to interrogate the client and let the user input his move
    VMove reAskMove(VPlayer player); // in case of a bad placement, send an error message and re-ask a new move

    // new user to sign in
    String askNewUsername() throws UsernameTooShortException;
    String askNewPassword();
    String chooseAnotherUsername(String user /*username already taken*/) throws UsernameTooShortException; // if the username already exists let the player choose another one

    // old user to log in
    String askUsername();
    String askPassword();
    void loggedIn(String player); // invoked by the server to permit login
}
