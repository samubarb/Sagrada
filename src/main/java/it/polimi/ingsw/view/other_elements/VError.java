package it.polimi.ingsw.view.other_elements;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public enum VError {
    ADIACENT_DICE ("Devi posizionare il dado ai bordi, oppure accanto ad un altro già presente."),
    BUSY_POSITION ("La posizione è già occupata."),
    FAVOR_TOKEN ("Non hai abbastanza Favor Token per usare la Tool Card."),
    FRAME_VALUE_AND_COLOR ("Non puoi posizionare il dado, devi rispettare i vincoli di adiacenza."),
    NUT_CHOSEN_WRONG ("Il dado selezionato non corrisponde."),
    WP_COLOR ("Non puoi posizionare il dado perché è presente un vincolo di colore."),
    WP_VALUE ("Non puoi posizionare il dado perché è presente un vincolo valore."),
    CONNECTION ("Problemi di connessione."),
    ILLEGAL_MOVE ("Mossa non valida.");

    private String errorMessage;

    VError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Errore: " + this.errorMessage + newline;
    }
}
