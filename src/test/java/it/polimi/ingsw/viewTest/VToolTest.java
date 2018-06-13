package it.polimi.ingsw.viewTest;

import it.polimi.ingsw.inputoutput.IOManager;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.getToolDescriptions;
import static it.polimi.ingsw.inputoutput.IOManager.println;

public class VToolTest {
    @Test
    public void toolDescriptionFromJson() {
        String[] descriptions = getToolDescriptions();
        for (String d : descriptions)
            println(d);
    }
}
