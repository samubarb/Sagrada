package it.polimi.ingsw.viewTest;

import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.inputoutput.IOManager;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.print;

public class VDiceTest {

    @Test
    public void testsetPositionDice(){
        for (VColor vc : VColor.values())
            for (int i = 1; i <= 6; i++)
                print(new VDice(i, vc).toString());
    }
}
