package it.polimi.ingsw.view.other_elements;

public enum VConnectionStatus {
    CONNECTED ("connesso."), DISCONNECTED ("disconnesso."), RECONNECTED ("riconnesso.");

    private String status;

    VConnectionStatus (String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
