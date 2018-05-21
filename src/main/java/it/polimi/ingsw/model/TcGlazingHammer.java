package it.polimi.ingsw.model;

import java.io.Serializable;

public class TcGlazingHammer extends ToolCard implements iTool{

    public TcGlazingHammer(int number, String name) {
        super(number, name);
    }

    @Override
    public void useTool(Player player) {
        player.getCurrentGame().randomDice(player.getCurrentGame().getCurrentDice());


    }

    @Override
    public void useTool(Player player, Coordinates coordinates) {

    }
}
