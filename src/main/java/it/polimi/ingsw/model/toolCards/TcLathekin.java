package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.*;

public class TcLathekin extends ToolCard implements iTool {

    public TcLathekin(int number, String name, Color color) {
        super(number, name, color);
    }

    @Override
    public void useTool(Player player) {

    }

    @Override
    public void useTool(Player player, Coordinates coordinates) {

    }

    @Override
    public void useTool(Player player, Action action) throws NutChosenWrongException {

    }

    @Override
    public void useTool(Player player, Coordinates initialPosition, Coordinates finalPosition) {

    }

    /**
     *
     * move exactly two dice, obeying all placement restriction
     * @param player
     * @param initialPositionFirstNut
     * @param finalPositionFirstNut
     * @param initialPositionSecondNut
     * @param finalPositionSecondNut
     * @throws FrameValueAndColorException
     * @throws WindowPatternValueException
     * @throws WindowPatternColorException
     * @throws BusyPositionException
     */
    @Override
    public void useTool(Player player, Coordinates initialPositionFirstNut, Coordinates finalPositionFirstNut, Coordinates initialPositionSecondNut, Coordinates finalPositionSecondNut) throws FrameValueAndColorException, WindowPatternValueException, WindowPatternColorException, BusyPositionException {
        Dice dice=new Dice();
        if(finalPositionFirstNut.equals(initialPositionSecondNut)){
            dice=player.getFrame().getDice(initialPositionSecondNut);
            player.getFrame().setPositionDice(new Dice(),initialPositionSecondNut);
            player.positionDice(player.getFrame().getDice(initialPositionFirstNut),finalPositionFirstNut);
            player.getFrame().setPositionDice(new Dice(),initialPositionFirstNut);
            player.positionDice(dice,finalPositionSecondNut);
        }
        else {

            player.positionDice(player.getFrame().getDice(initialPositionFirstNut), finalPositionFirstNut);
            player.getFrame().setPositionDice(new Dice(), initialPositionFirstNut);
            player.positionDice(player.getFrame().getDice(initialPositionSecondNut), finalPositionSecondNut);
            player.getFrame().setPositionDice(new Dice(), initialPositionSecondNut);
        }
    }
}