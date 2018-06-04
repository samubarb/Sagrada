package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.exceptions.InvalidPositionException;

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
}
