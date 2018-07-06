package it.polimi.ingsw.viewTest;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.getToolDescription;
import static it.polimi.ingsw.inputoutput.IOManager.println;

public class VToolTest {
    @Test
    public void testtoolDescriptionFromJsonTest() {
        for (int i = 1; i <= 12; i++)
            println(getToolDescription(i));
    }
}
