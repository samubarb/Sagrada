package it.polimi.ingsw.controller.Server;

import it.polimi.ingsw.controller.NetworkSettings;

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

    public NetworkSettings setFromJSON() {
        return super.setFromJSON(this.serverJSONpath);
    }

    @Override
    public String toString() {
        return "Server Settings " + super.toString();
    }
}
