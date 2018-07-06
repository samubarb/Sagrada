package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PuODifferentShadesTest {

    @Test

    public void testPuODifferentShades(){
        Frame testFrame=new Frame();
        Dice testDice1=new Dice(Color.RED,1);
        Dice testDice2=new Dice(Color.GREEN,2);
        Dice testDice3=new Dice(Color.BLUE ,3);
        Dice testDice4=new Dice(Color.YELLOW,4);
        Dice testDice5=new Dice(Color.PURPLE,5);
        testFrame.setPositionDice(testDice1,0,0);
        testFrame.setPositionDice(testDice2,0,1);
        testFrame.setPositionDice(testDice3,0,2);
        testFrame.setPositionDice(testDice4,0,3);
        testFrame.setPositionDice(testDice5,0,4);
        testFrame.setPositionDice(testDice3,1,0);
        testFrame.setPositionDice(testDice4,2,0);
        testFrame.setPositionDice(testDice5,3,0);

        WindowPattern testWindow=new WindowPattern(5,"prova");
        Player playerOne=new Player("gianfranco", Color.RED,testFrame,testWindow);
        PuODifferentShades testLine=new PuODifferentShades("prova","line",5);
        PuODifferentShades testColumn=new PuODifferentShades("prova","column",4);

        assertTrue(testLine.calculateScore(playerOne)==5);
        assertTrue(testColumn.calculateScore(playerOne)==4);

    }
}
