package it.polimi.ingsw.controller.Server;

import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.client.rmiClient.RMIClientLauncher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testgetUsername() {
        User user = new User();
        String username = user.getUsername();
        assertEquals(username, "dacco");
    }

    @Test
    void testsetUsername() {
        User user = new User();
        user.setUsername("dac");
        assertEquals(user.getUsername(), "dac");
    }

    @Test
    void testgetPlayerInterface() {
        PlayerInterface playerInterface = new RMIClientLauncher();
        User user = new User("dacco", playerInterface);
        assertEquals(user.getPlayerInterface(), playerInterface);
    }

    @Test
    void testsetPlayerInterface() {
        User user = new User();
        PlayerInterface playerInterface = new RMIClientLauncher();
        user.setPlayerInterface(playerInterface);
        assertEquals(playerInterface, user.getPlayerInterface());
    }

    @Test
    void testisOnline() {
        User user = new User();
        assertEquals(user.isOnline, true);
    }

    @Test
    void testsetOnline() {
        User user = new User();
        user.setOnline(false);
        assertEquals(user.isOnline, false);
    }
}