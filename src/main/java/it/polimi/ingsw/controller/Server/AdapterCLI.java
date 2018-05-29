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
        int value = dice.getValue();

        }





        //create VDice
    }

    public VCoordinates CoordinatesToView(Coordinates xy) {
        // coordinates adaptation?
    }*/

    public VColor ColorToView(Color color) {
        VColor vc = VColor.RESET;
        switch (color) {
            case RED:
                vc = VColor.RED;
                break;

            case GREEN:
                vc = VColor.GREEN;
                break;

            case BLUE:
                vc = VColor.BLUE;
                break;

            case PURPLE:
                vc = VColor.PURPLE;
                break;

            case YELLOW:
                vc = VColor.YELLOW;
                break;

            case UNCOLORED:
                vc = VColor.RESET;
                break;
        }
        return vc;
    }
}
