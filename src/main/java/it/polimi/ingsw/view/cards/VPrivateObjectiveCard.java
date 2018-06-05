package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.other_elements.VColor;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public class VPrivateObjectiveCard extends VObjectiveCard{
    private VColor color;

    public VPrivateObjectiveCard(String name, VColor color) {
        super(name);
        this.color = color;
    }

    @Override
    public String toString() {
        return this.color + super.getName() + VColor.RESET + newline + super.getDescription();
    }
}
