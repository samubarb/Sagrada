package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.exceptions.*;
import it.polimi.ingsw.model.toolCards.TcCopperFoilBurnisher;
import it.polimi.ingsw.model.toolCards.TcLathekin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TcLathekinTest {

    @Test
    public void testLathkin(){
        Game gameTest=new Game();
        Frame testFrame=new Frame();
        Dice testDice1=new Dice(Color.RED,6);
        Dice testDice2=new Dice(Color.RED,3);
        Dice testDice3=new Dice(Color.BLUE ,1);
        Dice testDice4=new Dice(Color.YELLOW,4);
        testFrame.setPositionDice(testDice1,0,0);
        testFrame.setPositionDice(testDice2,0,1);
        testFrame.setPositionDice(testDice3,1,0);
        testFrame.setPositionDice(testDice4,1,1);
        WindowPattern testWindow=new WindowPattern(5,"prova");
        testWindow.setDicePosition(new Dice(Color.RED,0),2,0);
        Player playerOne=new Player("gianfranco", Color.RED,testFrame,testWindow);
        gameTest.setAddPlayer(playerOne);
        GameConfigurator gameConfigurator=new GameConfigurator(gameTest);
        playerOne.setWindowPattern(testWindow);
        playerOne.setFrame(testFrame);

        TcLathekin tcCard=new TcLathekin(1,"grozingPliers",Color.BLUE);
        System.out.println(playerOne.getWindowPattern().getDicePosition(new Coordinates(0,2)).getValue());

        gameTest.getCurrentPlayer();
        System.out.println(gameTest.getTurnOrder()[0].getName());
        assertTrue(gameTest.getTurnOrder().length==1);
        System.out.println(playerOne.getWindowPattern().getDicePosition(new Coordinates(0,2)).getColor());////
        System.out.println(playerOne.getWindowPattern().getDicePosition(new Coordinates(0,2)).getValue());
        System.out.println(playerOne.getFrame().getDice(1,1).getValue());
        System.out.println(playerOne.getFrame().getDice(1,1).getColor());
        System.out.println(playerOne.getFrame().getDice(2,0).getValue());
        System.out.println(playerOne.getFrame().getDice(2,0).getColor());
        try {
            tcCard.useTool(playerOne,new Coordinates(1,0),new Coordinates(0,2),new Coordinates(1,1),new Coordinates(1,0));
        } catch (FrameValueAndColorException e) {
            e.printStackTrace();
        } catch (WindowPatternValueException e) {
            e.printStackTrace();
        } catch (WindowPatternColorException e) {
            e.printStackTrace();
        } catch (BusyPositionException e) {
            e.printStackTrace();
        } catch (AdjacentDiceException e) {
            e.printStackTrace();
        }
        System.out.println("toolcard usata");
        System.out.println(playerOne.getFrame().getDice(0,1).getValue());
        System.out.println(playerOne.getFrame().getDice(0,1).getColor());
        System.out.println(playerOne.getFrame().getDice(1,1).getValue());
        System.out.println(playerOne.getFrame().getDice(1,1).getColor());
        System.out.println(playerOne.getFrame().getDice(2,0).getValue());
        System.out.println(playerOne.getFrame().getDice(2,0).getColor());
    }
}
