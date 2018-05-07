package it.polimi.ingsw.model;

import java.awt.*;

public class Frame {

    private Dice [][] frame;

    public Frame() {
        this.frame=new Dice[4][5];
        fillDiceDefault();
    }

    public void setPositionDice(Dice dice, int i,int j){
        frame[i][j]=dice;
    }

    public Dice getDice(int i, int j){
        Dice fakeDice;
        fakeDice=frame[i][j];
        return fakeDice;
    }

    public int getNumberOfDice(Dice dice){
        int counter=0;
        if( dice.getValue()==0){
            for(int i=0; i<4;i++)
                for(int j=0;j<5;j++)
                    if(this.getDice(i,j).getColor()==dice.getColor())
                        counter++;

        }
        if(dice.getColor()==Color.black){
            for(int i=0; i<4;i++)
                for(int j=0;j<5;j++)
                    if(this.getDice(i,j).getValue()==dice.getValue())
                        counter++;
        }

        return counter;

    }
    public void fillDiceDefault(){
        for(int i=0; i<4;i++)
            for(int j=0;j<5;j++)
                this.frame[i][j]=new Dice();
    }
}
