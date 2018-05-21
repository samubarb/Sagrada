package it.polimi.ingsw.controller.Server.Rmi;

import it.polimi.ingsw.controller.RMIApi.PlayerInterface;
import it.polimi.ingsw.controller.RMIApi.ServerInterface;
import it.polimi.ingsw.controller.Server.ServerLauncher;
import it.polimi.ingsw.model.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ServerInterfaceImpl implements ServerInterface {
    public static final int MAXPLAYER = 4;

    //private static String nomeGiocatore;
    //private static String[] registeredPlayers = new String[4];
    //private static int numberOfPlayer = 0;
    //private static final Object ROOMS_MUTEX = new Object();
    private ServerLauncher serverLauncher;


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
    public boolean register(PlayerInterface clientPlayer, String username) throws RemoteException {

        return serverLauncher.registerUser(clientPlayer, username);


        /*synchronized (ROOMS_MUTEX) {
            allocateLazy();
            if (this.nicknames.size() >= MAXPLAYER)
                return false;
            if (this.offlineNicknames.contains(username)) {
                this.offlineNicknames.remove((String) username);
                this.nicknames.add(username);
                //ridò il game al giocatore
                return true;
            } else if (!this.nicknames.contains(username)) {
                nicknames.add(username);
                return true;
            } else
                return false;
        }*/
    }

    /*void allocateLazy(){
        if (nicknames == null) {
            nicknames = new ArrayList<String>();
            offlineNicknames = new ArrayList<String>();
        }
    }*/

    @Override
    public int getNumberOfPlayer() throws RemoteException {
        return serverLauncher.getNicknames().size()+serverLauncher.getOfflineNicknames().size();
    }

    @Override
    public String getNumberOfPlayerActiveInactive() throws RemoteException {
        return serverLauncher.getNicknames().size()+" "+serverLauncher.getOfflineNicknames().size();
    }

    public void setServerLauncher(ServerLauncher serverLauncher) {
        this.serverLauncher = serverLauncher;
    }

    public ServerLauncher getServerLauncher() {
        return serverLauncher;
    }

}
