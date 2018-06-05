package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.exceptions.InvalidPositionException;

import static it.polimi.ingsw.inputoutput.IOManager.newline;
import static it.polimi.ingsw.inputoutput.IOManager.print;

public class VRoundTrack {
    VDice[] track;

    public VRoundTrack(int size) {
        this.track = new VDice[size];
    }

    public void add(VDice dice, int position) throws InvalidPositionException {
        if (position > this.track.length) {
            throw new InvalidPositionException();
        }
        else
            this.track[position] = dice;
    }

    public int size() {
        return this.track.length;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(VDice vd : this.track)
            if (vd != null)
                string.append(vd.toString());
            else string.append("   ");
        string.append(newline);
        for(int i = 0; i < this.track.length; i++)
            string.append(" " + (i + 1) + " ");
        return string.toString();
    }
}
