package it.polimi.ingsw.model;

import java.io.Serializable;

public class TcFluxBrush extends ToolCard implements iTool {

    public TcFluxBrush(int number, String name) {
        super(number, name);
    }

    @Override
    public void useTool(Player player) {
        player.getCurrentGame().randomDice(player.getChosenNut());
    }
}
