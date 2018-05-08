package it.polimi.ingsw.model;

import java.awt.*;

public class PuOSetDifferentColor extends PublicObjective implements iObjective{
    private int points;

    public PuOSetDifferentColor(String name, Color color,int points) {
        super(name, color);
        this.points=points;
    }

    @Override
    public int calculateScore(Player player) {
        Frame fakeFrame= player.getFrame();
        int size=5;
        int[] fakeArray=new int[size];
        Dice[] fakeArrayDice=new Dice[size];
        fakeArrayDice[0].setColor(Color.red);
        fakeArrayDice[1].setColor(Color.green);
        fakeArrayDice[2].setColor(Color.pink);
        fakeArrayDice[3].setColor(Color.blue);
        fakeArrayDice[4].setColor(Color.yellow);
        int minVal=20;
        for(int i=0;i<size;i++){
            fakeArray[i]=fakeFrame.getNumberOfDice(fakeArrayDice[i]);
            if (fakeArray[i]<minVal)
                minVal=fakeArray[i];
        }
        return (this.points*minVal);
    }
}
