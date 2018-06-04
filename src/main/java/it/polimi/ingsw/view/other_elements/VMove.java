package it.polimi.ingsw.view.other_elements;

import it.polimi.ingsw.view.cards.VToolCard;
import it.polimi.ingsw.view.game_elements.VDice;

public class VMove {
    private VDice dice;
    private VCoordinates xy;
    private VToolCard tool;

    public VMove(VDice dice, VCoordinates xy, VToolCard tool) {
        this.dice = dice;
        this.xy = xy;
        this.tool = tool;
    }

    public VMove(VDice dice, VCoordinates xy) {
        this.dice = dice;
        this.xy = xy;
    }

    public VMove(VToolCard tool) {
        this.tool = tool;
    }
    public VDice getDice() {
        return dice;
    }
    public VCoordinates getXY() {
        return xy;
    }
    public VToolCard getTool() {
        return tool;
    }

    @Override
    public String toString() {
        return "Mossa:\n    dado " + this.dice.toString() + "\n    posizione " + this. xy.toString();
    }
}
