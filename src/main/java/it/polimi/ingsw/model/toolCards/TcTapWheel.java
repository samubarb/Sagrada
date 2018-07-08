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

    /**
     *
     * move up to two dice of the same color that match the color o a die on the round track
     * @param player
     * @param initialPositionFirstNut
     * @param finalPositionFirstNut
     * @param initialPositionSecondNut
     * @param finalPositionSecondNut
     * @throws FrameValueAndColorException
     * @throws WindowPatternValueException
     * @throws WindowPatternColorException
     * @throws BusyPositionException
     * @throws AdjacentDiceException
     */
    @Override
    public void useTool(Player player, Coordinates initialPositionFirstNut, Coordinates finalPositionFirstNut, Coordinates initialPositionSecondNut, Coordinates finalPositionSecondNut) throws FrameValueAndColorException, WindowPatternValueException, WindowPatternColorException, BusyPositionException,AdjacentDiceException{
       if(!player.getFrame().getDice(initialPositionFirstNut).getColor().equals(player.getFrame().getDice(initialPositionSecondNut).getColor())||
          !player.getCurrentGame().checkColorDice(player.getFrame().getDice(initialPositionFirstNut)))
           throw new IllegalArgumentException();
       else {
           Player fakePlayer=new Player("fake");
           fakePlayer.setWindowPattern(player.getWindowPattern());
           fakePlayer.getFrame().copyFrame(player.getFrame());
           fakePlayer.getFrame().setPositionDice(new Dice(),initialPositionFirstNut);
           fakePlayer.getFrame().setPositionDice(new Dice(),initialPositionSecondNut);
           fakePlayer.positionDice(player.getFrame().getDice(initialPositionFirstNut),finalPositionFirstNut);
           fakePlayer.positionDice(player.getFrame().getDice(initialPositionSecondNut),finalPositionSecondNut);
           player.setFrame(fakePlayer.getFrame());
       }
    }
}
