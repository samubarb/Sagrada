package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.toolCards.TcFluxRemover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PuOColoredDiagonalsTest {

    @Test
    public void testColoredDiagonals(){
        Game gameTest=new Game();
        Frame testFrame=new Frame();
        Dice testDice1=new Dice(Color.RED,6);
        Dice testDice2=new Dice(Color.RED,3);
        Dice testDice3=new Dice(Color.BLUE ,1);
        Dice testDice4=new Dice(Color.YELLOW,3);
        testFrame.setPositionDice(testDice3,2,0);
        testFrame.setPositionDice(testDice3,1,1);
        testFrame.setPositionDice(testDice3,2,2);
        testFrame.setPositionDice(testDice3,3,3);
        testFrame.setPositionDice(testDice4,3,2);
        testFrame.setPositionDice(testDice4,2,3);
        testFrame.setPositionDice(testDice4,1,4);
        WindowPattern testWindow=new WindowPattern(5,"prova");
        //testWindow.setDicePosition(new Dice(Color.UNCOLORED,2),2,0);
        Player playerOne=new Player("gianfranco", Color.RED,testFrame,testWindow);
        gameTest.setAddPlayer(playerOne);
        GameConfigurator gameConfigurator=new GameConfigurator(gameTest);
        playerOne.setWindowPattern(testWindow);

        //TcFluxRemover tcCard=new TcFluxRemover(1,"grozingPliers",Color.BLUE);
        //System.out.println(playerOne.getWindowPattern().getDicePosition(new Coordinates(0,2)).getValue());

        gameTest.getCurrentPlayer();
        System.out.println(gameTest.getTurnOrder()[0].getName());
        assertTrue(gameTest.getTurnOrder().length==1);
        PuOColoredDiagonals testPuO=new PuOColoredDiagonals("prova",1);
        System.out.println(testPuO.calculateScore(playerOne));

    }
}
