package it.polimi.ingsw.viewTest;

import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.game_elements.VPlayer;
import it.polimi.ingsw.view.cards.VWindowPattern;
import it.polimi.ingsw.inputoutput.IOManager;
import org.junit.jupiter.api.Test;

public class VPlayerTest {

    @Test
    public void printPlayerTest() {
        IOManager.println(new VPlayer("TestPlayer", VColor.PURPLE, new VWindowPattern()).toString());
    }
}
