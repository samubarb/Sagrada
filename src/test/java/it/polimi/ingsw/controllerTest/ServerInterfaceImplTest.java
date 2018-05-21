package it.polimi.ingsw.controllerTest;


import it.polimi.ingsw.controller.Server.Rmi.ServerInterfaceImpl;
import it.polimi.ingsw.controller.Server.ServerLauncher;
import it.polimi.ingsw.controller.Server.User;
import it.polimi.ingsw.controller.client.ClientLauncher;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerInterfaceImplTest {
    /*
    This test checks getNumberOfPlayer
     */
    @Test
    public void testGetNicknames(){
        ServerLauncher serverLauncher = new ServerLauncher();
        ServerInterfaceImpl server = new ServerInterfaceImpl();
        server.setServerLauncher(serverLauncher);
        ClientLauncher clientLauncher = new ClientLauncher();
        try {
            server.register(clientLauncher, "dacco");
            System.out.println(serverLauncher.getOfflineNicknames().size());
            System.out.println(serverLauncher.getNicknames().size());
            System.out.println(serverLauncher.getNicknames().size()+serverLauncher.getOfflineNicknames().size());
            assertEquals(true, serverLauncher.getNicknames().size() == 1);
            assertEquals(true, serverLauncher.getNicknames().contains("dacco"));
            assertEquals(false, serverLauncher.getNicknames().size() == 2);
            assertEquals(false, serverLauncher.getNicknames().contains("teo"));
        }
        catch(RemoteException e){
            System.out.println("dioooo");
        }
    }
    /*
This test checks getOfflineNumberOfPlayer
 */
    @Test
    public void testGetOfflineNicknames(){
        ServerLauncher serverLauncher = new ServerLauncher();
        ServerInterfaceImpl server = new ServerInterfaceImpl();
        server.setServerLauncher(serverLauncher);
        try {
            server.register(new ClientLauncher(), "dacco1");
            server.getServerLauncher().getOfflineNicknames().add(new User("dacco", new ClientLauncher()));
            assertEquals(true, server.getServerLauncher().getOfflineNicknames().size() == 1);
            assertEquals(true, server.getServerLauncher().getOfflineNicknames().contains("dacco"));
            assertEquals(false, server.getServerLauncher().getOfflineNicknames().size() == 2);
            assertEquals(false, server.getServerLauncher().getOfflineNicknames().contains("teo"));
        }
        catch(Exception e){}
    }
    /**
     * This test checks single and multi login, in particular checks the case in which there are two players logging with the same name
     **/
    @Test
    public void testRegisterSameName() {
        System.out.println("testing");
        ServerLauncher serverLauncher = new ServerLauncher();
        ServerInterfaceImpl server = new ServerInterfaceImpl();
        server.setServerLauncher(serverLauncher);
        try {
            assertEquals(true, server.register(new ClientLauncher(), "bob"));
            assertEquals(false, server.register(new ClientLauncher(), "bob"));
        } catch (RemoteException e) {
        }
    }

    /**
     * This test tests the case in whitch more than 4 player try to join the room
     **/
    @Test
    public void testRegister5DIfferentPlayer() {
        System.out.println("testing");
        ServerLauncher serverLauncher = new ServerLauncher();
        ServerInterfaceImpl server = new ServerInterfaceImpl();
        server.setServerLauncher(serverLauncher);
        try {
            System.out.println("ciao");
            assertEquals(false, server.register(new ClientLauncher(), "bob0"));
            System.out.println("ciao");
            assertEquals(true, server.register(new ClientLauncher(), "bob1"));
            assertEquals(true, server.register(new ClientLauncher(), "bob2"));
            System.out.println("ciao");
            assertEquals(true, server.register(new ClientLauncher(), "bob3"));
            assertEquals(false, server.register(new ClientLauncher(), "bob4"));
        } catch (Exception e) {
        }
    }

    /**
     * This test checks getNumberOfPlayer implementation
     */
    @Test
    public void testGetNumberOfPlayer() {
        System.out.println("testing");
        ServerLauncher serverLauncher = new ServerLauncher();
        ServerInterfaceImpl server = new ServerInterfaceImpl();
        server.setServerLauncher(serverLauncher);
        try {
            assertEquals(0, server.getNumberOfPlayer());
            server.register(new ClientLauncher(), "bob0");
            assertEquals(1, server.getNumberOfPlayer());
            server.register(new ClientLauncher(), "bob1");
            assertEquals(2, server.getNumberOfPlayer());
            server.register(new ClientLauncher(), "bob2");
            assertEquals(3, server.getNumberOfPlayer());
            server.register(new ClientLauncher(), "bob3");
            assertEquals(4, server.getNumberOfPlayer());
            assertEquals(false, server.getNumberOfPlayer() == 5);
        } catch (Exception e) {
        }
    }


    @Test
    public void testReconnectingInactivePlayer() {
        ServerLauncher serverLauncher = new ServerLauncher();
        ServerInterfaceImpl server = new ServerInterfaceImpl();
        server.setServerLauncher(serverLauncher);
        try {
            server.register(new ClientLauncher(),"Dacco");
            server.getServerLauncher().getOfflineNicknames().add(new User("teo", new ClientLauncher()));
            assertEquals(true, server.getServerLauncher().getOfflineNicknames().contains("teo"));
            assertEquals(false, server.getServerLauncher().getOfflineNicknames().contains("teo2"));
            assertEquals(true, server.register(new ClientLauncher(), "teo"));
            assertEquals(true, server.getServerLauncher().getNicknames().contains("teo"));
            assertEquals(true, server.getServerLauncher().getNicknames().size() == 2);
            assertEquals(true, server.getServerLauncher().getOfflineNicknames().isEmpty());
            assertEquals(false, server.getServerLauncher().getOfflineNicknames().contains("teo"));
        }
        catch(RemoteException e){}
    }

}
