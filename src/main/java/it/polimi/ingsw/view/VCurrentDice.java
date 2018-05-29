package it.polimi.ingsw.view;

import static it.polimi.ingsw.inputoutput.IOManager.newline;
import static it.polimi.ingsw.inputoutput.IOManager.print;
import static it.polimi.ingsw.inputoutput.IOManager.println;

public class VCurrentDice {
    VDice[] dice;

    public VCurrentDice(int length) {
        dice = new VDice[length];
    }

    public void add(VDice dice, int position) {
        if (position > this.dice.length) {
            // throw exception here
        }
        this.dice[position] = dice;
    }

    public VDice get(int position) {
        return this.dice[position];
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(VDice vd : this.dice)
            string.append(vd.toString());

        string.append(newline);

        for(int i = 0; i < this.dice.length; i++)
            string.append(" " + (i + 1) + " ");

        return string.toString();
    }
}
