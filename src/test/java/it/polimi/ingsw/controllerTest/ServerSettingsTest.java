package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.Server.ServerSettings;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class ServerSettingsTest {
    @Test
    public final void fromJSONTest() {
        println(new ServerSettings().setFromJSON().toString());
    }
}
