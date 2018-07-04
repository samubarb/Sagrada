
package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.VWindowPattern;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static it.polimi.ingsw.inputoutput.IOManager.newline;
import static it.polimi.ingsw.inputoutput.IOManager.thinPadding;

public class VFrame {
    VDice[][] frame;

    public VFrame() {
        this.frame = new VDice[5][4];
    }

    public void setDice(VDice dice, VCoordinates xy) {
        this.frame[xy.getX() - 1][xy.getY() - 1] = dice;
    }

    public VDice getDice(VCoordinates xy) {
        return this.frame[xy.getX() - 1][xy.getY() - 1];
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("   ");
        for (int i = 0; i < 5; i++)
            string.append(" " + (1 + i) + "   ");
        string.append(newline);
        for (int j = 0; j < 4; j++) {
            string.append((j+1) + " ");
            for (int i = 0; i < 5; i++) {
                string.append("|");
                if (this.frame[i][j] == null) {
                    string.append("   ");
                } else {
                    string.append(this.frame[i][j].toString());
                }
                string.append("|");
            }
            string.append("\n");
        }
        return string.toString();
    }

    public GridPane buildGrid(VWindowPattern windowPattern) {
        GridPane wp = windowPattern.buildGrid();

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 4; j++) {
                if (this.frame[i][j] != null) {
                    Pane face = this.frame[i][j].toGUI();
                    face.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(thinPadding))));
                    wp.add(face, i, j);
                }
            }

        return wp;
    }
}