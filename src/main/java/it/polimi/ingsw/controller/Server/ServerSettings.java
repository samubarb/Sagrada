package it.polimi.ingsw.controller.Server;

import it.polimi.ingsw.controller.NetworkSettings;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

/*
 * Class used to set the neededd parameters to guarantee the communication between server and client(s).
 * Take the ServerSetting from a JSON file (./JSONconf/) and set the attributes consequently
 */
public class ServerSettings extends NetworkSettings {
    private String serverJSONpath;

    public ServerSettings() {
        super();
        this.serverJSONpath = "./JSONconf/ServerSettings.json";
    }

    public ServerSettings setFromJSON() {
        super.setFromJSON(this.serverJSONpath);
        return this;
    }

    @Override
    public String toString() {
        return "Server Settings " + newline + super.toString();
    }
}
