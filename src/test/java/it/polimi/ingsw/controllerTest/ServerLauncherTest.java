package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.Server.ServerLauncher;
import it.polimi.ingsw.controller.client.rmiClient.RMIClientLauncher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerLauncherTest {
    @Test
    public void testGetNicknames() {
        ServerLauncher serverLauncher = new ServerLauncher();
        ArrayList nicknames = serverLauncher.getNicknames();
        assertEquals(nicknames, serverLauncher.getNicknames());
    }

    @Test
    public void testGetLoginMutex() {
        ServerLauncher serverLauncher = new ServerLauncher();
        Object loginMutex = serverLauncher.getLoginMutex();
        assertEquals(loginMutex, serverLauncher.getLoginMutex());
    }

    @Test
    public void testGetTurnLatch() {
        ServerLauncher serverLauncher = new ServerLauncher();
        CountDownLatch turnLatch = serverLauncher.getTurnLatch();
        assertEquals(turnLatch, serverLauncher.getTurnLatch());
    }

    @Test
    public void testGetCurrentPlayerInterface() {
        ServerLauncher serverLauncher = new ServerLauncher();
        PlayerInterface playerInterface = serverLauncher.getCurrentPlayerInterface();
        assertEquals(playerInterface, serverLauncher.getCurrentPlayerInterface());
    }

    @Test
    public void testSetCurrentPlayerInterface() {
        ServerLauncher serverLauncher = new ServerLauncher();
        PlayerInterface playerInterface = new RMIClientLauncher();
        serverLauncher.setCurrentPlayerInterface(playerInterface);
        assertEquals(playerInterface, serverLauncher.getCurrentPlayerInterface());
    }
}
