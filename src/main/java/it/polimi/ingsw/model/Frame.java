package it.polimi.ingsw.model;


import java.io.Serializable;

public class Frame implements Serializable {

    public static final int LINE_SIZE=4;
    public static final int COLUMN_SIZE=5;
    private Dice [][] frame;


    public Frame() {
        this.frame=new Dice[4][5];
        fillDiceDefault();
    }

    public void setPositionDice(Dice dice, Coordinates position){
        frame[position.getY()][position.getX()]=dice;
    }
    public void setPositionDice(Dice dice,int i,int j){
        frame[i][j]=dice;
    }

    public Dice getDice(Coordinates position){
        Dice fakeDice;
        fakeDice=frame[position.getY()][position.getX()];
        return fakeDice;
    }

    public Dice getDice(int i,int j){
        Dice fakeDice;
        fakeDice=frame[i][j];
        return fakeDice;
    }

    /**
     * @param dice
     * @return
     */
    public int getNumberOfDice(Dice dice){
        int counter=0;
        if( dice.getValue()==0){
            for(int i=0; i<LINE_SIZE;i++)
                for(int j=0;j<COLUMN_SIZE;j++)
                    if(this.getDice(i,j).getColor()==dice.getColor())
                        counter++;

        }
        if(dice.getColor()==Color.UNCOLORED){
            for(int i=0; i<LINE_SIZE;i++)
                for(int j=0;j<COLUMN_SIZE;j++)
                    if(this.getDice(i,j).getValue()==dice.getValue())
                        counter++;
        }

        return counter;

    }
    public void fillDiceDefault(){
        for(int i=0; i<LINE_SIZE;i++)
            for(int j=0;j<COLUMN_SIZE;j++)
                this.frame[i][j]=new Dice();
    }

    /*
    *   checkPositionDice:
    *   control of the color and the value of the orthogonally adjacent dice
    * */

    public boolean checkPositionDice(Dice dice,Coordinates position){
        if(position.getX()==0)
            if(getDice(position.getX(),position.getY()-1).getValue()==dice.getValue()||getDice(position.getX(),position.getY()-1).getColor().equals(dice.getColor()))
                return false;
        if(position.getY()==0)
            if(getDice(position.getX()-1,position.getY()).getValue()==dice.getValue()||getDice(position.getX()-1,position.getY()).getColor().equals(dice.getColor()))
                return false;

        if(getDice(position.getX()-1,position.getY()).getValue()==dice.getValue()||getDice(position.getX()-1,position.getY()).getColor().equals(dice.getColor()))
            return false;
        else if(getDice(position.getX(),position.getY()-1).getValue()==dice.getValue()||getDice(position.getX(),position.getY()-1).getColor().equals(dice.getColor()))
            return false;
        else return true;

    }

    /*
    *   checkControlAjacentDice
    * */

    public boolean chechControlAdjacentDice(Dice dice, Coordinates position){
        if (getDice(position.getX()-1,position.getY()).getValue()!=0||getDice(position.getX()-1,position.getY()-1).getValue()!=0||getDice(position.getX(),position.getY()-1).getValue()!=0)
            return true;
        else return false;

    }

}
