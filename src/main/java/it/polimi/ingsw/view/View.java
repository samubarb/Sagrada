package it.polimi.ingsw.view;

import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.view.cards.VToolCard;
import it.polimi.ingsw.view.cards.VWindowPattern;
import it.polimi.ingsw.view.exceptions.UsernameTooShortException;
import it.polimi.ingsw.view.game_elements.VGame;
import it.polimi.ingsw.view.game_elements.VPlayer;
import it.polimi.ingsw.view.other_elements.VCoordinates;

public interface View {

    void updateState(VGame game);


    /* Server to Client requests */
    void setTurn(VPlayer player);

    /* Moves handling */
    // VMove askMove(VPlayer player); // use by the server to interrogate the client and let the user input his move
    // VMove reAskMove(VPlayer player); // in case of a bad placement, send an error message and re-ask a new move

    int askDice();
    VCoordinates askCoordinates();

    int askWindowPattern(); // returns the position of the used card
    int askToolCard(); // tool cards are taken from Game

    // new user to sign in
    String askNewUsername() throws UsernameTooShortException;
    String askNewPassword();
    String chooseAnotherUsername(String user /*username already taken*/) throws UsernameTooShortException; // if the username already exists let the player choose another one

    // old user to log in
    String askUsername();
    String askPassword();
    void loggedIn(String player); // invoked by the server to permit login



    /*
    Mosse che mi servono by teo

    Coordinates askCoordinates(int i);
    Action askaction();
    Delle visual per la scelta dei windowPattern
    delle visual per la scelta del tipo di grafica
    delle visual per il tipo di connsessione
    per visual intendo scegli tra queste 4 pattern
        -1
        -2
        -3
        -4
        e mi ritorna la pattern scelta
        lo stesso vale per i due tipi di grafica e i due tipi di connessione
    una visual che possa scegliere se:
        -piazzare dado
        -usare carta tool
        -non fare nulla
    piazzare dado è già fatto, usare carta tool vorrrà dire chiedere quale carta
    Per identificare la carta tool mi devi ritornare un intero, così non c'è bisogno di adapter vari
    gestione errore
    nomedelmetodo(Errore);
    una print che visualizza i punteggi void printPunteggi(array[]); o cose simili
    una print per visualizzare al vincitore HAI VINTO void youWin();
    Le classi ho messo quelle del model
    se ti servono quelle del view mi servono anche i metodi dell'adapter
    */
}
