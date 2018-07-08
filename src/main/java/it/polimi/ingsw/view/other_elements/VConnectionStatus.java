package it.polimi.ingsw.view.other_elements;

public enum VConnectionStatus {
    CONNECTED ("connesso."), DISCONNECTED ("disconnesso."), RECONNECTED ("riconnesso.");

    private String status;

    /**
     * represent the three states in which a client can be in regards of the server
     * @param status
     */
    VConnectionStatus (String status) {
        this.status = status;
    }

    /**
     * get the correnspondent string to be printed
     * @return
     */
    @Override
    public String toString() {
        return this.status;
    }
}
