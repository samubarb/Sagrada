package it.polimi.ingsw.model;

import java.awt.*;

public class PuOSetDifferentShades extends PublicObjective implements iObjective {

    public PuOSetDifferentShades(String name, Color color) {
        super(name, color);
    }

    @Override
    public int calculateScore(Player player) {
        Frame fakeFrame= player.getFrame();
        int size=6;
        int[] fakeArray=new int[6];
        int minVal=20;
        Dice fakeDice=new Dice();
        for(int i=0;i<6;i++){
            fakeDice.setValue(i+1);
            fakeArray[i]=fakeFrame.getNumberOfDice(fakeDice);
            if (fakeArray[i]<minVal)
                minVal=fakeArray[i];
        }
        return (5*minVal);
    }
}
