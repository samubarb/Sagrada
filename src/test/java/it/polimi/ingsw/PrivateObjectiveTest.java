package it.polimi.ingsw;


import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Frame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class PrivateObjectiveTest {

    @Test
    public void testPrivateObjectiveConstructor(){
        PrivateObjective test =new PrivateObjective("prova", Color.red);
        assertTrue(test.getColor()==Color.red);
        assertTrue(test.getName().equals("prova"));
    }

    @Test
    public void testCalculateScore(){
        PrivateObjective testPrivate=new PrivateObjective("ciao",Color.red);
        Frame testFrame=new Frame();
        WindowPattern testWindow=new WindowPattern(5,"prova");
        Player test=new Player("gian",Color.red,testFrame,testWindow);
        Dice testDice1=new Dice(1);
        Dice testDice2=new Dice(2);
        Dice testDice3=new Dice(3);
        Dice testDice4=new Dice(4);
        Dice testDice5=new Dice(5);
        Dice testDice6=new Dice(6);
        Dice testDice7=new Dice(3);
        Dice defaultDice=new Dice(0);
        testDice1.setColor(Color.green);
        testDice2.setColor(Color.red);
        testDice3.setColor(Color.yellow);
        testDice4.setColor(Color.pink);
        testDice5.setColor(Color.blue);
        testDice6.setColor(Color.green);
        testDice7.setColor(Color.red);
        defaultDice.setColor(Color.black);
        //for(int i=0;i<4;i++)
           // for(int j=0;j<5;j++){
               // testFrame.setPositionDice(defaultDice,i,j);
               // System.out.println(testFrame.getDice(i,j).getValue());
               // }

        assertTrue(testPrivate.calculateScore(test)==0);
        testFrame.setPositionDice(testDice1,0,0);
        testFrame.setPositionDice(testDice2,0,1);
        testFrame.setPositionDice(testDice3,0,2);
        testFrame.setPositionDice(testDice4,0,3);
        testFrame.setPositionDice(testDice5,1,0);
        testFrame.setPositionDice(testDice6,1,1);
        testFrame.setPositionDice(testDice7,1,2);


        System.out.println(testPrivate.calculateScore(test));
        System.out.print(testPrivate.toString());
        assertTrue(testPrivate.calculateScore(test)==5);

    }


}
