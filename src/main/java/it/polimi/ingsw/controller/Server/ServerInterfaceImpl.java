package it.polimi.ingsw.controller.Server;

import it.polimi.ingsw.controller.RMIApi.ServerInterface;
import it.polimi.ingsw.model.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ServerInterfaceImpl implements ServerInterface {
    public static final int MAXPLAYER = 4;

    private static String nomeGiocatore;
    private static String[] registeredPlayers = new String[4];
    private ArrayList<String> nicknames;
    private ArrayList<String> offlineNicknames;
    private static int numberOfPlayer = 0;

    @Override
    public int print(String stringa) throws RemoteException{
        System.out.println(stringa);
        return 1;
    }

    /*@Override
    public int login(String stringa) throws RemoteException{
        nomeGiocatore = stringa;
        System.out.println("si è connesso "+nomeGiocatore);
        if("matteo".equals(nomeGiocatore))
            return 1;
        else
            return 0;
    }*/

    @Override
    public boolean register(Player clientPlayer, String username) throws RemoteException {
        allocateLazy();
        if(this.nicknames.size()>=MAXPLAYER)
            return false;
        if (this.offlineNicknames.contains(username)) {
            this.offlineNicknames.remove((String) username);
            this.nicknames.add(username);
            //ridò il game al giocatore
            return true;
        }
        else if(!this.nicknames.contains(username)){
            nicknames.add(username);
            return true;
        }
        else
            return false;
    }

    void allocateLazy(){
        if (nicknames == null) {
            nicknames = new ArrayList<String>();
            offlineNicknames = new ArrayList<String>();
        }
    }

    @Override
    public int getNumberOfPlayer() throws RemoteException {
        return nicknames.size();
    }

    public ArrayList<String> getNicknames() {
        return nicknames;
    }

    public ArrayList<String> getOfflineNicknames() {
        return offlineNicknames;

    }

}
