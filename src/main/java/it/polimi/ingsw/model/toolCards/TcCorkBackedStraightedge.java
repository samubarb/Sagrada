package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.NutChosenWrongException;

public class TcCorkBackedStraightedge extends ToolCard implements iTool {

    public TcCorkBackedStraightedge(int number, String name, Color color) {
        super(number, name, color);
    }

    @Override
    public void useTool(Player player) {
        //this method is not used

    }

    /**
     * after drafting, place the die in a spot that is not adjacent to another die
     * @param player
     * @param coordinates
     */
    @Override
    public void useTool(Player player, Coordinates coordinates) {
        if(player.checkWindowPatternColorRestriction(player.getChosenNut(),coordinates)&&player.checkWindowPatternValueRestriction(player.getChosenNut(),coordinates))
            player.getFrame().setPositionDice(player.getChosenNut(),coordinates);
        else
            throw new IllegalArgumentException();

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
