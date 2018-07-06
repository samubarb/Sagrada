package it.polimi.ingsw.viewTest;

import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import it.polimi.ingsw.view.other_elements.VMove;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class VMoveTest {
    @Test
    public void testVMovePrint() {
        VMove move = new VMove(new VDice(5, VColor.PURPLE), new VCoordinates(2,2));
        println(move.toString());
    }
}
