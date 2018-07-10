package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.exceptions.InvalidPositionException;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import static it.polimi.ingsw.inputoutput.IOManager.newline;
import static it.polimi.ingsw.inputoutput.IOManager.print;

public class VRoundTrack {
    private VDice[] track;

    /**
     * represents the round track, the array of dice that keep track of the round
     * @param size
     */
    public VRoundTrack(int size) {
        this.track = new VDice[size];
    }

    /**
     * add a dice to the round track
     * @param dice dice to be placed
     * @param position position in which place the dice
     * @throws InvalidPositionException thrown if trying to place the dice in
     */
    public void add(VDice dice, int position) throws InvalidPositionException {
        if (position > this.track.length) {
            throw new InvalidPositionException();
        }
        else
            this.track[position] = dice;
    }

    /**
     * get the size of the round track
     * @return
     */
    public int size() {
        return this.track.length;
    }

    /**
     * get the dice given his index
     * @param i the index
     * @return the wanted dice
     */
    public VDice get(int i) {
        return this.track[i];
    }

    /**
     * get the text version of the round track to be shown in CLI
     * @return
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Round Track:");
        for(VDice vd : this.track)
            if (vd != null)
                string.append(vd.toString());
            else string.append("   ");
        string.append(newline);
        for(int i = 0; i < this.track.length; i++)
            string.append(" " + (i + 1) + " ");
        return string.toString();
    }

    /**
     * get the graphic version of the round track to be shown in GUI
     * @return
     */
    public HBox toGUI() {
        HBox roundTrack = new HBox();
        for (VDice dice : this.track)
            if (dice != null) {
                roundTrack.getChildren().add(dice.toGUI());

            }
        else roundTrack.getChildren().add(new VDice().slotDice());

        return roundTrack;
    }
}
