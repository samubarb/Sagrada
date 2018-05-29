package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.*;
import it.polimi.ingsw.view.exceptions.InvalidPositionException;

public interface Adapter {

    VWindowPattern patternToView(WindowPattern pattern);
    VFrame frameToView(Frame frame);
    VColor colorToView(Color color);
    VCoordinates coordinatesToView(Coordinates xy);
    VDice diceToView(Dice dice);
    Coordinates coordinatesToModel(VCoordinates xy);
    //VPlayer playerToView(Player player);
    VCurrentDice currentDiceToView(Dice[] currentDice) throws InvalidPositionException;


}
