package it.polimi.ingsw.model;

import java.awt.*;

public class PuODifferentShades extends PublicObjective implements iObjective {

    private int flagColumnLine;
    private int points;

    //flagColumnLine 0--->line 1--->column

    public PuODifferentShades(String name, Color color, int flagColumnLine, int points) {
        super(name, color);
        this.flagColumnLine = flagColumnLine;
        this.points = points;
    }

    public int getFlagColumnLine() {
        return flagColumnLine;
    }

    public void setFlagColumnLine(int flagColumnLine) {
        this.flagColumnLine = flagColumnLine;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "PuODifferentShades{" +
                "flagColumnLine=" + flagColumnLine +
                ", points=" + points +
                '}';
    }

    @Override
    public int calculateScore(Player player) {
        Frame fakeFrame= player.getFrame();
        boolean allDifferent=true;
        int score=0;
        if (flagColumnLine==0){
            for(int i=0;i<4;i++){
                for(int j=0;j<5;j++){
                    for(int x=j+1;x<5&& allDifferent;x++){
                        if(fakeFrame.getDice(i,j).getValue()==fakeFrame.getDice(i,x).getValue())
                            allDifferent=false; }
                }

                if(allDifferent)
                    score+=points;
                else allDifferent=true;
            }
        }
        if (flagColumnLine==1){
            for(int i=0;i<5;i++) {
                for (int j = 0; j < 4; j++) {
                    for (int x = j + 1; x < 4 && allDifferent; x++) {
                        if (fakeFrame.getDice(j, i).getValue() == fakeFrame.getDice(x, i).getValue())
                            allDifferent = false;
                    }
                }
                if (allDifferent)
                    score += points;
                else allDifferent = true;
            }
        }
        return score;
    }
}
