package it.polimi.ingsw.viewTest;

import it.polimi.ingsw.view.other_elements.VColor;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VGameTest {
    @Test
    public void printShadowTest() {
        for(VColor vc : VColor.values())
            print(vc.toString() + "\u2593");
        print(VColor.RESET.toString());
    }
}
