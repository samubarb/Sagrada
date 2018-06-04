package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.other_elements.VColor;

public class VPrivateObjectiveCard extends VObjectiveCard{
    private VColor color;

    public VPrivateObjectiveCard(String name, VColor color) {
        super(name);
        this.color = color;
    }
}
