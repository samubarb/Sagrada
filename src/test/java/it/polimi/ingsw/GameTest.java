package it.polimi.ingsw;

import it.polimi.ingsw.model.Frame;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.WindowPattern;
import org.junit.jupiter.api.Test;
import it.polimi.ingsw.model.Color;


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


}
