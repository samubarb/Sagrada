package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.view.VDice;
import it.polimi.ingsw.view.VWindowPattern;

public interface Adapter {

    VWindowPattern patternToView(WindowPattern pattern);
    //VDice DiceToView(Dice dice);
}
