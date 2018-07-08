package it.polimi.ingsw.controller.Server;

import it.polimi.ingsw.controller.RMIApi.NetworkSettings;

import java.io.File;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

/*
 * Class used to set the neededd parameters to guarantee the communication between server and client(s).
 * Take the ServerSetting from a JSON file (./JSONconf/) and set the attributes consequently
 */
public class ServerSettings extends NetworkSettings {
    private String serverJSONpath;

    public ServerSettings() {
        super();
        File file = new File("JSONconf/ServerSettings.json");
        String ciao=file.getAbsolutePath();
        this.serverJSONpath = ciao;//file.getAbsolutePath();

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
