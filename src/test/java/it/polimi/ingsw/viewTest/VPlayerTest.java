package it.polimi.ingsw.viewTest;

import it.polimi.ingsw.view.VColor;
import it.polimi.ingsw.view.VDice;
import it.polimi.ingsw.view.VPlayer;
import it.polimi.ingsw.view.VWindowPattern;
import it.polimi.ingsw.inputoutput.IOManager;
import org.junit.jupiter.api.Test;

public class VPlayerTest {

    @Test
    public void printPlayerTest() {
        IOManager.println(new VPlayer("TestPlayer", VColor.PURPLE, new VWindowPattern()).toString());
    }
}
