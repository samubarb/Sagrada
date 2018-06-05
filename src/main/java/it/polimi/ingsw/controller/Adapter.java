package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.cards.VWindowPattern;
import it.polimi.ingsw.view.exceptions.InvalidPositionException;
import it.polimi.ingsw.view.game_elements.*;
import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.other_elements.VCoordinates;

public interface Adapter {

    VWindowPattern patternToView(WindowPattern pattern);
    VFrame frameToView(Frame frame);
    VColor colorToView(Color color);
    VCoordinates coordinatesToView(Coordinates xy);
    VDice diceToView(Dice dice);
    Coordinates coordinatesToModel(VCoordinates xy);
    Action booleanToAction(boolean action);

    // Game adapters
    VGame gameToView(Game game);
    // Game gameToModel(VGame game); // shouldn't be necessary

    VPlayer playerToView(Player player);
    VCurrentDice currentDiceToView(Dice[] currentDice) throws InvalidPositionException;
}
