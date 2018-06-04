package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.NutChosenWrongException;

public class TcCopperFoilBurnisher extends ToolCard implements iTool {

    public TcCopperFoilBurnisher(int number, String name, Color color) {
        super(number, name, color);
    }

    @Override
    public void useTool(Player player) {
        //this method is not used

    }

    @Override
    public void useTool(Player player, Coordinates coordinates) {
        //this method is not used

    }

    @Override
    public void useTool(Player player, Action action) throws NutChosenWrongException {
        //this method is not used

    }

    /**
     * move any one die in your window(frame) ignoring shades restriction
     * @param player
     * @param initialPosition
     * @param finalPosition
     */
    @Override
    public void useTool(Player player, Coordinates initialPosition, Coordinates finalPosition) {
        Dice diceToMove=new Dice();
        diceToMove=(player.getFrame().getDice(initialPosition));
        if(player.checkWindowPatternColorRestriction(diceToMove,finalPosition)&&player.checkAdjacentDice(diceToMove,finalPosition)&&
                player.checkFrameValueAndColorRestriction(diceToMove,finalPosition))
            player.getFrame().moveNut(initialPosition,finalPosition);
        else
            throw new IllegalArgumentException();
    }

    @Override
    public void useTool(Player player, Coordinates initialPositionFirstNut, Coordinates finalPositionFirstNut, Coordinates initialPositionSecondNut, Coordinates finalPositionSecondNut) {
        //this method is not used

    }
}
