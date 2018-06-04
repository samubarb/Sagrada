package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.NutChosenWrongException;

public class TcRunningPliers extends ToolCard implements iTool {

    public TcRunningPliers(int number, String name, Color color) {
        super(number, name, color);
    }

    /**
     * afterd your first turn immediately draft die, skip your next turn in this round
     * remove the player from the stack so he does not play anymore in this round
     * @param player
     */
    @Override
    public void useTool(Player player) {
        if(player.equals(player.getCurrentGame().getTurnStack().pop()));
        else throw new IllegalArgumentException();

    }

    @Override
    public void useTool(Player player, Coordinates coordinates) {
        //this method is not used

    }

    @Override
    public void useTool(Player player, Action action) throws NutChosenWrongException {
        //this method is not used

    }

    @Override
    public void useTool(Player player, Coordinates initialPosition, Coordinates finalPosition) {
        //this method is not used

    }

    @Override
    public void useTool(Player player, Coordinates initialPositionFirstNut, Coordinates finalPositionFirstNut, Coordinates initialPositionSecondNut, Coordinates finalPositionSecondNut) {
        //this method is not used

    }
}
