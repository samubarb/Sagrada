package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.Server.ServerSettings;
import it.polimi.ingsw.controller.client.ClientSettings;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class NetworkSettingsTest {
    @Test
    public void testServerfromJSON() {
        println(new ServerSettings().setFromJSON().toString());
    }

    @Test
    public void testClientfromJSONTest() {
        println(new ClientSettings().setFromJSON().toString());
    }
}
