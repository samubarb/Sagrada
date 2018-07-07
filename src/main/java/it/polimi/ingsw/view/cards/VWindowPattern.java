package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.exceptions.ConstraintNotValidException;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VWindowPattern {
    private String name;
    private int token;
    private VDice[][] pattern;

    private static final String favorToken = "•";

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

    public int getToken() {
        return token;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(this.name + newline);
        string.append("  ");
        for (int i = 0; i < cols; i++)
            string.append(" " + (i + 1) + "   ");
        string.append(newline);
        for (int j = 0; j < rows; j++) {
            string.append((j+1) + " ");
            for (int i = 0; i < cols; i++) {
                string.append("|");
                if (this.pattern[i][j] == null) {
                    string.append("   ");
                } else {
                    string.append(this.pattern[i][j].toString());
                }
                string.append("|");
            }
            string.append(newline);
        }
        string.append("Segnalini favore: ");
        for (int i = 0; i < this.token; i++)
            string.append(favorToken);
        string.append(newline);
        return string.toString();
    }

    public GridPane buildGrid() {
        GridPane grid = new GridPane();

        grid.setStyle("-fx-background-color: grey;");
        grid.setHgap(padding); // set horizontal gap
        grid.setVgap(padding); // set vertical gap
        grid.setPadding(new Insets(padding)); // set all around padding gaps
        grid.setGridLinesVisible(false);

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                if (this.pattern[i][j] == null) {
                    grid.add(VDice.slotDice(), i, j);
                }
                else {
                    grid.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(thinPadding))));
                    Pane face = this.pattern[i][j].toGUI();
                    grid.add(face, i, j);
                }
            }
        }
        return grid;
    }

    public VBox toGUI() {
        VBox wpattern = new VBox();
        GridPane grid = this.buildGrid();

        Label cardName = new Label(this.name);
        Label labelTokens = new Label("Segnalini favore: ");
        GridPane favorTokens = new GridPane();

        //cardName.setFont(Font.font(null, FontWeight.BOLD, 20));
        //cardName.setPadding(new Insets(padding));

        labelTokens.setFont(Font.font(null, FontWeight.NORMAL, 14));

        favorTokens.setPadding(new Insets(padding));
        favorTokens.setHgap(thinPadding);

        wpattern.setStyle("-fx-background-color: grey;");

        favorTokens.add(labelTokens, 0, 0);

        for (int i = 0; i < this.token; i++)
            favorTokens.add(new Circle(10, Color.WHITE), i + 1, 0);

        wpattern.getChildren().addAll(/*cardName,*/ grid, favorTokens);

        return wpattern;
    }
}
