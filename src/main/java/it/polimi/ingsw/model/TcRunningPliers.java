package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exceptions.NutChosenWrongException;

public class TcRunningPliers extends ToolCard implements iTool {

    public TcRunningPliers(int number, String name, Color color) {
        super(number, name, color);
    }

    @Override
    public void useTool(Player player) {

    }

    @Override
    public void useTool(Player player, Coordinates coordinates) {

    }

    @Override
    public void useTool(Player player, Action action) throws NutChosenWrongException {

    }

    @Override
    public void useTool(Player player, Coordinates initialPosition, Coordinates finalPosition) {

    }
}
