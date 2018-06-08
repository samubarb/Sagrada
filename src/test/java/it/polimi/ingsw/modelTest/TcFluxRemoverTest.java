package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.toolCards.TcFluxRemover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TcFluxRemoverTest {

    @Test
    public void testFluxRemover(){
        Game gameTest=new Game();
        Frame testFrame=new Frame();
        Dice testDice1=new Dice(Color.RED,6);
        Dice testDice2=new Dice(Color.RED,3);
        Dice testDice3=new Dice(Color.BLUE ,1);
        Dice testDice4=new Dice(Color.YELLOW,3);
        testFrame.setPositionDice(testDice1,0,0);
        testFrame.setPositionDice(testDice2,0,1);
        testFrame.setPositionDice(testDice3,1,0);
        testFrame.setPositionDice(testDice4,1,1);
        WindowPattern testWindow=new WindowPattern(5,"prova");
        testWindow.setDicePosition(new Dice(Color.UNCOLORED,2),2,0);
        Player playerOne=new Player("gianfranco", Color.RED,testFrame,testWindow);
        gameTest.setAddPlayer(playerOne);
        GameConfigurator gameConfigurator=new GameConfigurator(gameTest);
        playerOne.setWindowPattern(testWindow);

        TcFluxRemover tcCard=new TcFluxRemover(1,"grozingPliers",Color.BLUE);
        System.out.println(playerOne.getWindowPattern().getDicePosition(new Coordinates(0,2)).getValue());

        gameTest.getCurrentPlayer();
        System.out.println(gameTest.getTurnOrder()[0].getName());
        assertTrue(gameTest.getTurnOrder().length==1);
        playerOne.setChosenNut(gameTest.getCurrentDice()[1]);
        System.out.println("dado scelto");
        System.out.println(playerOne.getChosenNut().getValue());

        tcCard.useTool(playerOne);
        System.out.println(playerOne.getChosenNut().getValue());
        System.out.println(gameTest.getRolledDice()[89].getValue());



    }
}
