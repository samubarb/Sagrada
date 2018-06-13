package it.polimi.ingsw.viewTest;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.getToolDescription;
import static it.polimi.ingsw.inputoutput.IOManager.println;

public class VToolTest {
    @Test
    public void toolDescriptionFromJsonTest() {
        for (int i = 0; i < 12; i++)
            println(getToolDescription(i));
    }

    @Test
    public void toolPrintTest(){

    }
}
