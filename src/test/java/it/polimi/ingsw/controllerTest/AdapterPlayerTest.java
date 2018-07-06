package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.game_elements.VPlayer;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class AdapterPlayerTest {
    @Test
    public void testprintPlayer() {
        Player player = new Player("Test Player");
        player.setColor(Color.GREEN);
        Game game = new Game();
        game.setAddPlayer(player);
        player.setWindowPattern(new GameConfigurator(game).createWPCbatllo());
        player.setPrivateObjective(new PrivateObjective("prova", Color.RED));

        VPlayer vPlayer = new AdapterCLI().playerToView(player);

        println(vPlayer.toString());
    }
}

