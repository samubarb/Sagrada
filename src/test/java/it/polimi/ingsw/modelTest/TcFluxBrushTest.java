package it.polimi.ingsw.modelTest;


import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.toolCards.TcFluxBrush;
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
        TcFluxBrush tcCard=new TcFluxBrush(1,"fluxBrush",Color.BLUE);
        gameTest.setAddPlayer(playerOne);
        gameTest.setAddPlayer(playerTwo);
        gameTest.setAddPlayer(playerThree);
        GameConfigurator gameConfigurator=new GameConfigurator(gameTest);
        //gameTest.configureGame();
        //gameTest.setTurnOrder();
        //gameTest.setNewRolledDice(gameTest.getRound());
        playerOne.setCurrentGame(gameTest);
        gameTest.getCurrentPlayer();
        System.out.print(gameTest.getTurnOrder()[0].getName());
        System.out.print(gameTest.getTurnOrder()[1].getName());
        System.out.println(gameTest.getTurnOrder()[2].getName());
        assertTrue(gameTest.getTurnOrder().length==3);
        playerOne.setChosenNut(playerOne.getCurrentGame().getCurrentDice()[2]);
        System.out.println(playerOne.getChosenNut().getValue());
        tcCard.useTool(playerOne);
        System.out.println(playerOne.getChosenNut().getValue());
        //tcCard.useTool(playerOne,Action.INCREASE);

    }
}
