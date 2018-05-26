package it.polimi.ingsw.controller.Server;

import com.google.gson.Gson;

import static it.polimi.ingsw.inputoutput.IOManager.fileToString;
import static it.polimi.ingsw.inputoutput.IOManager.newline;
import static it.polimi.ingsw.inputoutput.IOManager.println;

public class ServerSettings {
    private String ipaddress;
    private int port;

    public ServerSettings() {}

    public ServerSettings(String ipaddress, int port) {
        this.ipaddress = ipaddress;
        this.port = port;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public int getPort() {
        return port;
    }

    public ServerSettings setFromJSON() {
        String fileToString = fileToString("./JSONconf/ServerSettings.json");
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
