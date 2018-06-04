package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.other_elements.VColor;

public class VToolCard {
    private String name;
    private String description;
    private VColor color;


    public VToolCard(String name, VColor color) {
        this.name = name;
        this.color = color;
    }
}
