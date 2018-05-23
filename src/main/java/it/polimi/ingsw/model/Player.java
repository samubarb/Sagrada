package it.polimi.ingsw.model;


import it.polimi.ingsw.model.exceptions.*;
import it.polimi.ingsw.model.toolCards.ToolCard;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{

    private String name;
    private Color color;
    private int favorTokens;
    private int finalPoints;
    private Frame frame;
    private WindowPattern windowPattern;
    private ArrayList<PrivateObjective> privateObjective;
    private Game currentGame;
    private Dice chosenNut;

    /*
     *constructor of default
     */

    public Player() {
    }


    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.frame = new Frame();
        this.windowPattern = null;
        this.privateObjective = new ArrayList<PrivateObjective>();
        this.chosenNut=new Dice();
    }

    public Player(String name, Color color, Frame frame, WindowPattern windowPattern) {
        this.name = name;
        this.color = color;
        this.frame = frame;
        this.windowPattern = windowPattern;
        this.chosenNut=new Dice();
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

    public Dice getChosenNut() {
        return chosenNut;
    }

    public void setChosenNut(Dice chosenNut) {
        this.chosenNut = chosenNut;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    /*
    * checkFavorTokenPlayer:
    * check if the tool card has already been used and check if the player has enough favor tokens
     */

    public boolean checkFavorTokenPlayer(ToolCard toolCard) throws FavorTokenException {
        if(toolCard.getAlreadyUsed())
            if(getFavorTokens()>=2){
                setFavorTokens(getFavorTokens()-2);
                return true;}
            else throw new FavorTokenException();
        else if (getFavorTokens()>=1)
            setFavorTokens(getFavorTokens()-1);
            else throw new FavorTokenException();
            toolCard.setAlreadyUsed(true);
            return true;

    }

    /*
     * checkWindowPatternColorRestriction:
     * check if the chosen nut respects the color restriction
     */

    public boolean checkWindowPatternColorRestriction(Dice dice,Coordinates position){
        if(getWindowPattern().getDicePosition(position).getColor().equals(Color.UNCOLORED))
            return true;
        else if(getWindowPattern().getDicePosition(position).getColor().equals(dice.getColor()))
                return true;
            else return false;

    }

    /*
     * checkWindowPatternValueRestriction:
     * check if the chosen nut respects the value restriction
     */

    public boolean checkWindowPatternValueRestriction(Dice dice, Coordinates position){
        if(getWindowPattern().getDicePosition(position).getValue()==0)
            return true;
        else if(getWindowPattern().getDicePosition(position).getValue()==dice.getValue())
            return true;
        else return false;

    }

    /**
     * @param dice
     * @param position
     * @return
     */
    public boolean checkAdjacentDice(Dice dice, Coordinates position){
        return this.frame.checkControlAdjacentDice(dice,position);

    }



    /**
     * @param dice die to be positioned
     * @param position coordinates of the die position
     * @return true if control has passed, otherwise false
     */
    public boolean checkFrameValueAndColorRestriction(Dice dice,Coordinates position){
        return this.frame.checkPositionDice(dice,position);

    }

    /**
     * check if the position is free
     * @param position
     * @return
     */
    public boolean checkIfThePositionIsFree(Coordinates position){
        if(getFrame().getDice(position).getValue()==0&&getFrame().getDice(position).getColor().equals(Color.UNCOLORED))
            return true;
        else return false;
    }

    /**
     * @param dice die to be positioned
     * @param position coordinates of the die position
     * @return it returns true if it has passed the controls and the die is positioned
     * @throws WindowPatternColorException
     * @throws WindowPatternValueException
     * @throws FrameValueAndColorException
     */
    public boolean positionDice(Dice dice, Coordinates position) throws WindowPatternColorException,WindowPatternValueException,FrameValueAndColorException,BusyPositionException {
        if(!checkIfThePositionIsFree(position))
            throw new BusyPositionException();
        if(!checkWindowPatternColorRestriction(dice,position))
            throw new WindowPatternColorException();
        if(!checkWindowPatternValueRestriction(dice,position))
            throw new WindowPatternValueException();
        if(!checkAdjacentDice(dice,position))
            throw new WindowPatternValueException();
        if(!checkFrameValueAndColorRestriction(dice,position))
            throw new FrameValueAndColorException();
        else {
            this.frame.setPositionDice(dice, position);
            return true;
        }

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
