package it.polimi.ingsw.model;

import java.awt.*;

public class PuODifferentShades extends PublicObjective implements iObjective {

    private String flagColumnLine;
    private int points;
    public static final int LINE_SIZE= 4;
    public static final int COLUMN_SIZE = 5;

    //flagColumnLine 0--->line 1--->column

    public PuODifferentShades(String name, Color color, String flagColumnLine, int points) {
        super(name, color);
        this.flagColumnLine = flagColumnLine;
        this.points = points;
    }

    public String getFlagColumnLine() {
        return flagColumnLine;
    }

    public void setFlagColumnLine(String flagColumnLine) {
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
        if (flagColumnLine.equals("line")){
            for(int i=0;i<LINE_SIZE;i++){
                for(int j=0;j<COLUMN_SIZE;j++){
                    for(int x=j+1;x<COLUMN_SIZE&& allDifferent;x++){
                        if(fakeFrame.getDice(i,j).getValue()==fakeFrame.getDice(i,x).getValue())
                            allDifferent=false; }
                }

                if(allDifferent)
                    score+=points;
                else allDifferent=true;
            }
        }
        if (flagColumnLine.equals("column")){
            for(int i=0;i<COLUMN_SIZE;i++) {
                for (int j = 0; j < LINE_SIZE; j++) {
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
