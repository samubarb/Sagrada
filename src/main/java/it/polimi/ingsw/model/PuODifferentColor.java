package it.polimi.ingsw.model;

import java.awt.*;
import java.io.Serializable;

public class PuODifferentColor extends PublicObjective implements iObjective {

    private String flagColumnLine;
    public static final int LINE_SIZE= 4;
    public static final int COLUMN_SIZE = 5;
    public static final String COLUMN="column";
    public static final String LINE="line";


    //flagColumnLine 0--->line 1--->column

    public PuODifferentColor(String name, String flag,int points) {
        super(name,points);
        this.flagColumnLine=flag;

    }

    @Override
    public String toString() {
        return "PuODifferentColor{" +
                "flagColumnLine=" + flagColumnLine +
                ", points=" + this.getPoints() +
                '}';
    }

    @Override
    public int calculateScore(Player player) {
        Frame fakeFrame= player.getFrame();
        boolean allDifferent=true;
        int score=0;
        if (flagColumnLine.equals(LINE)){
            for(int i=0;i<LINE_SIZE;i++){
                for(int j=0;j<COLUMN_SIZE;j++){
                    for(int x=j+1;x<COLUMN_SIZE&& allDifferent;x++){
                        if(fakeFrame.getDice(i,j).getColor()==fakeFrame.getDice(i,x).getColor())
                              allDifferent=false; }
                    }

                if(allDifferent)
                    score+=this.getPoints();
                else allDifferent=true;
                }
            }
            if (flagColumnLine.equals(COLUMN)){
                for(int i=0;i<COLUMN_SIZE;i++) {
                    for (int j = 0; j < LINE_SIZE; j++) {
                        for (int x = j + 1; x < LINE_SIZE && allDifferent; x++) {
                            if (fakeFrame.getDice(j, i).getColor() == fakeFrame.getDice(x, i).getColor())
                                allDifferent = false;
                        }
                    }
                    if (allDifferent)
                        score += this.getPoints();
                    else allDifferent = true;
                }
            }
            return score;
    }

}


