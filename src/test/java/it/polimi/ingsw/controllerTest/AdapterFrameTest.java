package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Coordinates;
import it.polimi.ingsw.model.Dice;
import it.polimi.ingsw.model.Frame;
import it.polimi.ingsw.view.game_elements.VFrame;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class AdapterFrameTest {
    @Test
    public void testprintEmptyFrame() {
        Frame frame = new Frame();
        AdapterCLI adapter = new AdapterCLI();
        VFrame vFrame = adapter.frameToView(frame);
        println(vFrame.toString());
    }

    @Test
    public void testprintRandomFrame() {
        Frame frame = new Frame();
        AdapterCLI adapter = new AdapterCLI();

        frame.setPositionDice(new Dice(Color.GREEN, 4), new Coordinates(1 /*2*/, 2 /*3*/));

        VFrame vFrame = adapter.frameToView(frame);
        println(vFrame.toString());
    }
}
