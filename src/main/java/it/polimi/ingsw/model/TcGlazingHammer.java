package it.polimi.ingsw.model;

public class TcGlazingHammer extends ToolCard implements iTool{

    public TcGlazingHammer(int number, String name) {
        super(number, name);
    }

    @Override
    public void useTool(Player player) {
        player.getCurrentGame().randomDice(player.getCurrentGame().getCurrentDice());


    }
}