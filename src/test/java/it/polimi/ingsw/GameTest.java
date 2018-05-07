package it.polimi.ingsw;

import it.polimi.ingsw.model.Frame;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.WindowPattern;
import org.junit.jupiter.api.Test;

import java.awt.*;

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
        Frame testFrame=new Frame();
        WindowPattern testWindow=new WindowPattern(5,"prova");
        Player playerOne=new Player("gianfranco", Color.red,testFrame,testWindow);
        Player playerTwo=new Player("franco", Color.red,testFrame,testWindow);
        gameTest.setAddPlayer(playerOne);
        gameTest.setAddPlayer(playerTwo);
        gameTest.setNewRolledDice(round);
        for(int i=0;i<5;i++){
            assertTrue(gameTest.getCurrentDice()[i].getColor()!=Color.black);
        }
        assertTrue(gameTest.getCurrentDice()[5].getColor()==Color.black);

    }


}
