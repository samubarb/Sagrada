package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameConfigurator;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.view.VWindowPattern;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class AdapterPatternTest {
    @Test
    public void printPatternTest() {
        GameConfigurator gameConf = new GameConfigurator(new Game());
        AdapterCLI adapter = new AdapterCLI();
        WindowPattern virtus = gameConf.createWPCVirtus();

        VWindowPattern vPattern = adapter.patternToView(virtus);
        println(vPattern.toString());
    }
}
