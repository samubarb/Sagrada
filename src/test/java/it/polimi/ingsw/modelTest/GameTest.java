package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testRolledDiceGame(){

        Game gameTest=new Game();
        gameTest.shuffleRolledDice();
        System.out.println(gameTest.getRolledDice()[0].getValue());
        System.out.println(gameTest.getRolledDice()[1].getValue());
        System.out.println(gameTest.getRolledDice()[56].getValue());
        System.out.println(gameTest.getRolledDice()[0].getColor());
        System.out.println(gameTest.getRolledDice()[1].getColor());
        System.out.println(gameTest.getRolledDice()[2].getColor());
        System.out.println(gameTest.getRolledDice()[5].getColor());
        assertTrue(gameTest.getRolledDice().length==90);

    }
    @Test
    public void testSetNewRolledDice(){
        int round=2;
        Game gameTest=new Game();
        gameTest.configureGame();
        Frame testFrame=new Frame();
        WindowPattern testWindow=new WindowPattern(5,"prova");
        Player playerOne=new Player("gianfranco", Color.RED,testFrame,testWindow);
        Player playerTwo=new Player("franco", Color.RED,testFrame,testWindow);
        gameTest.setAddPlayer(playerOne);
        gameTest.setAddPlayer(playerTwo);
        gameTest.configureGame();
        gameTest.setNewRolledDice(round);
        for(int i=0;i<5;i++){
            assertTrue(gameTest.getCurrentDice()[i].getColor()!=Color.UNCOLORED);
        }
    }

    @Test
    public void testSetNewOrder(){
        Game gameTest=new Game();
        Frame testFrame=new Frame();
        WindowPattern testWindow=new WindowPattern(5,"prova");
        Player playerOne=new Player("gianfranco", Color.RED,testFrame,testWindow);
        Player playerTwo=new Player("matteo", Color.GREEN,testFrame,testWindow);
        Player playerThree=new Player("paolo", Color.PURPLE,testFrame,testWindow);
        gameTest.setAddPlayer(playerOne);
        gameTest.setAddPlayer(playerTwo);
        gameTest.setAddPlayer(playerThree);
        GameConfigurator gameConfigurator=new GameConfigurator(gameTest);
        //gameTest.configureGame();
        //gameTest.setTurnOrder();
        System.out.print(gameTest.getTurnOrder()[0].getName());
        System.out.print(gameTest.getTurnOrder()[1].getName());
        System.out.println(gameTest.getTurnOrder()[2].getName());
        assertTrue(gameTest.getTurnOrder().length==3);
        Player testPlayer=gameTest.getCurrentPlayer();
        System.out.println("round "+ gameTest.getRound());
        System.out.println(gameTest.getCurrentPlayerIndex());
        System.out.println(testPlayer.getName());
        testPlayer=gameTest.getCurrentPlayer();
        System.out.println("index"+gameTest.getCurrentPlayerIndex());
        System.out.println("round "+ gameTest.getRound());
        System.out.println(testPlayer.getName());
        testPlayer=gameTest.getCurrentPlayer();
        System.out.println(testPlayer.getName());
        System.out.println("index"+gameTest.getCurrentPlayerIndex());
        testPlayer=gameTest.getCurrentPlayer();
        System.out.println(testPlayer.getName());
        System.out.println("index"+gameTest.getCurrentPlayerIndex());
        System.out.println("round "+ gameTest.getRound());
        testPlayer=gameTest.getCurrentPlayer();
        System.out.println(testPlayer.getName());
        System.out.println("index"+gameTest.getCurrentPlayerIndex());
        System.out.println("round "+ gameTest.getRound());
        testPlayer=gameTest.getCurrentPlayer();
        System.out.println(testPlayer.getName());
        System.out.println("index"+gameTest.getCurrentPlayerIndex());
        System.out.println("round "+ gameTest.getRound());
        System.out.print(gameTest.getTurnOrder()[0].getName());
        System.out.print(gameTest.getTurnOrder()[1].getName());
        System.out.println(gameTest.getTurnOrder()[2].getName());
        testPlayer=gameTest.getCurrentPlayer();
        System.out.println(testPlayer.getName());
        System.out.print(gameTest.getTurnOrder()[0].getName());
        System.out.print(gameTest.getTurnOrder()[1].getName());
        System.out.println(gameTest.getTurnOrder()[2].getName());
        testPlayer=gameTest.getCurrentPlayer();
        testPlayer=gameTest.getCurrentPlayer();
        testPlayer=gameTest.getCurrentPlayer();
        testPlayer=gameTest.getCurrentPlayer();
        testPlayer=gameTest.getCurrentPlayer();
        testPlayer=gameTest.getCurrentPlayer();
        System.out.print(gameTest.getTurnOrder()[0].getName());
        System.out.print(gameTest.getTurnOrder()[1].getName());
        System.out.println(gameTest.getTurnOrder()[2].getName());
        testPlayer=gameTest.getCurrentPlayer();
        testPlayer=gameTest.getCurrentPlayer();
        testPlayer=gameTest.getCurrentPlayer();
        testPlayer=gameTest.getCurrentPlayer();
        testPlayer=gameTest.getCurrentPlayer();
        testPlayer=gameTest.getCurrentPlayer();
        System.out.print(gameTest.getTurnOrder()[0].getName());
        System.out.print(gameTest.getTurnOrder()[1].getName());
        System.out.println(gameTest.getTurnOrder()[2].getName());


    }


}
