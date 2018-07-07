package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PuOShadesTest {
    public static final String LIGHT_SHADES="lightShades";
    public static final String MEDIUM_SHADES="mediumShades";
    public static final String DARK_SHADES="darkShades";

    @Test

    public void testPuOShades(){

        Frame testFrame=new Frame();
        Dice testDice1=new Dice(Color.RED,1);
        Dice testDice2=new Dice(Color.GREEN,2);
        Dice testDice3=new Dice(Color.BLUE ,3);
        Dice testDice4=new Dice(Color.YELLOW,4);
        Dice testDice5=new Dice(Color.PURPLE,5);
        Dice testDice6=new Dice(Color.PURPLE,6);
        testFrame.setPositionDice(testDice1,0,0);
        testFrame.setPositionDice(testDice2,0,1);
        testFrame.setPositionDice(testDice3,0,2);
        testFrame.setPositionDice(testDice4,0,3);
        testFrame.setPositionDice(testDice5,0,4);
        testFrame.setPositionDice(testDice3,1,0);
        testFrame.setPositionDice(testDice4,2,0);
        testFrame.setPositionDice(testDice5,3,0);
        testFrame.setPositionDice(testDice6,3,1);
        testFrame.setPositionDice(testDice5,3,2);
        testFrame.setPositionDice(testDice4,3,3);



        WindowPattern testWindow=new WindowPattern(5,"prova");
        Player playerOne=new Player("gianfranco", Color.RED,testFrame,testWindow);
        PuOShades testone=new PuOShades("prova",2,LIGHT_SHADES);
        PuOShades testtwo=new PuOShades("prova",2,MEDIUM_SHADES);
        PuOShades testthree=new PuOShades("prova",2,DARK_SHADES);

        System.out.println(testone.calculateScore(playerOne));
        System.out.println(testtwo.calculateScore(playerOne));
        System.out.println(testthree.calculateScore(playerOne));

        assertTrue(testone.calculateScore(playerOne)==2);
        assertTrue(testtwo.calculateScore(playerOne)==4);
        assertTrue(testthree.calculateScore(playerOne)==2);
    }
}
