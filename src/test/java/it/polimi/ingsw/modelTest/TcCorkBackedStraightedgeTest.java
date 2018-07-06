package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.toolCards.TcCorkBackedStraightedge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TcCorkBackedStraightedgeTest {

    @Test

    public void testTcCorkBackedStraightedge(){

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
        Player playerTwo=new Player("dacco", Color.YELLOW,testFrame,testWindow);
        gameTest.setAddPlayer(playerOne);
        gameTest.setAddPlayer(playerTwo);
        GameConfigurator gameConfigurator=new GameConfigurator(gameTest);
        gameTest.getCurrentPlayer();
        playerOne.setWindowPattern(testWindow);
        playerOne.setFrame(testFrame);
        playerOne.setChosenNut(testDice3);


        TcCorkBackedStraightedge tcCorkBackedStraightedge=new TcCorkBackedStraightedge(2,"prova",Color.YELLOW);
        tcCorkBackedStraightedge.useTool(playerOne,new Coordinates(3,3));
        assertTrue(playerOne.getFrame().getDice(3,3).getValue()==1);
    }
}
