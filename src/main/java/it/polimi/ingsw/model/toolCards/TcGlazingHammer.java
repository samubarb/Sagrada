package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.*;

public class TcGlazingHammer extends ToolCard implements iTool {

    public TcGlazingHammer(int number, String name,Color color) {
        super(number, name,color);
    }

    /**
     * Re-roll all dice in the currentDice array
     * @param player
     */
    @Override
    public void useTool(Player player) {
        player.getCurrentGame().randomDice(player.getCurrentGame().getCurrentDice());


    }

    @Override
    public void useTool(Player player, Coordinates coordinates) {
        //this method is not used

    }

    @Override
    public void useTool(Player player, Action action) {
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
