package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.other_elements.VColor;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static it.polimi.ingsw.inputoutput.IOManager.*;
import static javafx.scene.paint.Color.BLACK;

public class VDice {

    private static final String colorConstraint = "\u2593"; // used in VWindowPattern

    private int value;
    private VColor color;
    private boolean action;

    /**
     * represents the face of a dice
     * @param value int number value, from 1 to 6
     * @param color VColor the color of the dice
     */
    public VDice(int value, VColor color) {
        this.value = value;
        this.color = color;
        this.action = false;
    }

    /**
     * constructor to build a blank dice
     */
    public VDice() {
        this.value = 0;
        this.color = VColor.RESET;
        this.action = false;
    }

    /**
     * create and get a dice face for the GUI
     * @return StackPane, color + number
     */
    private StackPane full() {
        StackPane stack = new StackPane();
        stack.getChildren().add(empty());
        Circle dot = new Circle();
        dot.setRadius(dotRadius);
        dot.setFill(BLACK);
        stack.getChildren().add(dot);
        return stack;
    }

    /**
     * create and get an "empty" dice, to signal the non-presence of a dice
     * @return Rectangle
     */
    private Rectangle empty() {
        Rectangle empty = new Rectangle();
        empty.setFill(colorToGUI(this.color));
        empty.setWidth(thirdOfCell);
        empty.setHeight(thirdOfCell);
        return empty;
    }

    /**
     * get the dice value
     * @return int between 1 and 6
     */
    public int getValue() {
        return this.value;
    }

    /**
     * get the dice color
     * @return VColor enum object
     */
    public VColor getColor() {
        return this.color;
    }

    /**
     * get the formatted dice face to be shown in the CLI
     * @return String printable
     */
    @Override
    public String toString() {
        if (this.value == 0 && this.color.equals(VColor.RESET))
            return "   ";
        if (this.value == 0)
            return this.color.toString() + "[" + colorConstraint + "]" + this.color.RESET;
        return this.color.toString() + "[" + this.value + "]" + this.color.RESET;
    }

    /**
     * get the dice face to be shown in the GUI
     * @return GridPane with the dice face
     */
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
                face.add(full(), 0, 0); // first column
                face.add(full(), 0, 1);
                face.add(full(), 0, 2);
                face.add(empty(), 1, 0);
                face.add(empty(), 1, 1);
                face.add(empty(), 1, 2);
                face.add(full(), 2, 0); // second column
                face.add(full(), 2, 1);
                face.add(full(), 2, 2);
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

        face.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(thinPadding))));

        face.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                action = true;
            }
        });

        return face;
    }

    /**
     * get the "empty" dice, representing and empty slot for a dice
     * @return GridPane with a slot
     */
    public GridPane slotDice() {
        GridPane face = new GridPane();

        for (int j = 0; j < face_details; j++)
            for (int i = 0; i < face_details; i++) {
                Rectangle empty = new Rectangle();
                empty.setFill(Color.TRANSPARENT);
                empty.setWidth(thirdOfCell);
                empty.setHeight(thirdOfCell);
                face.add(empty, i, j);
            }
        face.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(thinPadding))));

        face.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                action = true;
            }
        });

        return face;
    }

    /**
     * get listener flag
     * @return flag value
     */
    public boolean gotClicked() {
        return this.action;
    }
}
