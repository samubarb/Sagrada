package it.polimi.ingsw;

import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Frame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class FrameTest {

    @Test
    public void setPositionDiceTest(){
        Frame testFrame=new Frame();
        Dice testDice1=new Dice(Color.red,6);
        Dice testDice2=new Dice(Color.red,3);
        Dice testDice3=new Dice(Color.blue ,1);
        Dice testDice4=new Dice(Color.yellow,3);
        testFrame.setPositionDice(testDice1,0,0);
        testFrame.setPositionDice(testDice2,0,1);
        testFrame.setPositionDice(testDice3,1,0);
        testFrame.setPositionDice(testDice4,1,1);
        Dice dice=new Dice(Color.red,0);
        Dice dice2=new Dice(Color.black,3);
        assertTrue(testFrame.getNumberOfDice(dice)==2);
        assertTrue(testFrame.getNumberOfDice(dice2)==2);


    }

    @Test
    public void getDiceTest(){
        Frame testFrame=new Frame();
        Dice testDice1=new Dice(Color.red,6);
        Dice testDice2=new Dice(Color.red,3);
        Dice testDice3=new Dice(Color.blue ,1);
        Dice testDice4=new Dice(Color.yellow,3);
        testFrame.setPositionDice(testDice1,0,0);
        testFrame.setPositionDice(testDice2,0,1);
        testFrame.setPositionDice(testDice3,1,0);
        testFrame.setPositionDice(testDice4,1,1);
        assertTrue(testFrame.getDice(1,1)==testDice4);


    }
}
