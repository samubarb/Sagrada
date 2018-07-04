package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.other_elements.VColor;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static it.polimi.ingsw.inputoutput.IOManager.*;
import static javafx.scene.paint.Color.BLACK;

public class VDice {
    
    private static final String colorConstraint = "\u2593"; // used in VWindowPattern

    private int value;
    private VColor color;

    public VDice(int value, VColor color) {
        this.value = value;
        this.color = color;
    }

    public Color colorToGUI() {
        switch (this.color) {
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

    private StackPane full() {
        StackPane stack = new StackPane();
        stack.getChildren().add(empty());
        Circle dot = new Circle();
        dot.setRadius(dotRadius);
        dot.setFill(BLACK);
        stack.getChildren().add(dot);
        return stack;
    }

    private Rectangle empty() {
        Rectangle empty = new Rectangle();
        empty.setFill(colorToGUI());
        empty.setWidth(thirdOfCell);
        empty.setHeight(thirdOfCell);
        return empty;
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

    public GridPane toGUI() {
        GridPane face = new GridPane();

        switch(this.value) {
            case 1:
                face.add(empty(), 0, 0);
                face.add(empty(), 0, 1);
                face.add(empty(), 0, 2);
                face.add(empty(), 1, 0);
                face.add(full(), 1, 1); // center
                face.add(empty(), 1, 2);
                face.add(empty(), 2, 0);
                face.add(empty(), 2, 1);
                face.add(empty(), 2, 2);
                break;

            case 2:
                face.add(empty(), 0, 0);
                face.add(empty(), 0, 1);
                face.add(full(), 0, 2); //top right
                face.add(empty(), 1, 0);
                face.add(empty(), 1, 1);
                face.add(empty(), 1, 2);
                face.add(full(), 2, 0); // bottom left
                face.add(empty(), 2, 1);
                face.add(empty(), 2, 2);
                break;

            case 3:
                face.add(empty(), 0, 0);
                face.add(empty(), 0, 1);
                face.add(full(), 0, 2); //top right
                face.add(empty(), 1, 0);
                face.add(full(), 1, 1); // center
                face.add(empty(), 1, 2);
                face.add(full(), 2, 0); // bottom left
                face.add(empty(), 2, 1);
                face.add(empty(), 2, 2);
                break;

            case 4:
                face.add(full(), 0, 0); // top left
                face.add(empty(), 0, 1);
                face.add(full(), 0, 2); // top right
                face.add(empty(), 1, 0);
                face.add(empty(), 1, 1);
                face.add(empty(), 1, 2);
                face.add(full(), 2, 0); // bottom left
                face.add(empty(), 2, 1);
                face.add(full(), 2, 2); // bottom right
                break;

            case 5:
                face.add(full(), 0, 0); // top left
                face.add(empty(), 0, 1);
                face.add(full(), 0, 2); // top right
                face.add(empty(), 1, 0);
                face.add(full(), 1, 1); // center
                face.add(empty(), 1, 2);
                face.add(full(), 2, 0); // bottom left
                face.add(empty(), 2, 1);
                face.add(full(), 2, 2); // bottom right
                break;

            case 6:
                face.add(full(), 0, 0); // top left
                face.add(empty(), 0, 1);
                face.add(full(), 0, 2); // top right
                face.add(full(), 1, 0); // center left
                face.add(empty(), 1, 1);
                face.add(full(), 1, 2); // center right
                face.add(full(), 2, 0); // bottom left
                face.add(empty(), 2, 1);
                face.add(full(), 2, 2); // bottom right
                break;

            default:
                face.add(empty(), 0, 0);
                face.add(empty(), 0, 1);
                face.add(empty(), 0, 2);
                face.add(empty(), 1, 0);
                face.add(empty(), 1, 1);
                face.add(empty(), 1, 2);
                face.add(empty(), 2, 0);
                face.add(empty(), 2, 1);
                face.add(empty(), 2, 2);
                break;
        }
        return face;
    }
}
