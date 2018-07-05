package it.polimi.ingsw.view.cards;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public abstract class VObjectiveCard {
    private String name;

    public VObjectiveCard(String name) {
        this.name = name;
    }

    protected String getName() { return this.name; }
    protected abstract String getDescription();

    @Override
    public String toString() {
        return newline + this.getDescription();
    }


}
