package it.polimi.ingsw.view.cards;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public class VObjectiveCard {
    private String name;
    private String description;

    public VObjectiveCard(String name) {
        this.name = name;
        this.description = setDescription(name);
    }

    private String setDescription(String cardName) { // Take it from a JSON file depending on the card name
        return "Description here";
    }

    protected String getName() { return this.name; }
    protected String getDescription() { return this.description; }

    @Override
    public String toString() {
        return this.name + newline + this.description;
    }
}
