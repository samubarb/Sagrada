package it.polimi.ingsw.model;

import java.io.Serializable;

public class TcGrindingStone extends ToolCard implements iTool{

    public TcGrindingStone(int number, String name) {
        super(number, name);
    }

    @Override
    public void useTool(Player player) {
        player.getChosenNut().setValue(7-player.getChosenNut().getValue());
    }

    @Override
    public void useTool(Player player, Coordinates coordinates) {

    }
}
