package it.polimi.ingsw.view;

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
}
