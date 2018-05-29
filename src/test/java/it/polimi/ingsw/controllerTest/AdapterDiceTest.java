package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.Frame;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.VColor;
import it.polimi.ingsw.view.VDice;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class AdapterDiceTest {
    public static final int DEFAUTL_VALUE = 0;

    private final Dice redDice = new Dice(Color.RED, DEFAUTL_VALUE);
    private final Dice greenDice = new Dice(Color.GREEN, DEFAUTL_VALUE);
    private final Dice purpleDice=new Dice(Color.PURPLE,DEFAUTL_VALUE);
    private final Dice yellowDice=new Dice(Color.YELLOW,DEFAUTL_VALUE);
    private final Dice blueDice=new Dice(Color.BLUE,DEFAUTL_VALUE);
    private final Dice dice1=new Dice(Color.UNCOLORED,1);
    private final Dice dice2=new Dice(Color.UNCOLORED,2);
    private final Dice dice3=new Dice(Color.UNCOLORED,3);
    private final Dice dice4=new Dice(Color.UNCOLORED,4);
    private final Dice dice5=new Dice(Color.UNCOLORED,5);
    private final Dice dice6=new Dice(Color.UNCOLORED,6);


    @Test
    public void testAdapterColor() {
        AdapterCLI adapterTest= new AdapterCLI();
        //VColor testColor;
        for(Color c : Color.values()) {
            VColor testColor = adapterTest.colorToView(c);
            println(testColor.toString() + "Ciao");

        }
    }

    @Test
    public void testAdapterDice(){
        AdapterCLI adapterTest= new AdapterCLI();
        Dice redDice = new Dice(Color.RED, DEFAUTL_VALUE);
        Dice greenDice = new Dice(Color.GREEN, 1);
        Dice purpleDice=new Dice(Color.PURPLE,2);
        Dice yellowDice=new Dice(Color.YELLOW,3);
        Dice blueDice=new Dice(Color.BLUE,4);
        Dice greenDice2 = new Dice(Color.GREEN, 5);
        Dice purpleDice2=new Dice(Color.PURPLE,6);
        VDice diceTest=adapterTest.diceToView(redDice);
        println(diceTest.toString());
        diceTest=adapterTest.diceToView(greenDice);
        diceTest=adapterTest.diceToView(purpleDice);
        println(diceTest.toString());
        diceTest=adapterTest.diceToView(yellowDice);
        println(diceTest.toString());
        diceTest=adapterTest.diceToView(blueDice);
        println(diceTest.toString());
        diceTest=adapterTest.diceToView(greenDice2);
        println(diceTest.toString());
        diceTest=adapterTest.diceToView(purpleDice2);
        println(diceTest.toString());


    }

}
