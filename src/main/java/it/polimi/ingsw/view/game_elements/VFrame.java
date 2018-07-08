
package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.VWindowPattern;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VFrame {
    VDice[][] frame;

    /**
     * represents the game player's frame, where the player puts his dice
     */
    public VFrame() {
        this.frame = new VDice[cols][rows];
    }

    /**
     * used to placed a dice in a specific position
     * @param dice VDice
     * @param xy VCoordinates position
     */
    public void setDice(VDice dice, VCoordinates xy) {
        this.frame[xy.getX() - 1][xy.getY() - 1] = dice;
    }

    /**
     * retrieve a dice from the frame, at a specific position
     * @param xy position
     * @return VDice
     */
    public VDice getDice(VCoordinates xy) {
        return this.frame[xy.getX() - 1][xy.getY() - 1];
    }

    /**
     * get the formatted text version to be shown in CLI
     * @return String printable
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("   ");
        for (int i = 0; i < cols; i++)
            string.append(" " + (1 + i) + "   ");
        string.append(newline);
        for (int j = 0; j < rows; j++) {
            string.append((j+1) + " ");
            for (int i = 0; i < cols; i++) {
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

    /**
     * get the formatted graphic version to be shown in GUI
     * @param windowPattern used to make the frame be on top of wpattern
     * @return GridPane with the union of frame and wpattern
     */
    public GridPane buildGrid(VWindowPattern windowPattern) {
        GridPane wp = windowPattern.buildGrid();

        for (int i = 0; i < cols; i++)
            for (int j = 0; j < rows; j++) {
                if (this.frame[i][j] != null) {
                    Pane face = this.frame[i][j].toGUI();
                    face.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(thinPadding))));
                    wp.add(face, i, j);
                }
            }

        return wp;
    }
}