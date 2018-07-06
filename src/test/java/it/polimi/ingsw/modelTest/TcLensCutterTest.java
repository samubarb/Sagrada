package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.toolCards.TcLathekin;
import it.polimi.ingsw.model.toolCards.TcLensCutter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TcLensCutterTest {


    @Test
    public void testLensCutter(){
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
        gameTest.setRoundTrack(testDice1,0);
        gameTest.setRoundTrack(testDice3,1);
        gameTest.setRoundTrack(testDice4,2);
        gameTest.setCurrentDice(testDice2,1);
        playerOne.setChosenNut(gameTest.getDiceFromCurrentDice(1));


        TcLensCutter tcLensCutter=new TcLensCutter(3,"prova",Color.RED);
        tcLensCutter.useTool(playerOne,new Coordinates(1,0));

        System.out.println(playerOne.getChosenNut().getValue());
        System.out.println(gameTest.getRoundTrack()[1].getValue());
        assertTrue(playerOne.getChosenNut().getValue()==1);


    }
}
