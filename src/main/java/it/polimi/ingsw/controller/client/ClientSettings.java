package it.polimi.ingsw.controller.client;

import com.google.gson.Gson;
import it.polimi.ingsw.controller.RMIApi.NetworkSettings;

import static it.polimi.ingsw.inputoutput.IOManager.fileToString;
import static it.polimi.ingsw.inputoutput.IOManager.newline;

/*
 * Class used to set the neededd parameters to guarantee the communication between server and client(s).
 * Take the ClientSetting from a JSON file (./JSONconf/) and set the attributes consequently
 */
public class ClientSettings extends NetworkSettings {
    private String clientJSONpath;
    private String clientAddress;

    public ClientSettings() {
        super();
        this.clientJSONpath = "./JSONconf/ClientSettings.json";
    }

    public ClientSettings setFromJSON() {
        super.setFromJSON(this.clientJSONpath);
        String fileToString = fileToString(this.clientJSONpath);
        ClientSettings settings = new Gson().fromJson(fileToString, ClientSettings.class);

        this.clientAddress = settings.getClientAddress();

        return this;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    @Override
    public String toString() {
        return "Client Settings " + newline + "Client IP :" + this.clientAddress + newline + super.toString();
    }
}
