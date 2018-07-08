package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.NutChosenWrongException;

public class TcFluxRemover extends ToolCard implements iTool{
    private int counter;

    public TcFluxRemover(int number, String name, Color color) {
        super(number, name, color);
        this.counter=90;
    }

    /**
     * after drafting, return the die to the dice bag(rolledDice) and pull 1 die from the bag
     * @param player
     */
    @Override
    public void useTool(Player player) {

        Dice diceFromRolledDice= player.getCurrentGame().getRolledDice()[this.counter-1];
        Dice flagDice=new Dice();
        flagDice=player.getChosenNut();
        player.setChosenNut(diceFromRolledDice);
        player.getCurrentGame().getRolledDice()[counter-1]=flagDice;

    }

    @Override
    public void useTool(Player player, Coordinates coordinates) {
        //this method is not used

    }

    @Override
    public void useTool(Player player, Action action) throws NutChosenWrongException {
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
