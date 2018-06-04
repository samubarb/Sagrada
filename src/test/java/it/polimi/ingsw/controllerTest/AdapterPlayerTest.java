package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.game_elements.VPlayer;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class AdapterPlayerTest {
    @Test
    public void printPlayerTest() {
        Player player = new Player("Test Player");
        player.setColor(Color.GREEN);
        player.setWindowPattern(new GameConfigurator(new Game()).createWPCbatllo());

        VPlayer vPlayer = new AdapterCLI().playerToView(player);

        println(vPlayer.toString());
    }
}

