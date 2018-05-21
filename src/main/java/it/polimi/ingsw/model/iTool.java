package it.polimi.ingsw.model;

import it.polimi.ingsw.model.exceptions.NutChosenWrongException;

public interface iTool {

    public void useTool (Player player);
    public void useTool (Player player,Coordinates coordinates);
    public void useTool(Player player,Action action)throws NutChosenWrongException;
    public void useTool(Player player, Coordinates initialPosition,Coordinates finalPosition);
}
