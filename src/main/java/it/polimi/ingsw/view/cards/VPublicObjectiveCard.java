package it.polimi.ingsw.view.cards;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public class VPublicObjectiveCard extends VObjectiveCard{
    private int points;

    public VPublicObjectiveCard(String name, int points) {
        super(name);
        this.points = points;
    }

    @Override
    public String toString() {
        return super.getName() + "Punti: " + points + newline + super.getDescription();
    }
}
