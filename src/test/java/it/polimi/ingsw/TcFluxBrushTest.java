package it.polimi.ingsw;


import it.polimi.ingsw.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TcFluxBrushTest {

    @Test
    public void testTcFluxBrush() {

        Game gameTest=new Game();
        Frame testFrame=new Frame();
        WindowPattern testWindow=new WindowPattern(5,"prova");
        Player playerOne=new Player("gianfranco", Color.RED,testFrame,testWindow);
        Player playerTwo=new Player("matteo", Color.GREEN,testFrame,testWindow);
        Player playerThree=new Player("paolo", Color.PURPLE,testFrame,testWindow);
        gameTest.setAddPlayer(playerOne);
        gameTest.setAddPlayer(playerTwo);
        gameTest.setAddPlayer(playerThree);
        gameTest.configureGame();
        gameTest.setTurnOrder();
        System.out.print(gameTest.getTurnOrder()[0].getName());
        System.out.print(gameTest.getTurnOrder()[1].getName());
        System.out.println(gameTest.getTurnOrder()[2].getName());
        assertTrue(gameTest.getTurnOrder().length==3);
    }
}
