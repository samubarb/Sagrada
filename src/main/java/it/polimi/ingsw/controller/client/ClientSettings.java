package it.polimi.ingsw.controller.client;

import it.polimi.ingsw.controller.NetworkSettings;

/*
 * Class used to set the neededd parameters to guarantee the communication between server and client(s).
 * Take the ClientSetting from a JSON file (./JSONconf/) and set the attributes consequently
 */
public class ClientSettings extends NetworkSettings {
    private String clientJSONpath;

    public ClientSettings() {
        super();
        this.clientJSONpath = "./JSONconf/ClientSettings.json";
    }

    public NetworkSettings setFromJSON() {
        return super.setFromJSON(this.clientJSONpath);
    }

    @Override
    public String toString() {
        return "Client " + super.toString();
    }
}
