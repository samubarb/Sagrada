package it.polimi.ingsw.controller.Server;

import it.polimi.ingsw.controller.Adapter;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Coordinates;
import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.view.VColor;
import it.polimi.ingsw.view.VCoordinates;
import it.polimi.ingsw.view.VDice;
import it.polimi.ingsw.view.VWindowPattern;

public class AdapterCLI implements Adapter {

    public VWindowPattern patternToView(WindowPattern pattern) {
        VWindowPattern vPattern = new VWindowPattern();
        vPattern.setName(pattern.getName());
        vPattern.setToken(pattern.getFavorTokenToAssign());

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 5; i++) {
             // to finish
            }
        }



        return vPattern;
    }
/*
    public VDice DiceToView(Dice dice) {
        VColor vc;
        VCoordinates xy;





        //create VDice
    }

    public VCoordinates CoordinatesToView(Coordinates xy) {
        // coordinates adaptation?
    }

    public VColor ColorToView(Color color) {
        //shitch-case on colors
    }*/
}
