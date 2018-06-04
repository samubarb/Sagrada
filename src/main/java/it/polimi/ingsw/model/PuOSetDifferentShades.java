package it.polimi.ingsw.model;

import java.awt.*;
import java.io.Serializable;

public class PuOSetDifferentShades extends PublicObjective implements iObjective{


    public PuOSetDifferentShades(String name,int points) {
        super(name,points);

    }

    @Override
    public int calculateScore(Player player) {
        Frame fakeFrame= player.getFrame();
        int size=6;
        int[] countDiceValue=new int[size];
        int minVal=20;
        Dice fakeDice=new Dice();
        for(int i=0;i<size;i++){
            fakeDice.setValue(i+1);
            countDiceValue[i]=fakeFrame.getNumberOfDice(fakeDice);
            if (countDiceValue[i]<minVal)
                minVal=countDiceValue[i];
        }
        return (this.getPoints()*minVal);
    }
}
