package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.NutChosenWrongException;

public class TcEglomiseBrush extends ToolCard implements iTool {

    public TcEglomiseBrush(int number, String name, Color color) {
        super(number, name, color);
    }


    /**
     * move any one die in your window(frame),ignoring the windowPattern color restriction
     * @param player
     * @param initialPosition
     * @param finalPosition
     */
    @Override
    public void useTool(Player player, Coordinates initialPosition, Coordinates finalPosition) {
        Dice diceToMove=new Dice();
        diceToMove=(player.getFrame().getDice(initialPosition));
        if(player.checkWindowPatternValueRestriction(diceToMove,finalPosition)&&player.checkAdjacentDice(diceToMove,finalPosition)&&
            player.checkFrameValueAndColorRestriction(diceToMove,finalPosition))
            player.getFrame().moveNut(initialPosition,finalPosition);
        else
            throw new IllegalArgumentException();

    }

    @Override
    public void useTool(Player player) {
        //this method is not used

    }

    @Override
    public void useTool(Player player, Coordinates coordinates) {

    }

    @Override
    public void useTool(Player player, Action action) throws NutChosenWrongException {

    }

    @Override
    public void useTool(Player player, Coordinates initialPositionFirstNut, Coordinates finalPositionFirstNut, Coordinates initialPositionSecondNut, Coordinates finalPositionSecondNut) {

    }
}
