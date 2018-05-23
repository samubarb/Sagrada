package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Frame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class PuODifferentColourTest {

    @Test
    public void calculateScoreTest(){
        Frame testFrame=new Frame();
        Dice testDice1=new Dice(Color.RED,6);
        Dice testDice2=new Dice(Color.GREEN,3);
        Dice testDice3=new Dice(Color.BLUE ,1);
        Dice testDice4=new Dice(Color.YELLOW,3);
        Dice testDice5=new Dice(Color.PURPLE,3);
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
        PuODifferentColor testLine=new PuODifferentColor("prova",Color.GREEN,"line",6);
        PuODifferentColor testLine2=new PuODifferentColor("prova",Color.GREEN,"column",5);
        assertTrue(testLine.calculateScore(playerOne)==6);
        assertTrue(testLine2.calculateScore(playerOne)==5);


    }
}
