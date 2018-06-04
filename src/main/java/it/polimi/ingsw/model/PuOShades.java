package it.polimi.ingsw.model;


import java.io.Serializable;

public class PuOShades extends PublicObjective implements iObjective {
    public static final String LIGHT_SHADES="lightShades";
    public static final String MEDIUM_SHADES="mediumShades";
    public static final String DARK_SHADES="darkShades";

    //set 1 if lightShades 3 if mediumShades 5 if darkShades
    private String typeOfShades;

    public PuOShades(String name, int points, String typeOfShades) {
        super(name,points);
        this.typeOfShades = typeOfShades;
    }


    public String getTypeOfShades() {
        return typeOfShades;
    }

    public void setTypeOfShades(String typeOfShades) {
        this.typeOfShades = typeOfShades;
    }

    @Override
    public String toString() {
        return "PuOShades{" +
                "points=" + getPoints() +
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
            case LIGHT_SHADES:
                i = 1;
                break;
            case MEDIUM_SHADES:
                i = 3;
                break;
            case DARK_SHADES:
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
