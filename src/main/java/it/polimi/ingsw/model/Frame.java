package it.polimi.ingsw.model;


import java.io.Serializable;

public class Frame implements Serializable {

    public static final int LINE_SIZE=4;
    public static final int COLUMN_SIZE=5;
    public static final int LEFT_BORDER=0;
    public static final int RIGHT_BORDER=4;
    public static final int UP_BORDER=0;
    public static final int DOWN_BORDER=3;
    public static final int DEFAULT_VALUE=0;


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

    public boolean centralAdjacent(Coordinates position){
        if(getDice(position.getX()-1,position.getY()).getValue()!=DEFAULT_VALUE||getDice(position.getX()-1,position.getY()-1).getValue()!=DEFAULT_VALUE||
                getDice(position.getX(),position.getY()-1).getValue()!=DEFAULT_VALUE|| getDice(position.getX()-1,position.getY()+1).getValue()!=DEFAULT_VALUE||
                getDice(position.getX(),position.getY()+1).getValue()!=DEFAULT_VALUE|| getDice(position.getX()+1,position.getY()).getValue()!=DEFAULT_VALUE||
                getDice(position.getX()+1,position.getY()+1).getValue()!=DEFAULT_VALUE|| getDice(position.getX()+1,position.getY()-1).getValue()!=DEFAULT_VALUE)
            return true;
        else return false;

    }
    public boolean borderAdjacent(Coordinates position){

        if(position.getY()==LEFT_BORDER) {
            if (getDice(position.getX() - 1, position.getY()).getValue() != DEFAULT_VALUE|| getDice(position.getX() - 1, position.getY() + 1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getX(), position.getY() + 1).getValue() != DEFAULT_VALUE || getDice(position.getX() + 1, position.getY()).getValue() != DEFAULT_VALUE ||
                    getDice(position.getX() + 1, position.getY() + 1).getValue() != DEFAULT_VALUE)
                return true;
        }
        if(position.getY()==RIGHT_BORDER){
            if (getDice(position.getX() - 1, position.getY()).getValue() != DEFAULT_VALUE || getDice(position.getX() - 1, position.getY() -1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getX(), position.getY() - 1).getValue() != DEFAULT_VALUE || getDice(position.getX() + 1, position.getY()).getValue() != DEFAULT_VALUE ||
                    getDice(position.getX() + 1, position.getY() -1).getValue() != DEFAULT_VALUE)
                return true;
        }
        if(position.getX()==UP_BORDER){
            if (getDice(position.getX() +1, position.getY()+1).getValue() != DEFAULT_VALUE || getDice(position.getX(), position.getY()+1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getX(), position.getY() - 1).getValue() != DEFAULT_VALUE || getDice(position.getX() + 1, position.getY()).getValue() != DEFAULT_VALUE ||
                    getDice(position.getX() + 1, position.getY() -1).getValue() != DEFAULT_VALUE)
                return true;

        }
        if(position.getX()==DOWN_BORDER){
            if (getDice(position.getX() - 1, position.getY()).getValue() != DEFAULT_VALUE || getDice(position.getX() - 1, position.getY() -1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getX(), position.getY() - 1).getValue() != DEFAULT_VALUE || getDice(position.getX(), position.getY()+1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getX()-1, position.getY()+1).getValue() != DEFAULT_VALUE)
                return true;
        }
        return false;
    }

    public boolean cornerAdjacent(Coordinates position){
        if(position.getX()==UP_BORDER&&position.getY()==LEFT_BORDER){
            if (getDice(position.getX(), position.getY()+1).getValue() != DEFAULT_VALUE || getDice(position.getX()+1, position.getY()+1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getX()+1, position.getY()).getValue() != DEFAULT_VALUE )
                return true;
        }
        if(position.getX()==UP_BORDER&&position.getY()==RIGHT_BORDER){
            if (getDice(position.getX(), position.getY()-1).getValue() != DEFAULT_VALUE || getDice(position.getX()+1, position.getY()-1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getX()+1, position.getY()).getValue() != DEFAULT_VALUE )
                return true;
        }
        if(position.getX()==DOWN_BORDER&&position.getY()==LEFT_BORDER){
            if (getDice(position.getX(), position.getY()+1).getValue() != DEFAULT_VALUE || getDice(position.getX()-1, position.getY()+1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getX()-1, position.getY()).getValue() != DEFAULT_VALUE )
                return true;
        }
        if(position.getX()==DOWN_BORDER&&position.getY()==RIGHT_BORDER){
            if (getDice(position.getX(), position.getY()-1).getValue() != DEFAULT_VALUE || getDice(position.getX()-1, position.getY()-1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getX()-1, position.getY()).getValue() != DEFAULT_VALUE )
                return true;
        }
        return false;




    }

    public boolean checkCornerOrthogonalColorAndValueAdjacency(Dice dice,Coordinates position){
        if(position.getX()==UP_BORDER&&position.getY()==LEFT_BORDER){
            if (getDice(position.getX(), position.getY()+1).getValue() == dice.getValue() ||
                    getDice(position.getX()+1, position.getY()).getValue() == dice.getValue()||getDice(position.getX(), position.getY()+1).getColor().equals(dice.getColor()) ||
                    getDice(position.getX()+1, position.getY()).getColor().equals(dice.getColor()))
                return false;
        }
        if(position.getX()==UP_BORDER&&position.getY()==RIGHT_BORDER) {
            if (getDice(position.getX(), position.getY() + 1).getValue() == dice.getValue() ||
                    getDice(position.getX(), position.getY() - 1).getValue() == dice.getValue() || getDice(position.getX(), position.getY() + 1).getColor().equals(dice.getColor()) ||
                    getDice(position.getX(), position.getY() - 1).getColor().equals(dice.getColor()))
                return false;
        }
        if(position.getX()==DOWN_BORDER&&position.getY()==RIGHT_BORDER) {
            if (getDice(position.getX()-1, position.getY()).getValue() == dice.getValue() ||
                    getDice(position.getX(), position.getY()-1 ).getValue() == dice.getValue() || getDice(position.getX()-1, position.getY()).getColor().equals(dice.getColor()) ||
                    getDice(position.getX(), position.getY() - 1).getColor().equals(dice.getColor()))
                return false;
        }
        if(position.getX()==DOWN_BORDER&&position.getY()==LEFT_BORDER) {
            if (getDice(position.getX()-1, position.getY()).getValue() == dice.getValue() ||
                    getDice(position.getX(), position.getY()+1).getValue() == dice.getValue() || getDice(position.getX()-1, position.getY()).getColor().equals(dice.getColor()) ||
                    getDice(position.getX(), position.getY()+1).getColor().equals(dice.getColor()))
                return false;
        }
        

        return false;




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
    *   checkControlAdjacentDice
    * */

    public boolean checkControlAdjacentDice(Dice dice, Coordinates position){

        if(position.getY()==LEFT_BORDER)
            if(position.getX()==UP_BORDER||position.getX()==DOWN_BORDER)
                return cornerAdjacent(position);
            else return borderAdjacent(position);
        if(position.getY()==RIGHT_BORDER)
            if(position.getX()==UP_BORDER||position.getX()==DOWN_BORDER)
                return cornerAdjacent(position);
            else return borderAdjacent(position);
         if(position.getX()==UP_BORDER)
             return borderAdjacent(position);
         if(position.getX()==DOWN_BORDER)
             return borderAdjacent(position);
         else return centralAdjacent(position);


    }

}
