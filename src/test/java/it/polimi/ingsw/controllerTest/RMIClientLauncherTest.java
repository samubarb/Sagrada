package it.polimi.ingsw.controllerTest;

import it.polimi.ingsw.controller.RMIApi.ServerInterface;
import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.controller.Server.Rmi.ServerInterfaceImpl;
import it.polimi.ingsw.controller.Server.ServerLauncher;
import it.polimi.ingsw.controller.Server.ServerSettings;
import it.polimi.ingsw.controller.Server.User;
import it.polimi.ingsw.controller.client.rmiClient.RMIClientLauncher;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameConfigurator;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.exceptions.*;
import it.polimi.ingsw.view.CLI;
import it.polimi.ingsw.view.game_elements.VGame;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RMIClientLauncherTest {

    @Test
    public void testConnect() {
        ServerLauncher serverLauncher = new ServerLauncher();
        try {
            serverLauncher.startSocketRMIServer(12899, new ServerSettings().setFromJSON().getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
        RMIClientLauncher clientLauncher = new RMIClientLauncher();
        String address = new ServerSettings().setFromJSON().getServerAddress();//getIpServer(input);
        int port = new ServerSettings().setFromJSON().getPort();//getPortServer(input);
         boolean returnValue = clientLauncher.connect(address, port);
        assertEquals(true, returnValue);
        returnValue = clientLauncher.connect("ciao", 1234);
        assertEquals(false, returnValue);
        return;
    }

    @Test
    public void testGetServer() {
        ServerInterface server = new ServerInterfaceImpl();
        RMIClientLauncher clientLauncher = new RMIClientLauncher();
        clientLauncher.setServer(server);
        assertEquals(clientLauncher.getServer(), server);
        assertNotEquals(clientLauncher.getServer(), new ServerInterfaceImpl());
    }

    @Test
    public void testSetServer(){
        ServerInterface server = new ServerInterfaceImpl();
        RMIClientLauncher clientLauncher = new RMIClientLauncher();
        clientLauncher.setServer(server);
        assertEquals(server, clientLauncher.getServer());
    }
    @Test
    public void testGetAddress(){
        ServerInterface server = new ServerInterfaceImpl();
        RMIClientLauncher clientLauncher = new RMIClientLauncher();
        clientLauncher.setAddress(new ServerSettings().setFromJSON().getServerAddress());
        assertEquals(new ServerSettings().setFromJSON().getServerAddress(), clientLauncher.getAddress());
    }

    @Test
    public void testGetterAndSetter() {
        ServerInterface server = new ServerInterfaceImpl();
        RMIClientLauncher clientLauncher = new RMIClientLauncher();

        Game game = new Game();

        Player p1 = new Player("TestPlayer1", Color.RED);
        Player p2 = new Player("TestPlayer2", Color.BLUE);
        Player p3 = new Player("TestPlayer3", Color.GREEN);
        Player p4 = new Player("TestPlayer4", Color.PURPLE);

        game.setAddPlayer(p1);
        game.setAddPlayer(p2);
        game.setAddPlayer(p3);
        game.setAddPlayer(p4);

        new GameConfigurator(game);
        clientLauncher.setView(new CLI("TestPlayer1"));
        clientLauncher.setPort(new ServerSettings().setFromJSON().getPort());
        assertEquals(clientLauncher.getPort(), new ServerSettings().setFromJSON().getPort());
        assertEquals(new ServerSettings().setFromJSON().getPort(), clientLauncher.getPort());
        clientLauncher.setIsMyTurn(true);
        assertEquals(true, clientLauncher.isIsMyTurn());
        assertNotEquals(false, clientLauncher.isIsMyTurn());
        clientLauncher.setGameStarted(true);
        assertEquals(true, clientLauncher.isGameStarted());
        assertNotEquals(false, clientLauncher.isGameStarted());
        clientLauncher.setUsername("matteo");
        assertEquals("matteo", clientLauncher.getUsername());
        clientLauncher.setGame(game);
        assertEquals(game, clientLauncher.getGame());
        assertEquals("matteo", clientLauncher.ping());
        try {
            clientLauncher.setMyTurn(false);
            clientLauncher.setClientGame(game);
            assertEquals(clientLauncher.getGame(), game);
            clientLauncher.setClientGameHide(game);
            assertEquals(clientLauncher.getGame(), game);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        assertEquals(false, clientLauncher.isIsMyTurn());



    }

    @Test
    void testNotifier() {
        ServerInterface server = new ServerInterfaceImpl();
        RMIClientLauncher clientLauncher = new RMIClientLauncher();

        Game game = new Game();

        Player p1 = new Player("TestPlayer1", Color.RED);
        Player p2 = new Player("TestPlayer2", Color.BLUE);
        Player p3 = new Player("TestPlayer3", Color.GREEN);
        Player p4 = new Player("TestPlayer4", Color.PURPLE);

        game.setAddPlayer(p1);
        game.setAddPlayer(p2);
        game.setAddPlayer(p3);
        game.setAddPlayer(p4);

        new GameConfigurator(game);
        clientLauncher.setView(new CLI("TestPlayer1"));
        try {
            clientLauncher.setClientGame(game);

            VGame vgame = new AdapterCLI().gameToView(game);
            clientLauncher.notifyDisconnection(new User());
            clientLauncher.notifyReconnection("matteo");
            clientLauncher.printaaa("matteo");
            clientLauncher.onRegister("Registered");
            clientLauncher.notifyConnection("matteo");
            clientLauncher.notifyTurn("matteo");
            clientLauncher.notifyScoreBoard();
            clientLauncher.notifyError(new RemoteException());
            clientLauncher.notifyError(new FavorTokenException());
            clientLauncher.notifyError(new NutChosenWrongException());
            clientLauncher.notifyError(new IllegalArgumentException());
            clientLauncher.notifyError(new WindowPatternColorException());
            clientLauncher.notifyError(new WindowPatternValueException());
            clientLauncher.notifyError(new FrameValueAndColorException());
            clientLauncher.notifyError(new BusyPositionException());
            clientLauncher.notifyError(new AdjacentDiceException());
            clientLauncher.notifyWinner();
            clientLauncher.notifyLoosers();
            clientLauncher.notifyUserReconnection("dac");
            clientLauncher.notifyMaxPlayerReached();
            clientLauncher.notifyNameInUse();
            clientLauncher.notifyUserLogged("dac");
            clientLauncher.notifyGameStarted();
            clientLauncher.notifyUserDisconnection("dac");
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
}
