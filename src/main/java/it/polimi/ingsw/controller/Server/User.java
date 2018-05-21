package it.polimi.ingsw.controller.Server;

import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.client.ClientLauncher;

import java.io.Serializable;

public class User implements Serializable {
    String username;
    PlayerInterface playerInterface;

    public User(String username, PlayerInterface playerInterface) {
        this.username = username;
        this.playerInterface = playerInterface;
    }
    public User(){
        this.username="dacco";
        this.playerInterface = new ClientLauncher();
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
