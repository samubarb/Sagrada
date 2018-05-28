package it.polimi.ingsw.viewTest;

import it.polimi.ingsw.view.*;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class VMoveTest {

    @Test
    public void VMovePrint() {
        VMove move = new VMove(new VDice(5, VColor.PURPLE), new VCoordinates(2,2));
        println(move.toString());
    }

    @Test
    public void askMovePrint() {
    }
}
