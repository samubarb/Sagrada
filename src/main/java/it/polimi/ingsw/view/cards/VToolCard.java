package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.other_elements.VColor;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public class VToolCard {
    private String name;
    private String description;
    private VColor color;


    public VToolCard(String name, VColor color) {
        this.name = name;
        this.color = color;
        this.description = setDescription();
    }

    private String setDescription() {
        return "Description here";
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public VColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(this.color).append(this.name).append(VColor.RESET).append(newline).
                append(this.description).append(newline);
        return string.toString();
    }
}
