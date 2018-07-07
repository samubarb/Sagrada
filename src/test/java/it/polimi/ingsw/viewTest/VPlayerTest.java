package it.polimi.ingsw.viewTest;

import it.polimi.ingsw.controller.RMIApi.Adapter;
import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.game_elements.VGame;
import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.game_elements.VPlayer;
import it.polimi.ingsw.view.cards.VWindowPattern;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class VPlayerTest {

    @Test
    public void testprintPlayerTest() {
        println(new VPlayer("TestPlayer", VColor.PURPLE, new VWindowPattern()).toString());
    }

    @Test
    public void testprintPlayersTest() {
        Adapter ada = new AdapterCLI();
        Game game = new Game();

        Player p1 = new Player("TestPlayer1", Color.RED);
        Player p2 = new Player("TestPlayer2", Color.BLUE);
        Player p3 = new Player("TestPlayer3", Color.GREEN);
        Player p4 = new Player("TestPlayer4", Color.PURPLE);

        game.setAddPlayer(p1);
        game.setAddPlayer(p2);
        game.setAddPlayer(p3);
        game.setAddPlayer(p4);

        new GameConfigurator(game);

        VGame vgame = ada.gameToView(game);
        vgame.setClientPlayer(p1.getName());

        println(vgame);
    }
}
