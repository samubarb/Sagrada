package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameConfigurator;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.view.cards.VWindowPattern;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class AdapterPatternTest {
    @Test
    public void printPatternTest() {
        GameConfigurator gameConf = new GameConfigurator(new Game());
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


}
