package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.Action;
import it.polimi.ingsw.model.Coordinates;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.exceptions.*;

public interface iTool {

    public void useTool (Player player);
    public void useTool (Player player,Coordinates coordinates);
    public void useTool(Player player,Action action)throws NutChosenWrongException;
    public void useTool(Player player, Coordinates initialPosition,Coordinates finalPosition);
    public void useTool(Player player,Coordinates initialPositionFirstNut,Coordinates finalPositionFirstNut,Coordinates initialPositionSecondNut,Coordinates finalPositionSecondNut) throws FrameValueAndColorException, WindowPatternValueException, WindowPatternColorException, BusyPositionException, AdjacentDiceException;

}
