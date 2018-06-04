package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.exceptions.InvalidPositionException;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VCurrentDice {
    VDice[] dice;

    public VCurrentDice(int length) {
        dice = new VDice[length];
    }

    public void add(VDice dice, int position) throws InvalidPositionException{
        if (position > this.dice.length) {
            throw new InvalidPositionException();
        }
        else
            this.dice[position] = dice;
    }

    public int size() {
        return this.dice.length;

    }

    public VDice get(int position) {
        return this.dice[position];
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(VDice vd : this.dice)
            if (vd != null)
                string.append(vd.toString());
        else string.append("   ");
        string.append(newline);
        for(int i = 0; i < this.dice.length; i++)
            string.append(" " + (i + 1) + " ");
        return string.toString();
    }
}
