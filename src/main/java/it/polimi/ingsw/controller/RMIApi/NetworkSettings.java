package it.polimi.ingsw.controller.RMIApi;

import com.google.gson.Gson;

import static it.polimi.ingsw.inputoutput.IOManager.fileToString;
import static it.polimi.ingsw.inputoutput.IOManager.newline;

/*
 * Class used to set the needed parameters to guarantee the communication between server and client(s).
 * This class is meant to be specialized in the Server and Client form.
 */
public class NetworkSettings {
    private String serverAddress;
    private int port;

    public NetworkSettings() {}

    public NetworkSettings(String serverAddress, String clientAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public String getServerAddress() {
        return serverAddress;
    }
    public int getPort() {
        return port;
    }

    public NetworkSettings setFromJSON(String path) {
        String fileToString = fileToString(path);
        NetworkSettings settings = new Gson().fromJson(fileToString, NetworkSettings.class);

        this.serverAddress = settings.getServerAddress();
        this.port = settings.getPort();
        return this;
    }

    @Override
    public String toString() {
        return "Server IP: " + this.serverAddress + newline + "Port: " + this.port;
    }
}
