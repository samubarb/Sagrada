package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.NutChosenWrongException;

public class TcGrozingPliers extends ToolCard implements iTool {

    public TcGrozingPliers(int number, String name, Color color) {
        super(number, name, color);
    }

    /**
     * after drafting increase o decrease the value of the drafted die by 1
     * @param player
     * @param action
     * @throws NutChosenWrongException throw exception if you can not increase or decrease
     */
    @Override
    public void useTool(Player player, Action action)throws NutChosenWrongException {
        if(action.equals(Action.INCREASE)&&player.getChosenNut().getValue()==6)
            throw new NutChosenWrongException();
        if(action.equals(Action.DECREASE)&&player.getChosenNut().getValue()==1)
            throw new NutChosenWrongException();
        if(action.equals(Action.INCREASE))

            player.getChosenNut().setValue(player.getChosenNut().getValue()+1);
        else
            player.getChosenNut().setValue(player.getChosenNut().getValue()-1);



    }

    @Override
    public void useTool(Player player) {
        //this method is not used

    }

    @Override
    public void useTool(Player player, Coordinates coordinates) {
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
