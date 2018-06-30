package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.exceptions.ConstraintNotValidException;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VWindowPattern {
    private String name;
    private int token;
    private VDice[][] pattern;

    public VWindowPattern() {
        this.pattern = new VDice[5][4];
    }

    public void setConstraint(VDice dice, VCoordinates xy) throws ConstraintNotValidException {
        if (dice == null) {
            this.pattern[xy.getX() - 1][xy.getY() - 1] = dice;
            return;
        }

        if (dice.getValue() != 0 && dice.getColor() != VColor.RESET)
            throw new ConstraintNotValidException();

        if (dice.getValue() == 0 && dice.getColor() == VColor.RESET) {
            this.pattern[xy.getX() - 1][xy.getY() - 1] = null;
            return;
        }

        this.pattern[xy.getX() - 1][xy.getY() - 1] = dice;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() { return this.name; }

    public void setToken(int token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(this.name + newline);
        string.append("  ");
        for (int i = 0; i < 5; i++)
            string.append(" " + (i + 1) + "   ");
        string.append(newline);
        for (int j = 0; j < 4; j++) {
            string.append((j+1) + " ");
            for (int i = 0; i < 5; i++) {
                string.append("|");
                if (this.pattern[i][j] == null) {
                    string.append("   ");
                } else {
                    string.append(this.pattern[i][j].toString());
                }
                string.append("|");
            }
            string.append("\n");
        }
        return string.toString();
    }

    public Group toGUI() { /*To refine*/
        Color color;
        Label cardName = new Label(this.name);
        GridPane grid = new GridPane();
        GridPane wpattern = new GridPane();
        GridPane favorTokens = new GridPane();
        Group root = new Group();

        wpattern.setStyle("-fx-background-color: grey;");
        grid.setStyle("-fx-background-color: grey;");

        grid.setHgap(padding); // set horizontal gap
        grid.setVgap(padding); // set vertical gap
        grid.setPadding(new Insets(padding)); // set all around padding gaps

        // grid.add(cardName, 0,0);

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                color = this.pattern[i][j] == null ? Color.TRANSPARENT : this.pattern[i][j].colorToGUI();
                grid.add(new Rectangle(cellWidth, cellHeight, color), i, j);
            }
        }

        for (int i = 0; i < this.token; i++)
            favorTokens.add(new Circle(10, Color.WHITE), i, 0);

        wpattern.add(cardName, 0,0);
        wpattern.add(grid, 0, 1);
        wpattern.add(favorTokens, 0, 2);

        root.getChildren().addAll(wpattern);

        return root;
    }
}
