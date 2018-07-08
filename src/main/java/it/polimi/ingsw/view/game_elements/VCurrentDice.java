package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.exceptions.InvalidPositionException;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VCurrentDice {
    private VDice[] dice;
    private boolean action;

    /**
     * represents the dice slot from which the players take dice
     * @param length int value with the number of dice, dependant from the players number
     */
    public VCurrentDice(int length) {
        dice = new VDice[length];
        this.action = false;
    }

    /**
     * set a dice in a specific position
     * @param dice the dice to place
     * @param position the position in which place it
     * @throws InvalidPositionException thrown if
     */
    public void add(VDice dice, int position) throws InvalidPositionException{
        if (position > this.dice.length) {
            throw new InvalidPositionException();
        }
        else
            this.dice[position] = dice;
    }

    /**
     * get the current dice length
     * @return
     */
    public int size() {
        return this.dice.length;
    }

    /**
     * get the dice of a determined position
     * @param position VCoordinates
     * @return VDice
     */
    public VDice get(int position) {
        return this.dice[position];
    }

    /**
     * get the formatted text version, to be shown in the CLI
     * @return String printable
     */
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

    /**
     * get the formatted current dice to be shown in GUI
     * @return HBox in which every node is a dice face
     */
    public HBox toGUI() {
        HBox currentDice = new HBox();
        for(VDice vd : this.dice)
            if (vd != null)
                currentDice.getChildren().add(vd.toGUI());
        else currentDice.getChildren().add(new VDice().slotDice());

        return currentDice;
    }
}
