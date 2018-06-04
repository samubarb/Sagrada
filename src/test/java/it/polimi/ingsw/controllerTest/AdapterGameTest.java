package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.game_elements.VGame;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class AdapterGameTest {
    @Test
    public void AdapterGamePrintTest() {
        Game game = new Game();
        Player tp1 = new Player("TestPlayer1", Color.GREEN);
        Player tp2 = new Player("TestPlayer2", Color.RED);

        game.setAddPlayer(tp1);
        game.setAddPlayer(tp2);
        game.setCurrentDice(new Dice[6]);
        game.addWindowPatternCard(new GameConfigurator(game).createWPCViaLux(), 0);
        game.setCurrentPlayerIndex(0);
        println(game.getCurrentPlayer().getName());

        VGame vGame = new AdapterCLI().gameToView(game);
        vGame.setClientPlayer(tp1.getName());
        println(vGame.toString());
    }
}
