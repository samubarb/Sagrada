package it.polimi.ingsw.model;

import java.io.Serializable;

public class TcGlazingHammer extends ToolCard implements iTool,Serializable{

    public TcGlazingHammer(int number, String name) {
        super(number, name);
    }

    @Override
    public void useTool(Player player) {
        player.getCurrentGame().randomDice(player.getCurrentGame().getCurrentDice());


    }
}
