package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.Action;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Coordinates;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.exceptions.NutChosenWrongException;

public class TcFluxRemover extends ToolCard implements iTool{

    public TcFluxRemover(int number, String name, Color color) {
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

    @Override
    public void useTool(Player player, Coordinates initialPositionFirstNut, Coordinates finalPositionFirstNut, Coordinates initialPositionSecondNut, Coordinates finalPositionSecondNut) {

    }
}
