package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.RMIApi.ServerInterface;
import it.polimi.ingsw.controller.client.ClientLauncher;
import it.polimi.ingsw.model.Player;
import org.junit.jupiter.api.Test;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientLauncherTest {

    /*
    This test checks single and multi login, in particular checks the case in which there are two players logging with the same name
     */
    @Test
    public void testRegisterSameName() {
        System.out.println("testing");
        String name = "ServerInterface";
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            ServerInterface server = (ServerInterface) registry.lookup(name);

            assertEquals(true, server.register(new ClientLauncher(), "bob"));
            assertEquals(false, server.register(new ClientLauncher(), "bob"));
        }
        catch (Exception e){}
    }
    /*
    This test tests the case in whitch more than 4 player try to join the room
     */
    @Test
    public void testRegister5DIfferentPlayer(){
        System.out.println("testing");
        String name = "ServerInterface";
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            ServerInterface server = (ServerInterface) registry.lookup(name);

            assertEquals(true, server.register(new ClientLauncher(), "bob0"));
            assertEquals(true, server.register(new ClientLauncher(), "bob1"));
            assertEquals(true, server.register(new ClientLauncher(), "bob2"));
            assertEquals(true, server.register(new ClientLauncher(), "bob3"));
            assertEquals(false, server.register(new ClientLauncher(), "bob4"));
        }
        catch (Exception e){}
    }

}
