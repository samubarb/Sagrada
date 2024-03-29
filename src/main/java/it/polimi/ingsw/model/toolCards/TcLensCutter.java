package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.*;

public class TcLensCutter extends ToolCard implements iTool {
    private final static int FIRST_ROUND=1;


    public TcLensCutter(int number, String name,Color color) {
        super(number, name,color);
    }

    /**
     * after drafting, swap the drafted die with a die from the roundTrack
     * @param player
     * @param coordinates
     */
    @Override
    public void useTool(Player player, Coordinates coordinates) {
       if(player.getCurrentGame().getRoundTrack()[coordinates.getX()].getColor()==Color.UNCOLORED||player.getCurrentGame().getRound()==FIRST_ROUND)
           throw new IllegalArgumentException();
       Dice diceFromRoundTrack= player.getCurrentGame().getRoundTrack()[coordinates.getX()];
       Dice flagDice=new Dice();
       flagDice=player.getChosenNut();
       player.setChosenNut(diceFromRoundTrack);
       player.getCurrentGame().getRoundTrack()[coordinates.getX()]=flagDice;


    }

    @Override
    public void useTool(Player player) {
        //this method is not used

    }

    @Override
    public void useTool(Player player, Action action) {
        //this method is not used

    }

    @Override
    public void useTool(Player player, Coordinates initialPosition, Coordinates finalPosition) {
        //this method is not used

    }

    @Override
    public void useTool(Player player, Coordinates initialPositionFirstNut, Coordinates finalPositionFirstNut, Coordinates initialPositionSecondNut, Coordinates finalPositionSecondNut) {
        //this method is not used

    }
}
