package it.polimi.ingsw.controller.ProvaServer;

import it.polimi.ingsw.model.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ServerInterfaceImpl implements ServerInterface {
    public static final int MAXPLAYER = 4;

    private static String nomeGiocatore;
    private static String[] registeredPlayers = new String[4];
    private ArrayList<String> nicknames;
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
    public Boolean register(Player clientPlayer, String username) throws RemoteException {
        allocateLazy();
        int count = nicknames.size();
        if (count >= MAXPLAYER)
            return false;
        if (nicknames.contains(username))
            return false;
        nicknames.add(username);
        return true;

    }
    void allocateLazy(){
        if (nicknames == null) {
            nicknames = new ArrayList<String>();
        }
    }

}
