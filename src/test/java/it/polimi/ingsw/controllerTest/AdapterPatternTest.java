package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameConfigurator;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.view.cards.VWindowPattern;
import it.polimi.ingsw.view.game_elements.VWindowPatterns;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.inputoutput.IOManager.gridSpace;
import static it.polimi.ingsw.inputoutput.IOManager.makeHorizontal;
import static it.polimi.ingsw.inputoutput.IOManager.println;

public class AdapterPatternTest {
    @Test
    public void testprintPattern() {
        Game game = new Game();
        game.setAddPlayer(new Player("TestPlayer"));
        GameConfigurator gameConf = new GameConfigurator(game);
        AdapterCLI adapter = new AdapterCLI();
        ArrayList<WindowPattern> patterns = new ArrayList<WindowPattern>();
        patterns.add(gameConf.createWPCVirtus());
        patterns.add(gameConf.createWPCauroraeMgnificus());
        patterns.add(gameConf.createWPCauroraSagradis());
        patterns.add(gameConf.createWPCbatllo());
        patterns.add(gameConf.createWPCBellesguard());
        patterns.add(gameConf.createWPCfirmitas());
        patterns.add(gameConf.createWPCindustria());
        patterns.add(gameConf.createWPCKaleidoscopicDream());
        patterns.add(gameConf.createWPCshadowThief());
        patterns.add(gameConf.createWPCsunCatcher());
        patterns.add(gameConf.createWPCsymphonyOfLight());
        patterns.add(gameConf.createWPCViaLux());

        for (WindowPattern wp : patterns) {
            VWindowPattern vPattern = adapter.patternToView(wp);
            println(vPattern.toString());
        }
    }

    @Test
    public void testprintPatterns() {
        Game game = new Game();
        game.setAddPlayer(new Player("TestPlayer"));
        GameConfigurator gameConf = new GameConfigurator(game);

        WindowPattern[] patterns = new WindowPattern[4];
        patterns[0] = gameConf.createWPCVirtus();
        patterns[1] = gameConf.createWPCauroraeMgnificus();
        patterns[2] = gameConf.createWPCauroraSagradis();
        patterns[3] = gameConf.createWPCbatllo();

        VWindowPatterns vPatterns = new AdapterCLI().patternToView(patterns);

        println(vPatterns);
    }
}
