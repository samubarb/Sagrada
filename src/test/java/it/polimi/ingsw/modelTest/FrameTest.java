package it.polimi.ingsw.modelTest;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.toolCards.TcFluxBrush;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FrameTest {

    @Test
    public void setPositionDiceTest(){
        Frame testFrame=new Frame();
        Dice testDice1=new Dice(Color.RED,6);
        Dice testDice2=new Dice(Color.RED,3);
        Dice testDice3=new Dice(Color.BLUE ,1);
        Dice testDice4=new Dice(Color.YELLOW,3);
        testFrame.setPositionDice(testDice1,0,0);
        testFrame.setPositionDice(testDice2,0,1);
        testFrame.setPositionDice(testDice3,1,0);
        testFrame.setPositionDice(testDice4,1,1);
        Dice dice=new Dice(Color.RED,0);
        Dice dice2=new Dice(Color.UNCOLORED,3);
        assertTrue(testFrame.getNumberOfDice(dice)==2);
        assertTrue(testFrame.getNumberOfDice(dice2)==2);


    }

    @Test
    public void getDiceTest(){
        Frame testFrame=new Frame();
        Dice testDice1=new Dice(Color.RED,6);
        Dice testDice2=new Dice(Color.RED,3);
        Dice testDice3=new Dice(Color.BLUE ,1);
        Dice testDice4=new Dice(Color.YELLOW,3);
        testFrame.setPositionDice(testDice1,0,0);
        testFrame.setPositionDice(testDice2,0,1);
        testFrame.setPositionDice(testDice3,1,0);
        testFrame.setPositionDice(testDice4,1,1);
        assertTrue(testFrame.getDice(1,1)==testDice4);


    }
    @Test
    public void testCheckControlAdjacentDice(){
        Frame testFrame=new Frame();
        Dice testDice1=new Dice(Color.RED,6);
        Dice testDice2=new Dice(Color.RED,3);
        Dice testDice3=new Dice(Color.BLUE ,1);
        Dice testDice4=new Dice(Color.YELLOW,3);
        Dice testDice5=new   Dice(Color.PURPLE,5);
        testFrame.setPositionDice(testDice1,0,0);
        testFrame.setPositionDice(testDice2,0,1);
        testFrame.setPositionDice(testDice3,1,0);
        testFrame.setPositionDice(testDice4,1,1);
        testFrame.setPositionDice(testDice5,new Coordinates(4,1));
        assertTrue(testFrame.checkControlAdjacentDice(testDice1,new Coordinates(2,2)));
        assertFalse(testFrame.checkControlAdjacentDice(testDice1,new Coordinates(0,3)));
        assertTrue(testFrame.checkControlAdjacentDice(testDice1,new Coordinates(2,0)));
        assertTrue(testFrame.checkControlAdjacentDice(testDice1,new Coordinates(4,0)));
        assertFalse(testFrame.checkControlAdjacentDice(testDice1,new Coordinates(4,3)));
    }

    @Test
    public void testCheckPositionDice(){
        Frame testFrame=new Frame();
        Dice testDice1=new Dice(Color.RED,6);
        Dice testDice2=new Dice(Color.RED,3);
        Dice testDice3=new Dice(Color.BLUE ,1);
        Dice testDice4=new Dice(Color.YELLOW,3);
        Dice testDice5=new   Dice(Color.PURPLE,5);
        Dice testDice6=new Dice(Color.GREEN,2);
        testFrame.setPositionDice(testDice1,0,0);
        assertTrue(testFrame.checkPositionDice(testDice2,new Coordinates(1,1)));
        assertFalse(testFrame.checkPositionDice(testDice2,new Coordinates(1,0)));
        testFrame.setPositionDice(testDice4,1,0);
        testFrame.setPositionDice(testDice4,1,0);
        assertFalse(testFrame.checkPositionDice(testDice2,new Coordinates(0,2)));
        assertTrue(testFrame.checkPositionDice(testDice2,new Coordinates(1,2)));
    }
}
