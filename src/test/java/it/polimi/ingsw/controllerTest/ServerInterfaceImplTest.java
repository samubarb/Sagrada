package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.Server.ServerInterfaceImpl;
import it.polimi.ingsw.model.Player;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerInterfaceImplTest {
    /*
    This test checks getNumberOfPlayer
     */
    @Test
    void getNicknamesTest(){
        ServerInterfaceImpl server = new ServerInterfaceImpl();
        try {
            server.register(new Player(), "dacco");
            assertEquals(true, server.getNicknames().size() == 1);
            assertEquals(true, server.getNicknames().contains("dacco"));
            assertEquals(false, server.getNicknames().size() == 2);
            assertEquals(false, server.getNicknames().contains("teo"));
        }
        catch(RemoteException e){}
    }
    /*
This test checks getOfflineNumberOfPlayer
 */
    @Test
    void getOfflineNicknamesTest(){
        ServerInterfaceImpl server = new ServerInterfaceImpl();
        try {
            server.register(new Player(), "dacco1");
            server.getOfflineNicknames().add("dacco");
            assertEquals(true, server.getOfflineNicknames().size() == 1);
            assertEquals(true, server.getOfflineNicknames().contains("dacco"));
            assertEquals(false, server.getOfflineNicknames().size() == 2);
            assertEquals(false, server.getOfflineNicknames().contains("teo"));
        }
        catch(Exception e){}
    }
    /*
    This test checks single and multi login, in particular checks the case in which there are two players logging with the same name
     */
    @Test
    void registerSameNameTest() {
        System.out.println("testing");
        ServerInterfaceImpl server = new ServerInterfaceImpl();
        try {
            assertEquals(true, server.register(new Player(), "bob"));
            assertEquals(false, server.register(new Player(), "bob"));
        } catch (RemoteException e) {
        }
    }

    /*
    This test tests the case in whitch more than 4 player try to join the room
     */
    @Test
    void register5DIfferentPlayerTest() {
        System.out.println("testing");
        ServerInterfaceImpl server = new ServerInterfaceImpl();
        try {
            assertEquals(true, server.register(new Player(), "bob0"));
            assertEquals(true, server.register(new Player(), "bob1"));
            assertEquals(true, server.register(new Player(), "bob2"));
            assertEquals(true, server.register(new Player(), "bob3"));
            assertEquals(false, server.register(new Player(), "bob4"));
        } catch (Exception e) {
        }
    }

    @Test
    void getNumberOfPlayerTest() {
        System.out.println("testing");
        ServerInterfaceImpl server = new ServerInterfaceImpl();
        try {
            assertEquals(0, server.getNumberOfPlayer());
            server.register(new Player(), "bob0");
            assertEquals(1, server.getNumberOfPlayer());
            server.register(new Player(), "bob1");
            assertEquals(2, server.getNumberOfPlayer());
            server.register(new Player(), "bob2");
            assertEquals(3, server.getNumberOfPlayer());
            server.register(new Player(), "bob3");
            assertEquals(4, server.getNumberOfPlayer());
            assertEquals(false, server.getNumberOfPlayer() == 5);
        } catch (Exception e) {
        }
    }


    @Test
    void reconnectingInactivePlayerTest() {
        ServerInterfaceImpl server = new ServerInterfaceImpl();
        try {
            server.register(new Player(),"Dacco");
            server.getOfflineNicknames().add("teo");
            assertEquals(true, server.getOfflineNicknames().contains("teo"));
            assertEquals(false, server.getOfflineNicknames().contains("teo2"));
            assertEquals(true, server.register(new Player(), "teo"));
            assertEquals(true, server.getNicknames().contains("teo"));
            assertEquals(true, server.getNicknames().size() == 2);
            assertEquals(true, server.getOfflineNicknames().isEmpty());
            assertEquals(false, server.getOfflineNicknames().contains("teo"));
        }
        catch(RemoteException e){}
    }

}
