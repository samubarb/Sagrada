package it.polimi.ingsw.controller.Server;

import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.client.ClientLauncher;
import it.polimi.ingsw.controller.client.rmiClient.RMIClientLauncher;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    String username;
    PlayerInterface playerInterface;
    boolean isOnline;

    public User(String username, PlayerInterface playerInterface) {
        this.username = username;
        this.playerInterface = playerInterface;
        this.isOnline = true;
    }
    public User(){
        this.username="dacco";
        this.playerInterface = new RMIClientLauncher();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PlayerInterface getPlayerInterface() {
        return playerInterface;
    }

    public void setPlayerInterface(PlayerInterface playerInterface) {
        this.playerInterface = playerInterface;
    }



}
