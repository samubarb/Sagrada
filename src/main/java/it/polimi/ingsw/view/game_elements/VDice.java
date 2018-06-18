package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.other_elements.VColor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VDice {
    // U+2680 to U+2685
    public static final String[] faces = {
            "\u2680",
            "\u2681",
            "\u2682",
            "\u2683",
            "\u2684",
            "\u2685"
    };

    private static final String colorConstraint = "\u2593"; // used in VWindowPattern

    private int value;
    private VColor color;

    public VDice(int value, VColor color) {
        this.value = value;
        this.color = color;
    }

    public Color colorToGUI(VColor vc) {
        switch (vc) {
            case RED:
                return Color.RED;
            case YELLOW:
                return Color.YELLOW;
            case PURPLE:
                return Color.PURPLE;
            case BLUE:
                return Color.BLUE;
            case GREEN:
                return Color.GREEN;
            default:
                return Color.TRANSPARENT;
        }
    }

    public int getValue() {
        return this.value;
    }

    public VColor getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        if (this.value == 0)
            return this.color.toString() + "[" + colorConstraint + "]" + this.color.RESET;
        return this.color.toString() + "[" + this.value + "]" + this.color.RESET;
    }

    public Label toGUI() {
        Label dice = new Label(this.color.toString());
        
        //dice.setStyle();
        return dice;
    }
}
