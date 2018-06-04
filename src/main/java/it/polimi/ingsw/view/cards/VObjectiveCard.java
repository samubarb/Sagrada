package it.polimi.ingsw.view.cards;

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
}
