package it.polimi.ingsw.viewTest;

import it.polimi.ingsw.view.VColor;
import it.polimi.ingsw.view.VDice;
import it.polimi.ingsw.inputoutput.IOManager;
import org.junit.jupiter.api.Test;

public class VDiceTest {

    @Test
    public void setPositionDiceTest(){

        for (VColor vc : VColor.values()) {
            for (int i = 1; i <= 6; i++)
                IOManager.print(new VDice(i, vc).toString());
            //IOManager.println("\n");
        }
    }
}
