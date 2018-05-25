package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.Arrays;

public class WindowPattern implements Serializable {

    private int favorTokenToAssign;
    private String name;
    private Frame pattern;


    public WindowPattern(int favorTokenToAssign, String name) {
        this.favorTokenToAssign = favorTokenToAssign;
        this.name = name;
    }

    public WindowPattern(int favorTokenToAssign, String name, Frame pattern) {
        this.favorTokenToAssign = favorTokenToAssign;
        this.name = name;
        this.pattern = pattern;
    }

    public int getFavorTokenToAssign() {
        return favorTokenToAssign;
    }

    public void setFavorTokenToAssign(int favorTokenToAssign) {
        this.favorTokenToAssign = favorTokenToAssign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Frame getPattern() {
        return pattern;
    }

    public void setPattern(Frame pattern) {
        this.pattern = pattern;
    }

    public void setDicePosition (Dice dice, int i, int j){
        pattern.setPositionDice(dice,i,j);
    }
    /*
    * returns the constraint(dice) that is in place
    * */
    public Dice getDicePosition(Coordinates xy){
        return this.pattern.getDice(xy);

    }

    @Override
    public String toString() {
        return "WindowPattern{" +
                "favorTokenToAssign=" + favorTokenToAssign +
                ", name='" + name + '\'' +
                ", pattern=" + pattern +
                '}';
    }
}
