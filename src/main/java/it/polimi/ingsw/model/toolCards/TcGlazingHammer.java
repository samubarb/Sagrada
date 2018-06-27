package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.*;

import java.util.Random;

public class TcGlazingHammer extends ToolCard implements iTool {

    public TcGlazingHammer(int number, String name,Color color) {
        super(number, name,color);
    }

    public void toolRandomDice(Dice[] dice){
        Random random=new Random();

        for(int i=0;i< dice.length;i++)
            if(dice[i].getValue()!=0)
                dice[i].setValue(random.nextInt(6)+1);


    }

    /**
     * Re-roll all dice in the currentDice array
     * @param player
     */
    @Override
    public void useTool(Player player) {
        toolRandomDice(player.getCurrentGame().getCurrentDice());


    }

    @Override
    public void useTool(Player player, Coordinates coordinates) {
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
