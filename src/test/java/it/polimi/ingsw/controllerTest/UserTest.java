package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.Server.User;
import it.polimi.ingsw.controller.client.rmiClient.RMIClientLauncher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    public void testGetUsername() {
        User user = new User();
        String username = user.getUsername();
        assertEquals(username, "dacco");
    }

    @Test
    public void testSetUsername() {
        User user = new User();
        user.setUsername("dac");
        assertEquals(user.getUsername(), "dac");
    }

    @Test
    public void testGetPlayerInterface() {
        PlayerInterface playerInterface = new RMIClientLauncher();
        User user = new User("dacco", playerInterface);
        assertEquals(user.getPlayerInterface(), playerInterface);
    }

    @Test
    public void testSetPlayerInterface() {
        User user = new User();
        PlayerInterface playerInterface = new RMIClientLauncher();
        user.setPlayerInterface(playerInterface);
        assertEquals(playerInterface, user.getPlayerInterface());
    }

    @Test
    public void testIsOnline() {
        User user = new User();
        assertEquals(user.isOnline(), true);
    }

    @Test
    public void testSetOnline() {
        User user = new User();
        user.setOnline(false);
        assertEquals(user.isOnline(), false);
    }
}
