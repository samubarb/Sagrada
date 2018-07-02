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
    private PrivateObjective privateObjective;
    private Game currentGame;
    private Dice chosenNut;
    private boolean firstDice=true;
    private int previousPlayerToken;
    public static final int DEFAUTL_VALUE = 0;
    public static final int LINE_SIZE=4;
    public static final int COLUMN_SIZE=5;

    /*
     *constructor of default
     */

    public Player() {

    }

    public Player(String name) {
        this.name = name;
        this.color = Color.UNCOLORED;
        this.frame = new Frame();
        this.windowPattern = null;
        this.privateObjective = new PrivateObjective();
        this.chosenNut=new Dice();
        this.previousPlayerToken=0;
    }

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.frame = new Frame();
        this.windowPattern = null;
        this.privateObjective = new PrivateObjective();
        this.chosenNut=new Dice();
        this.previousPlayerToken=0;
    }

    public Player(String name, Color color, Frame frame, WindowPattern windowPattern) {
        this.name = name;
        this.color = color;
        this.frame = frame;
        this.windowPattern = windowPattern;
        this.chosenNut=new Dice();
        this.previousPlayerToken=0;
    }

    /**
     * get the player's name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set the player's name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the player's color
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * set the player's color
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the player's favor token
     */
    public int getFavorTokens() {
        return favorTokens;
    }

    /**
     * @param favorTokens
     */
    public void setFavorTokens(int favorTokens) {
        this.favorTokens = favorTokens;
    }

    /**
     * @return the final points
     */
    public int getFinalPoints() {
        return finalPoints;
    }

    /**
     * set the final points
     * @param finalPoints
     */
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

    public PrivateObjective getPrivateObjective() {
        return privateObjective;
    }

    public void setPrivateObjective(PrivateObjective privateObjective) {
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

    public void addFinalPoints(int points){
        this.finalPoints+=points;

    }

    public int getPreviousPlayerToken() {
        return previousPlayerToken;
    }

    /**
     * copy favor token before using the tool card
     * @param previousPlayerToken
     */
    public void setPreviousPlayerToken(int previousPlayerToken) {
        this.previousPlayerToken = previousPlayerToken;
    }

    /**
     * -1 for each open space in the frame
     * @return
     */
    public int playerFramePoints(){
        int points=0;
        for(int i=0;i<LINE_SIZE;i++)
            for(int j=0;j<COLUMN_SIZE;j++)
                if(this.frame.getDice(i,j).getColor().equals(Color.UNCOLORED))
                    points++;
        return -points;
    }

    /*
    * checkFavorTokenPlayer:
    * check if the tool card has already been used and check if the player has enough favor tokens
     */

    public boolean checkFavorTokenPlayer(ToolCard toolCard) throws FavorTokenException {
        previousPlayerToken=getFavorTokens();
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

    /**
     * if the toolCard is not used, reset the favor token
     * @param toolCard
     */
    public void restoreFavorTokenPlayer(ToolCard toolCard){

        if(getPreviousPlayerToken()-getFavorTokens()==1)
            toolCard.setAlreadyUsed(false);
        setFavorTokens(getPreviousPlayerToken());


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
        if(getWindowPattern().getDicePosition(position).getValue()==DEFAUTL_VALUE)
            return true;
        if(getWindowPattern().getDicePosition(position).getValue()==dice.getValue())
            return true;
        else return false;


    }

    /**
     * @param dice to be place
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
    public boolean positionDice(Dice dice, Coordinates position) throws WindowPatternColorException, WindowPatternValueException, FrameValueAndColorException, BusyPositionException, AdjacentDiceException {
        if(firstDice)
            if(!(position.getY()==DEFAUTL_VALUE || position.getY()==LINE_SIZE-1||position.getX()==DEFAUTL_VALUE||position.getX()==COLUMN_SIZE-1))
                throw new AdjacentDiceException();

        if(!checkIfThePositionIsFree(position))
            throw new BusyPositionException();
        if(!checkWindowPatternColorRestriction(dice,position))
            throw new WindowPatternColorException();
        if(!checkWindowPatternValueRestriction(dice,position))
            throw new WindowPatternValueException();
        if(!checkAdjacentDice(dice,position)&&!firstDice)
            throw new AdjacentDiceException();
        if(!checkFrameValueAndColorRestriction(dice,position))
            throw new FrameValueAndColorException();
        else {
            this.frame.setPositionDice(dice, position);
            firstDice=false;
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
