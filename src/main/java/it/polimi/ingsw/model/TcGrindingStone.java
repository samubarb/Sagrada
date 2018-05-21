package it.polimi.ingsw.model;

import java.io.Serializable;

public class TcGrindingStone extends ToolCard implements iTool{

    public TcGrindingStone(int number, String name,Color color) {
        super(number, name,color);
    }


    /**
     * after drafting, flip the die to its opposite side
     * @param player
     */
    @Override
    public void useTool(Player player) {
        player.getChosenNut().setValue(7-player.getChosenNut().getValue());
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
