package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.exceptions.ConstraintNotValidException;

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

    public void setToken(int token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(this.name + newline);
        string.append("    ");
        for (int i = 0; i < 5; i++)
            string.append(" " + (1 + i) + "   ");
        string.append(newline);
        for (int j = 0; j < 4; j++) {
            string.append(" " + (j+1) + " ");
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
}