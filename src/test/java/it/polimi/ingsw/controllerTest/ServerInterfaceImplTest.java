package it.polimi.ingsw.controllerTest;


import it.polimi.ingsw.controller.Server.Rmi.ServerInterfaceImpl;
import it.polimi.ingsw.controller.Server.ServerLauncher;
import it.polimi.ingsw.controller.Server.User;
import it.polimi.ingsw.controller.client.ClientLauncher;
import it.polimi.ingsw.controller.client.rmiClient.RMIClientLauncher;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameConfigurator;
import it.polimi.ingsw.model.Player;
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
        RMIClientLauncher clientLauncher = new RMIClientLauncher();
            server.getServerLauncher().getNicknames().add(new User());
            System.out.println(serverLauncher.getNicknames().size());
            System.out.println(serverLauncher.getNicknames().size());
            assertEquals(true, serverLauncher.getNicknames().size() == 1);
            assertEquals(true, serverLauncher.contains("dacco",serverLauncher.getNicknames()));
            assertEquals(false, serverLauncher.getNicknames().size() == 2);
            assertEquals(false, serverLauncher.contains("teo",serverLauncher.getNicknames()));

    }
}
