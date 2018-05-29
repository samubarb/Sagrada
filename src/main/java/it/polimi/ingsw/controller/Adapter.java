package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.*;

public interface Adapter {

    VWindowPattern patternToView(WindowPattern pattern);
    VFrame frameToView(Frame frame);
    VColor colorToView(Color color);
    VCoordinates coordinatesToView(Coordinates xy);
    VDice diceToView(Dice dice);
    Coordinates coordinatesToModel(VCoordinates xy);
}
