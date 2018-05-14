package it.polimi.ingsw.model;

import java.util.Arrays;

public class WindowPattern {

    private int favorTokenToAssign;
    private String name;
    private Dice[][] pattern;

    public WindowPattern(int favorTokenToAssign, String name) {
        this.favorTokenToAssign = favorTokenToAssign;
        this.name = name;
    }

    public WindowPattern(int favorTokenToAssign, String name, Dice[][] pattern) {
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

    public Dice[][] getPattern() {
        return pattern;
    }

    public void setPattern(Dice[][] pattern) {
        this.pattern = pattern;
    }

    public void setDicePosition (Dice dice,int i,int j){
        pattern[i][j]=dice;
    }

    @Override
    public String toString() {
        return "WindowPattern{" +
                "favorTokenToAssign=" + favorTokenToAssign +
                ", name='" + name + '\'' +
                ", pattern=" + Arrays.toString(pattern) +
                '}';
    }
}