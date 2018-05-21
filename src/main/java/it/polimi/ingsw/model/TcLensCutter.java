package it.polimi.ingsw.model;

import java.io.Serializable;

public class TcLensCutter extends ToolCard implements iTool {


    public TcLensCutter(int number, String name) {
        super(number, name);
    }

    @Override
    public void useTool(Player player,Coordinates coordinates) {

       Dice diceFromRoundTrack= player.getCurrentGame().getRoundTrack()[coordinates.getX()];
       Dice flagDice=new Dice();
       flagDice=player.getChosenNut();
       player.setChosenNut(diceFromRoundTrack);
       player.getCurrentGame().getRoundTrack()[coordinates.getX()]=flagDice;


    }

    @Override
    public void useTool(Player player) {

    }
}
