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
        if(getDice(position.getY()-1,position.getX()).getValue()!=DEFAULT_VALUE||getDice(position.getY()-1,position.getX()-1).getValue()!=DEFAULT_VALUE||
                getDice(position.getY(),position.getX()-1).getValue()!=DEFAULT_VALUE|| getDice(position.getY()-1,position.getX()+1).getValue()!=DEFAULT_VALUE||
                getDice(position.getY(),position.getX()+1).getValue()!=DEFAULT_VALUE|| getDice(position.getY()+1,position.getX()).getValue()!=DEFAULT_VALUE||
                getDice(position.getY()+1,position.getX()+1).getValue()!=DEFAULT_VALUE|| getDice(position.getY()+1,position.getX()-1).getValue()!=DEFAULT_VALUE)
            return true;
        else return false;

    }
    public boolean borderAdjacent(Coordinates position){

        if(position.getX()==LEFT_BORDER) {
            if (getDice(position.getY() - 1, position.getX()).getValue() != DEFAULT_VALUE|| getDice(position.getY() - 1, position.getX() + 1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getY(), position.getX() + 1).getValue() != DEFAULT_VALUE || getDice(position.getY() + 1, position.getX()).getValue() != DEFAULT_VALUE ||
                    getDice(position.getY() + 1, position.getX() + 1).getValue() != DEFAULT_VALUE)
                return true;
        }
        if(position.getX()==RIGHT_BORDER){
            if (getDice(position.getY() - 1, position.getX()).getValue() != DEFAULT_VALUE || getDice(position.getY() - 1, position.getX() -1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getY(), position.getX() - 1).getValue() != DEFAULT_VALUE || getDice(position.getY() + 1, position.getX()).getValue() != DEFAULT_VALUE ||
                    getDice(position.getY() + 1, position.getX() -1).getValue() != DEFAULT_VALUE)
                return true;
        }
        if(position.getY()==UP_BORDER){
            if (getDice(position.getY() +1, position.getX()+1).getValue() != DEFAULT_VALUE || getDice(position.getY(), position.getX()+1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getY(), position.getX() - 1).getValue() != DEFAULT_VALUE || getDice(position.getY() + 1, position.getX()).getValue() != DEFAULT_VALUE ||
                    getDice(position.getY() + 1, position.getX() -1).getValue() != DEFAULT_VALUE)
                return true;

        }
        if(position.getY()==DOWN_BORDER){
            if (getDice(position.getY() - 1, position.getX()).getValue() != DEFAULT_VALUE || getDice(position.getY() - 1, position.getX() -1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getY(), position.getX() - 1).getValue() != DEFAULT_VALUE || getDice(position.getY(), position.getX()+1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getY()-1, position.getX()+1).getValue() != DEFAULT_VALUE)
                return true;
        }
        return false;
    }

    public boolean cornerAdjacent(Coordinates position){
        if(position.getY()==UP_BORDER&&position.getX()==LEFT_BORDER){
            if (getDice(position.getY(), position.getX()+1).getValue() != DEFAULT_VALUE || getDice(position.getY()+1, position.getX()+1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getY()+1, position.getX()).getValue() != DEFAULT_VALUE )
                return true;
        }
        if(position.getY()==UP_BORDER&&position.getX()==RIGHT_BORDER){
            if (getDice(position.getY(), position.getX()-1).getValue() != DEFAULT_VALUE || getDice(position.getY()+1, position.getX()-1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getY()+1, position.getX()).getValue() != DEFAULT_VALUE )
                return true;
        }
        if(position.getY()==DOWN_BORDER&&position.getX()==LEFT_BORDER){
            if (getDice(position.getY(), position.getX()+1).getValue() != DEFAULT_VALUE || getDice(position.getY()-1, position.getX()+1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getY()-1, position.getX()).getValue() != DEFAULT_VALUE )
                return true;
        }
        if(position.getY()==DOWN_BORDER&&position.getX()==RIGHT_BORDER){
            if (getDice(position.getY(), position.getX()-1).getValue() != DEFAULT_VALUE || getDice(position.getY()-1, position.getX()-1).getValue() != DEFAULT_VALUE ||
                    getDice(position.getY()-1, position.getX()).getValue() != DEFAULT_VALUE )
                return true;
        }
        return false;

    }

    /**
     * @param dice
     * @param position
     * @return
     */
    public boolean checkCornerOrthogonalColorAndValueAdjacency(Dice dice,Coordinates position){
        if(position.getY()==UP_BORDER&&position.getX()==LEFT_BORDER){
            if (getDice(position.getX(), position.getY()+1).getValue() == dice.getValue() ||
                    getDice(position.getY()+1, position.getX()).getValue() == dice.getValue()||getDice(position.getY(), position.getX()+1).getColor().equals(dice.getColor()) ||
                    getDice(position.getY()+1, position.getX()).getColor().equals(dice.getColor()))
                return false;
        }
        if(position.getY()==UP_BORDER&&position.getX()==RIGHT_BORDER) {
            if (getDice(position.getY(), position.getX() + 1).getValue() == dice.getValue() ||
                    getDice(position.getY(), position.getX() - 1).getValue() == dice.getValue() || getDice(position.getY(), position.getX() + 1).getColor().equals(dice.getColor()) ||
                    getDice(position.getY(), position.getX() - 1).getColor().equals(dice.getColor()))
                return false;
        }
        if(position.getY()==DOWN_BORDER&&position.getX()==RIGHT_BORDER) {
            if (getDice(position.getY()-1, position.getX()).getValue() == dice.getValue() ||
                    getDice(position.getY(), position.getX()-1 ).getValue() == dice.getValue() || getDice(position.getY()-1, position.getX()).getColor().equals(dice.getColor()) ||
                    getDice(position.getY(), position.getX() - 1).getColor().equals(dice.getColor()))
                return false;
        }
        if(position.getY()==DOWN_BORDER&&position.getX()==LEFT_BORDER) {
            if (getDice(position.getY()-1, position.getX()).getValue() == dice.getValue() ||
                    getDice(position.getY(), position.getX()+1).getValue() == dice.getValue() || getDice(position.getY()-1, position.getX()).getColor().equals(dice.getColor()) ||
                    getDice(position.getY(), position.getX()+1).getColor().equals(dice.getColor()))
                return false;
        }

        return true;

    }

    public boolean checkBorderOrthogonalColorAndValueAdjacency(Dice dice,Coordinates position){
        if(position.getX()==RIGHT_BORDER){
            if (getDice(position.getY(), position.getX()-1).getValue() == dice.getValue() || getDice(position.getY()-1, position.getX()).getValue() == dice.getValue() ||
                    getDice(position.getY()+1, position.getX()).getValue() == dice.getValue() || getDice(position.getY(), position.getX()-1).getColor().equals(dice.getColor()) ||
                    getDice(position.getY()-1, position.getX()).getColor().equals(dice.getColor())|| getDice(position.getY()+1, position.getX()).getColor().equals(dice.getColor()))
                return false;
        }
        if(position.getX()==LEFT_BORDER) {
            if (getDice(position.getY(), position.getX() + 1).getValue() == dice.getValue() || getDice(position.getY()-1, position.getX()).getValue() == dice.getValue() ||
                    getDice(position.getY()+1, position.getX()).getValue() == dice.getValue() || getDice(position.getY(), position.getX() + 1).getColor().equals(dice.getColor()) ||
                    getDice(position.getY()-1, position.getX()).getColor().equals(dice.getColor())|| getDice(position.getY()+1, position.getX()).getColor().equals(dice.getColor()))
                return false;
        }
        if(position.getY()==UP_BORDER) {
            if (getDice(position.getY(), position.getX()+1).getValue() == dice.getValue() || getDice(position.getY(), position.getX()-1).getValue() == dice.getValue() ||
                    getDice(position.getY()+1, position.getX()).getValue() == dice.getValue() || getDice(position.getY(), position.getX() + 1).getColor().equals(dice.getColor()) ||
                    getDice(position.getY(), position.getX()-1).getColor().equals(dice.getColor())|| getDice(position.getY()+1, position.getX()).getColor().equals(dice.getColor()))
                return false;
        }
        if(position.getY()==DOWN_BORDER) {
            if (getDice(position.getY(), position.getX()+1).getValue() == dice.getValue() || getDice(position.getY(), position.getX()-1).getValue() == dice.getValue() ||
                    getDice(position.getY()-1, position.getX()).getValue() == dice.getValue() || getDice(position.getY(), position.getX() + 1).getColor().equals(dice.getColor()) ||
                    getDice(position.getY(), position.getX()-1).getColor().equals(dice.getColor())|| getDice(position.getY()-1, position.getX()).getColor().equals(dice.getColor()))
                return false;
        }

        return true;


    }


    public boolean checkCentralOrthogonalColorAndValueAdjacency(Dice dice,Coordinates position){
        if(position.getX()==RIGHT_BORDER){
            if (getDice(position.getY(), position.getX()-1).getValue() == dice.getValue() || getDice(position.getY(), position.getX()+1).getValue() == dice.getValue() ||
                    getDice(position.getY()-1, position.getX()).getValue() == dice.getValue() || getDice(position.getY()+1, position.getX()).getValue() == dice.getValue() ||
                    getDice(position.getY(), position.getX()-1).getColor().equals(dice.getColor()) || getDice(position.getY(), position.getX() + 1).getColor().equals(dice.getColor())||
                    getDice(position.getY()-1, position.getX()).getColor().equals(dice.getColor())|| getDice(position.getY()+1, position.getX()).getColor().equals(dice.getColor()))
                return false;
        }
        return true;

    }



    /*
    *   checkPositionDice:
    *   control of the color and the value of the orthogonally adjacent dice
    * */

    public boolean checkPositionDice(Dice dice,Coordinates position){
        if(position.getX()==LEFT_BORDER)
            if(position.getY()==UP_BORDER||position.getY()==DOWN_BORDER)
                return checkCornerOrthogonalColorAndValueAdjacency(dice,position);
            else return checkBorderOrthogonalColorAndValueAdjacency(dice,position);
        if(position.getX()==RIGHT_BORDER)
            if(position.getY()==UP_BORDER||position.getY()==DOWN_BORDER)
                return checkCornerOrthogonalColorAndValueAdjacency(dice,position);
            else return checkBorderOrthogonalColorAndValueAdjacency(dice,position);
        if(position.getY()==UP_BORDER||position.getY()==DOWN_BORDER)
            return checkBorderOrthogonalColorAndValueAdjacency(dice,position);

        else return checkCentralOrthogonalColorAndValueAdjacency(dice,position);

    }


    /**
     * checkControlAdjacentDic
     * @param dice
     * @param position
     * @return
     */
    public boolean checkControlAdjacentDice(Dice dice, Coordinates position){

        if(position.getX()==LEFT_BORDER)
            if(position.getY()==UP_BORDER||position.getY()==DOWN_BORDER)
                return cornerAdjacent(position);
            else return borderAdjacent(position);
        if(position.getX()==RIGHT_BORDER)
            if(position.getY()==UP_BORDER||position.getY()==DOWN_BORDER)
                return cornerAdjacent(position);
            else return borderAdjacent(position);
         if(position.getY()==UP_BORDER||position.getY()==DOWN_BORDER)
             return borderAdjacent(position);

         else return centralAdjacent(position);


    }

}
