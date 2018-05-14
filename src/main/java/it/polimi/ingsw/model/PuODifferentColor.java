package it.polimi.ingsw.model;

import java.awt.*;

public class PuODifferentColor extends PublicObjective implements iObjective {

    private String flagColumnLine;
    private int points;
    public static final int LINE_SIZE= 4;
    public static final int COLUMN_SIZE = 5;


    //flagColumnLine 0--->line 1--->column

    public PuODifferentColor(String name, Color color, String flag,int points) {
        super(name, color);
        this.flagColumnLine=flag;
        this.points=points;
    }

    @Override
    public String toString() {
        return "PuODifferentColor{" +
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
                        if(fakeFrame.getDice(i,j).getColor()==fakeFrame.getDice(i,x).getColor())
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
                        for (int x = j + 1; x < LINE_SIZE && allDifferent; x++) {
                            if (fakeFrame.getDice(j, i).getColor() == fakeFrame.getDice(x, i).getColor())
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


