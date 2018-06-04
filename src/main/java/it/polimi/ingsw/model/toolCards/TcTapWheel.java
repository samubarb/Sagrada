package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.*;

public class TcTapWheel extends ToolCard implements iTool {

    public TcTapWheel(int number, String name, Color color) {
        super(number, name, color);
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
    public void useTool(Player player, Action action) throws NutChosenWrongException {
        //this method is not used

    }

    @Override
    public void useTool(Player player, Coordinates initialPosition, Coordinates finalPosition) {
        //this method is not used

    }

    @Override
    public void useTool(Player player, Coordinates initialPositionFirstNut, Coordinates finalPositionFirstNut, Coordinates initialPositionSecondNut, Coordinates finalPositionSecondNut) throws FrameValueAndColorException, WindowPatternValueException, WindowPatternColorException, BusyPositionException,AdjacentDiceException{
       if(!player.getFrame().getDice(initialPositionFirstNut).getColor().equals(player.getFrame().getDice(initialPositionSecondNut).getColor())||
          !player.getCurrentGame().checkColorDice(player.getFrame().getDice(initialPositionFirstNut)))
           throw new IllegalArgumentException();
       else {
           Dice dice = new Dice();
           if (finalPositionFirstNut.equals(initialPositionSecondNut)) {
               dice = player.getFrame().getDice(initialPositionSecondNut);
               player.getFrame().setPositionDice(new Dice(), initialPositionSecondNut);
               player.positionDice(player.getFrame().getDice(initialPositionFirstNut), finalPositionFirstNut);
               player.getFrame().setPositionDice(new Dice(), initialPositionFirstNut);
               player.positionDice(dice, finalPositionSecondNut);
           } else {

               player.positionDice(player.getFrame().getDice(initialPositionFirstNut), finalPositionFirstNut);
               player.getFrame().setPositionDice(new Dice(), initialPositionFirstNut);
               player.positionDice(player.getFrame().getDice(initialPositionSecondNut), finalPositionSecondNut);
               player.getFrame().setPositionDice(new Dice(), initialPositionSecondNut);
           }
       }
    }
}
