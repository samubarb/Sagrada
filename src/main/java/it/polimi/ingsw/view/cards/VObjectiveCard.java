package it.polimi.ingsw.view.cards;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public abstract class VObjectiveCard {
    private String name;

    /**
     * represents a generic objective card to be shown
     * @param name card name
     */
    public VObjectiveCard(String name) {
        this.name = name;
    }

    /**
     * get the card name
     * @return the card name String
     */
    protected String getName() { return this.name; }

    /**
     * get the card description / usage
     * @return the description String
     */
    protected abstract String getDescription();

    /**
     * return the description to be formatted in CLI
     * @return
     */
    @Override
    public String toString() {
        return newline + this.getDescription();
    }


}
