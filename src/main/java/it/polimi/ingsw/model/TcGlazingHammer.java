package it.polimi.ingsw.model;

import java.io.Serializable;

public class TcGlazingHammer extends ToolCard implements iTool{

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

    }

    @Override
    public void useTool(Player player, Action action) {

    }

    @Override
    public void useTool(Player player, Coordinates initialPosition, Coordinates finalPosition) {

    }
}
