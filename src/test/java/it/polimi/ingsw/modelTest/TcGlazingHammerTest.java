package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.toolCards.TcGlazingHammer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TcGlazingHammerTest {

    @Test
    public void testGlazingHammer(){

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
        System.out.println(gameTest.getCurrentDice()[0].getValue());
        System.out.println(gameTest.getCurrentDice()[1].getValue());
        System.out.println(gameTest.getCurrentDice()[2].getValue());
        System.out.println(gameTest.getCurrentDice()[0].getColor());
        System.out.println(gameTest.getCurrentDice()[1].getColor());
        System.out.println(gameTest.getCurrentDice()[2].getColor());
        TcGlazingHammer tcGlazingHammer= new TcGlazingHammer(2,"prova",Color.RED);
        tcGlazingHammer.useTool(playerOne);
        System.out.println(gameTest.getCurrentDice()[0].getValue());
        System.out.println(gameTest.getCurrentDice()[1].getValue());
        System.out.println(gameTest.getCurrentDice()[2].getValue());
        System.out.println(gameTest.getCurrentDice()[0].getColor());
        System.out.println(gameTest.getCurrentDice()[1].getColor());
        System.out.println(gameTest.getCurrentDice()[2].getColor());


        //assertTrue(playerOne.getChosenNut().getValue()==1);

    }
}
