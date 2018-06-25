package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.exceptions.ConstraintNotValidException;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

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

    public GridPane toGUI() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label name = new Label(this.name); // Set name of WPcard
        GridPane.setConstraints(name, 0,0);

        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 5; i++)
                if (pattern[i][j] != null) {
                    GridPane.setConstraints(pattern[i][j].toGUI(), i + 1, j + 1);
                    grid.getChildren().addAll(pattern[i][j].toGUI());
                }

        return grid;
    }
}
