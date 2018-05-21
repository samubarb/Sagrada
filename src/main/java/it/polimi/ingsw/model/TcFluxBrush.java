package it.polimi.ingsw.model;

import java.io.Serializable;

public class TcFluxBrush extends ToolCard implements iTool {

    public TcFluxBrush(int number, String name,Color color) {
        super(number, name,color);
    }

    /**
     * after drafting re-roll the drafted die (currentNut)
     * @param player
     */
    @Override
    public void useTool(Player player) {
        player.getCurrentGame().randomDice(player.getChosenNut());
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
