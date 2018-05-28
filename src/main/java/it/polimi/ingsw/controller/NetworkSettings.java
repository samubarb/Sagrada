package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.Server.ServerSettings;

import static it.polimi.ingsw.inputoutput.IOManager.fileToString;
import static it.polimi.ingsw.inputoutput.IOManager.newline;

/*
 * Class used to set the needed parameters to guarantee the communication between server and client(s).
 * This class is meant to be specialized in the Server and Client form.
 */
public class NetworkSettings {
    private String ipaddress;
    private int port;

    public NetworkSettings() {}

    public NetworkSettings(String ipaddress, int port) {
        this.ipaddress = ipaddress;
        this.port = port;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public int getPort() {
        return port;
    }

    public NetworkSettings setFromJSON(String path) {
        String fileToString = fileToString(path);
        ServerSettings settings = new Gson().fromJson(fileToString, ServerSettings.class);

        this.ipaddress = settings.getIpaddress();
        this.port = settings.getPort();
        return this;
    }

    @Override
    public String toString() {
        return "IP Address: " + this.ipaddress + newline + "Port: " + this.port;
    }
}
