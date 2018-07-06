package it.polimi.ingsw.viewTest;

import it.polimi.ingsw.view.CLI;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.other_elements.VError;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class VErrorTest {
    @Test
    public void testVErrorPrintTest() {
        for (VError e : VError.values())
            println(e.toString());
    }

    @Test
    public void testVErrorNotify() {
        View view = new CLI("TestPlayer");
        for (VError e : VError.values())
            view.notifyError(e);
    }
}
