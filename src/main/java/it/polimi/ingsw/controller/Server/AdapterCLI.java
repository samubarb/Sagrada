package it.polimi.ingsw.controller.Server;

import it.polimi.ingsw.controller.Adapter;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.*;
import it.polimi.ingsw.view.exceptions.ConstraintNotValidException;
import static it.polimi.ingsw.inputoutput.IOManager.*;

public final class AdapterCLI implements Adapter {

    public VWindowPattern patternToView(WindowPattern pattern) {
        VWindowPattern vPattern = new VWindowPattern();
        vPattern.setName(pattern.getName());
        vPattern.setToken(pattern.getFavorTokenToAssign());

        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 5; i++) {
                Coordinates xy = new Coordinates(i, j);
                try {
                    vPattern.setConstraint(diceToView(pattern.getDicePosition(xy)), coordinatesToView(xy)); // from model to Vpattern constraints
                } catch (ConstraintNotValidException e) {
                    println("Qualcosa è andato storto.");
                    println("Un vincolo del pattern non è corretto (solo colore OPPURE solo numero)");
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        return vPattern;
    }

    public VFrame frameToView(Frame frame) {
        VFrame vFrame = new VFrame();

        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 5; i++) {
                Coordinates xy = new Coordinates(i, j);
                vFrame.setDice(diceToView(frame.getDice(xy)), coordinatesToView(xy));
            }
        return vFrame;
    }

    public VDice diceToView(Dice dice) {
        if (dice == null)
            return null;
        if (dice.getValue() == 0 && dice.getColor() == Color.UNCOLORED)
            return null;
        return new VDice(dice.getValue(), colorToView(dice.getColor()));
    }


    public VCoordinates coordinatesToView(Coordinates xy) {
        int x, y;
        x = xy.getX() + 1;
        y = xy.getY() + 1;
        return new VCoordinates(x, y);
    }

    public Coordinates coordinatesToModel(VCoordinates xy) {
        int x, y;
        x = xy.getX() - 1;
        y = xy.getY() - 1;
        return new Coordinates(x, y);
    }

    /*
    public VPlayer playerToView(Player player) {
        VPlayer vPlayer = new VPlayer(player.getName());
        vPlayer.
    }
    */

    public VCurrentDice currentDiceToView(Dice[] currentDice) {
        VCurrentDice vCurrentDice = new VCurrentDice(currentDice.length);
        for (int i = 0; i < currentDice.length; i++) {
            vCurrentDice.add(diceToView(currentDice[i]), i);
        }
        return vCurrentDice;
    }

    public VColor colorToView(Color color) {
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
