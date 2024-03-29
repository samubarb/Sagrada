package it.polimi.ingsw.view.other_elements;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public enum VError {
    // In game errors
    ADIACENT_DICE ("Devi posizionare il dado ai bordi, oppure accanto ad un altro già presente."),
    BUSY_POSITION ("La posizione è già occupata."),
    FAVOR_TOKEN ("Non hai abbastanza Favor Token per usare la Tool Card."),
    FRAME_VALUE_AND_COLOR ("Non puoi posizionare il dado, devi rispettare i vincoli di adiacenza."),
    NUT_CHOSEN_WRONG ("Il dado selezionato non corrisponde."),
    WP_COLOR ("Non puoi posizionare il dado perché è presente un vincolo di colore."),
    WP_VALUE ("Non puoi posizionare il dado perché è presente un vincolo valore."),
    CONNECTION ("Problemi di connessione."),
    ILLEGAL_MOVE ("Mossa non valida."),

    // Server side errors
    GAME_FULL ("Numero massimo di giocatori raggiunto."),
    GAME_ALREADY_STARTED ("Partita già cominciata in corso."),
    USERNAME_DUPLICATE ("Il nome scelto è già in uso. Scegli un altro nome utente: ");


    private String errorMessage;

    /**
     * represents all the game rule or constraint, used to notify a wrong action to the player
     * @param errorMessage message to be notified
     */
    VError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * get the message to be notified
     * @return a message String
     */
    @Override
    public String toString() {
        return "Errore: " + this.errorMessage + newline;
    }
}
