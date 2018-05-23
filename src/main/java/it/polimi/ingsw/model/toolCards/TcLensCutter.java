package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.*;

public class TcLensCutter extends ToolCard implements iTool {


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

       Dice diceFromRoundTrack= player.getCurrentGame().getRoundTrack()[coordinates.getX()];
       Dice flagDice=new Dice();
       flagDice=player.getChosenNut();
       player.setChosenNut(diceFromRoundTrack);
       player.getCurrentGame().getRoundTrack()[coordinates.getX()]=flagDice;


    }

    @Override
    public void useTool(Player player) {

    }

    @Override
    public void useTool(Player player, Action action) {

    }

    @Override
    public void useTool(Player player, Coordinates initialPosition, Coordinates finalPosition) {

    }

    @Override
    public void useTool(Player player, Coordinates initialPositionFirstNut, Coordinates finalPositionFirstNut, Coordinates initialPositionSecondNut, Coordinates finalPositionSecondNut) {

    }
}
