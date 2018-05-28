package it.polimi.ingsw.viewTest;

import it.polimi.ingsw.view.VColor;
import it.polimi.ingsw.view.VCoordinates;
import it.polimi.ingsw.view.VDice;
import it.polimi.ingsw.view.VFrame;
import it.polimi.ingsw.view.exceptions.IllegalCoordinatesException;
import it.polimi.ingsw.inputoutput.IOManager;
import org.junit.jupiter.api.Test;


public class VFrameTest {
    @Test
    public void printEmptyFrame() {
        VFrame frame = new VFrame();
        IOManager.println(frame.toString());
    }

    @Test
    public void printExampleFrame() {
        VFrame frame = new VFrame();

        frame.setDice(new VDice(6, VColor.RED), new VCoordinates(4,4));
        IOManager.println(frame.toString());
    }

    @Test
    public void printFullFrame() {
        VFrame frame = new VFrame();
        for(int i = 1; i <= 5; i++)
            for(int j = 1; j <= 4; j++)
                frame.setDice(new VDice(2, VColor.BLUE), new VCoordinates(i,j));

        IOManager.println(frame.toString());
    }
}
