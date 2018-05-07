package it.polimi.ingsw.model;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    private String name;
    private Color color;
    private int favorTokens;
    private int finalPoints;
    private Frame frame;
    private WindowPattern windowPattern;
    private ArrayList<PrivateObjective> privateObjective;

    /*
     *constructor of default
     */

    public Player(String name, Color color, Frame frame, WindowPattern windowPattern) {
        this.name = name;
        this.color = color;
        this.frame = frame;
        this.windowPattern = windowPattern;
        this.privateObjective = new ArrayList<PrivateObjective>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getFavorTokens() {
        return favorTokens;
    }

    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }

    public int getFinalPoints() {
        return finalPoints;
    }

    public void setFinalPoints(int finalPoints) {
        this.finalPoints = finalPoints;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public WindowPattern getWindowPattern() {
        return windowPattern;
    }

    public void setWindowPattern(WindowPattern windowPattern) {
        this.windowPattern = windowPattern;
    }

    public ArrayList<PrivateObjective> getPrivateObjective() {
        return privateObjective;
    }

    public void setPrivateObjective(ArrayList<PrivateObjective> privateObjective) {
        this.privateObjective = privateObjective;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", favorTokens=" + favorTokens +
                ", finalPoints=" + finalPoints +
                ", frame=" + frame +
                ", windowPattern=" + windowPattern +
                ", privateObjective=" + privateObjective +
                '}';
    }
}
