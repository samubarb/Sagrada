package it.polimi.ingsw.model;

import java.awt.*;

public class PuOShades extends PublicObjective implements iObjective {

    private int points;

    //set 1 if lightShades 3 if mediumShades 5 if darkShades
    private int typeOfShades;

    public PuOShades(String name, Color color, int points, int typeOfShades) {
        super(name, color);
        this.points = points;
        this.typeOfShades = typeOfShades;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTypeOfShades() {
        return typeOfShades;
    }

    public void setTypeOfShades(int typeOfShades) {
        this.typeOfShades = typeOfShades;
    }

    @Override
    public String toString() {
        return "PuOShades{" +
                "points=" + points +
                ", typeOfShades=" + typeOfShades +
                '}';
    }

    @Override
    public int calculateScore(Player player) {
        Frame fakeFrame= player.getFrame();
        int numberOfDiceFirst,numberOfDiceSecond;
        Dice fakeDice= new Dice();
        int i=0;
        switch (typeOfShades) {
            case 1:
                i = 1;
                break;
            case 3:
                i = 3;
                break;
            case 5:
                i = 5;
                break;
        }
        fakeDice.setValue(i);
        numberOfDiceFirst=fakeFrame.getNumberOfDice(fakeDice);
        fakeDice.setValue(i+1);
        numberOfDiceSecond=fakeFrame.getNumberOfDice(fakeDice);
        if(numberOfDiceFirst<numberOfDiceSecond)
            return (numberOfDiceFirst*2);
        else
            return (numberOfDiceSecond*2);

    }
}
