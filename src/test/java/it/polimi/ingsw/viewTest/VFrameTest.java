package it.polimi.ingsw.viewTest;

import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.game_elements.VFrame;
import it.polimi.ingsw.inputoutput.IOManager;
import org.junit.jupiter.api.Test;


public class VFrameTest {
    @Test
    public void testprintEmptyFrame() {
        VFrame frame = new VFrame();
        IOManager.println(frame.toString());
    }

    @Test
    public void testprintExampleFrame() {
        VFrame frame = new VFrame();

        frame.setDice(new VDice(6, VColor.RED), new VCoordinates(4,4));
        IOManager.println(frame.toString());
    }

    @Test
    public void testprintFullFrame() {
        VFrame frame = new VFrame();
        for(int i = 1; i <= 5; i++)
            for(int j = 1; j <= 4; j++)
                frame.setDice(new VDice(2, VColor.BLUE), new VCoordinates(i,j));

        IOManager.println(frame.toString());
    }
}
