package it.polimi.ingsw.model;

import java.io.Serializable;

public class TcLensCutter extends ToolCard implements iTool {


    public TcLensCutter(int number, String name) {
        super(number, name);
    }

    @Override
    public void useTool(Player player) {

        player.getCurrentGame().getTurnOrder();



    }
}
