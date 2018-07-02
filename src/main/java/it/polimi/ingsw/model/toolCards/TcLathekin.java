package it.polimi.ingsw.model.toolCards;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.*;

public class TcLathekin extends ToolCard implements iTool {

    public TcLathekin(int number, String name, Color color) {
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
    public void useTool(Player player, Coordinates initialPositionFirstNut, Coordinates finalPositionFirstNut, Coordinates initialPositionSecondNut, Coordinates finalPositionSecondNut) throws FrameValueAndColorException, WindowPatternValueException, WindowPatternColorException, BusyPositionException,AdjacentDiceException {
        Player fakePlayer=new Player("fake");
        fakePlayer.setWindowPattern(player.getWindowPattern());
        fakePlayer.getFrame().copyFrame(player.getFrame());
        fakePlayer.getFrame().setPositionDice(new Dice(),initialPositionFirstNut);
        fakePlayer.getFrame().setPositionDice(new Dice(),initialPositionSecondNut);
        fakePlayer.positionDice(player.getFrame().getDice(initialPositionFirstNut),finalPositionFirstNut);
        fakePlayer.positionDice(player.getFrame().getDice(initialPositionSecondNut),finalPositionSecondNut);
        player.setFrame(fakePlayer.getFrame());

      /*  if(finalPositionFirstNut.equals(initialPositionSecondNut)){
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
        }*/
    }
}
