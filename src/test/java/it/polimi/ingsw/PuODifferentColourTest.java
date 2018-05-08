package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Frame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class PuODifferentColourTest {

    @Test
    public void calculateScoreTest(){
        Frame testFrame=new Frame();
        Dice testDice1=new Dice(Color.red,6);
        Dice testDice2=new Dice(Color.green,3);
        Dice testDice3=new Dice(Color.blue ,1);
        Dice testDice4=new Dice(Color.yellow,3);
        Dice testDice5=new Dice(Color.pink,3);
        testFrame.setPositionDice(testDice1,0,0);
        testFrame.setPositionDice(testDice2,0,1);
        testFrame.setPositionDice(testDice3,0,2);
        testFrame.setPositionDice(testDice4,0,3);
        testFrame.setPositionDice(testDice5,0,4);
        testFrame.setPositionDice(testDice3,1,0);
        testFrame.setPositionDice(testDice4,2,0);
        testFrame.setPositionDice(testDice5,3,0);

        WindowPattern testWindow=new WindowPattern(5,"prova");
        Player playerOne=new Player("gianfranco", Color.red,testFrame,testWindow);
        PuODifferentColor testLine=new PuODifferentColor("prova",Color.green,0,6);
        PuODifferentColor testLine2=new PuODifferentColor("prova",Color.green,1,5);
        assertTrue(testLine.calculateScore(playerOne)==6);
        assertTrue(testLine2.calculateScore(playerOne)==5);


    }
}
