package it.polimi.ingsw.controller.Server;

import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.client.ClientLauncher;
import it.polimi.ingsw.controller.client.rmiClient.RMIClientLauncher;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that represent the clients on server
 */
public class User implements Serializable {
    /**
     * Client username
     */
    String username;
    /**
     * Client's player interface used for comunication
     */
    PlayerInterface playerInterface;
    /**
     * Bollean that represent if the player s online or not
     */
    boolean isOnline;

    /**
     * Constructor of the class
     * @param username username of the player
     * @param playerInterface player interface of the player
     */
    public User(String username, PlayerInterface playerInterface) {
        this.username = username;
        this.playerInterface = playerInterface;
        this.isOnline = true;
    }

    /**
     * Constructor of the class used in test
     */
    public User(){
        this.username="dacco";
        this.playerInterface = new RMIClientLauncher();
        this.isOnline = true;
    }

    /**
     * Getter of the username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter of the username
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter of the player interface
     * @return the player interface
     */
    public PlayerInterface getPlayerInterface() {
        return playerInterface;
    }

    /**Setter of the player interface
     * @param playerInterface the player interface
     */
    public void setPlayerInterface(PlayerInterface playerInterface) {
        this.playerInterface = playerInterface;
    }

    /**
     * Getter if a player is online or not
     * @return if a player is online or not
     */
    public boolean isOnline() {
        return isOnline;
    }

    /**
     * Setter if a player is online or not
     * @param online represents id the player is online or not
     */
    public void setOnline(boolean online) {
        isOnline = online;
    }
}
