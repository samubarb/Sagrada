package it.polimi.ingsw.view;

import it.polimi.ingsw.view.exceptions.IllegalCoordinatesException;
import it.polimi.ingsw.view.exceptions.UsernameTooShortException;
import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.game_elements.VGame;
import it.polimi.ingsw.view.game_elements.VPlayer;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import it.polimi.ingsw.view.other_elements.VMove;
import it.polimi.ingsw.view.other_elements.VSettings;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class CLI implements View {
    private VPlayer clientPlayer;
    private VGame game;
    private static VSettings settings;
    private VSettings tempSettings;

    public static void main(String[] args) {
        CLI cli = new CLI("Test Player");
        cli.startCLI();
    }

    public CLI(String player) {
        this.clientPlayer = new VPlayer(player);
        this.settings = new VSettings(); // reset settings to the default
        this.game = new VGame();
    }

    public void updateGame(VGame game) {
        this.game = game;
        this.game.setClientPlayer(this.clientPlayer.getName());
    }

    public void startCLI() {
        this.splash();
        while(this.chooser(this.menu()));
    }

    public int askDice() {
        println("Scegli il dado da pescare");
        return this.game.askDice();
    }

    public int askDice(int i) {
        println("Scegli il dado numero " + i);
        return this.game.askDice();
    }

    public VCoordinates askCoordinates(int i) {
        return new VCoordinates(1,1);
    }

    public int askWindowPattern() {
        return this.game.askWindowPattern();
    }

    public int askToolCard() {
        return this.game.askToolCard();
    }

    public int askMove() {
        println("Scegli cosa fare tra:");
        println("1. Piazzare un dado");
        println("2. Usare una Tool Card");
        println("3. Passare il turno");
        return getIntBound(1, 3);
    }

    public VCoordinates askCoordinates() {
        println("Dove lo vuoi posizionare");
        return askXy();
    }

    private VCoordinates askXy() {
        boolean flag;
        VCoordinates xy;

        do {
            flag = false;

            print("Inserisci la coordinata X del piazzamento [1-5]: ");
            int x = getInt();
            print("Inserisci la coordinata Y del piazzamento [1-4]: ");
            int y = getInt();

            xy = new VCoordinates(x, y);

            try {
                xy.check();
            } catch (IllegalCoordinatesException e) {
                flag = true;
                println("Coordinate non valide.");
            }
        } while (flag);

        return xy;
    }

    public void updateState(VGame game) {
        this.game = game;
        clearScreen();
        println(this.game.toString());
    }
    public VMove move(VDice dice, VCoordinates xy) { // Add toolcard here
        return new VMove(dice, xy);
    }

    private boolean chooser(int i) {
        clearScreen();
        switch (i) {
            case 1:
                playGame();
                break;
            case 2:
                settings();
                break;
            case 3:
                gameRules();
                break;
            case 4:
                credits();
                break;
            case 5:
                if (exit())
                    System.exit(0);
                break;
            default:
                retry();
                break;
        }
        clearScreen();
        return true;
    }

    private void playGame() {
        println("...start game here");
        waitKey();
    }

    private void settings() {
        this.tempSettings = new VSettings();
        while (this.settingsChooser(this.settingsMenu()));
    }

    private int settingsMenu() {
        clearScreen();
        println("Impostazioni");
        println("1. Interfaccia grafica");
        println("2. Connessione");
        println("3. Indietro");
        print("Scegli l'opzione digitando il numero corrispondente: ");

        return getInt();
    }

    private boolean settingsChooser(int i) {
        clearScreen();
        boolean flag = false;
        switch (i) {
            case 1:
                flag = true;
                while(graphicsSettings());
                break;
            case 2:
                flag = true;
                while(connectionSettings());
                break;
            case 3:
                if (flag) askToSaveChanges();
                this.settings.reset(); // reset to the default values of CLI + RMI, to change with future implementation
                return false;
            default:
                retry();
                break;
        }
        return true;
    }

    private void askToSaveChanges() {
        println("Vuoi salvare i cambiamenti? " + Sn);
        if (Sn()) {
            this.settings = this.tempSettings;
            println("Cambiamenti salvati.");
        } else {
            println("Nulla è stato salvato.");
        }
    }

    private boolean graphicsSettings(){
        clearScreen();
        println("Quale interfaccia vuoi utilizzare? ");
        println("1. CLI");
        println("2. GUI");

        switch(getInt()) {
            case 1:
                this.tempSettings.setCLI();
                break;
            case 2:
                this.tempSettings.setGUI();
                break;
            default:
                retry();
                return true;
        }
        return false;
    }

    private boolean connectionSettings(){
        clearScreen();
        println("Quale connessione vuoi utilizzare? ");
        println("1. RMI");
        println("2. Socket");

        switch(getInt()) {
            case 1:
                this.tempSettings.setRMI();
                break;
            case 2:
                this.tempSettings.setSocket();
                break;
            default:
                retry();
                return true;
        }
        return false;
    }

    private void gameRules() {
        println("Show game rules here");
        waitKey();
    }

    private void credits() {
        println("Gioco sviluppato da Samuele Barbieri, Riccardo Cedroni, Matteo Chiari");
        waitKey();
    }

    private boolean exit() {
        print("Sicuro di voler uscire dal gioco? " + Sn);
        if (Sn()) {
            println("Uscendo...");
            return true;
        }
        return false;
    }

    private int menu() {
        println("1. Gioca");
        println("2. Impostazioni");
        println("3. Regole del gioco");
        println("4. Credits");
        println("5. Esci");
        print("Scegli l'opzione digitando il numero corrispondente: ");

        return getInt();
    }

    private void splash() {
        String splash = "  @@@  @@ @@  @@@@      @@@@@@  @   @@@@@@  .@   @ @   @@   #@@@  @@    @ @    \n" +
                "@@@*@@@@  @@*@@@@@@   @@@ @@   @@@ @   *@%   @@@@@ @@@ @@@ @@@@@@@ ,@@@@@ @@   \n" +
                "@@    @@     @    @@  @    @@   @@@     @@@   @     %@   @      @ @   @    @@. \n" +
                "@@           @@   @@  @@        @@@   @@@@    @@    @@   @@     @@@  @@    @@. \n" +
                "@@@         &@@   @@  @@     @  @@@@@@@@@     @@ @@ @@   @@     @@@  @@    @@. \n" +
                "@@@@@@@ @@   @@@@@,@  @@ @@@ @@ @@@ @@@@@     @@  #&@@          .@@  @@    @@. \n" +
                " @ @@@@@@@   @@@@ @@  @@    .@@ @@@   @@@@@   @@    @@   @@     @@@  @@ @@ @@. \n" +
                "       @@@@  @@   @@  @ * @@   @@@@      @@  @@@@@@@ @   @@     @@@  @@ @@ @@@ \n" +
                "       .@@@  @@   @@@  @@ @@ @@ @@@     %@@@ @@@@ @@@@@ @@@@  @@@@@  @@    @@& \n" +
                "        @@@  @@   @@@@(                  ,@@@     @@@   @  @@@@@@ @  @@    @@% \n" +
                "       @@@, @@@@                           @@@@@/@@@         @@@     @@    @@/ \n" +
                "      @@@@ *@@@                               @@                   ,@@@    @@, \n" +
                "@@ @@@@@@                                                            @@    @@  \n" +
                "@@@@@@@@                                                                  @@@  \n" +
                "@@@                                                                       @@@@ \n" +
                "@@@                                                                         #@@\n" +
                "@                                                                              \n";



        clearScreen();
        print(splash + centerText("Premi INVIO per continuare", "@@    @@     @    @@  @    @@   @@@     @@@   @     %@   @      @ @   @    @@. ".length()));
        enter();
        clearScreen();
    }

    private String centerText(String toCenter, int maxLen) {
        int len = toCenter.length();

        if (maxLen <= 0 || maxLen < len)
            return "";
        if (len == maxLen)
            return toCenter;

        StringBuilder centered = new StringBuilder();
        for (len = (maxLen - len) / 2; len >= 0; len--) {
            centered.append(" ");
        }
        centered.append(toCenter).append("\n");
        return centered.toString();
    }

    public void loggedIn(String playerLogged) {
        this.game.setClientPlayer(playerLogged);
        println("Accesso effettuato come " + playerLogged);
    }

    public void setTurn(VPlayer player) {
        this.game.setTurn(player);
    }

    public String askNewUsername() throws UsernameTooShortException {
        println("Inventa un nuovo Username: ");
        String user = getString();
        if (user.length() >= 3) {
            return user;
        } else {
            throw new UsernameTooShortException();
        }
    }

    public String askNewPassword() {
        println("Scegli una password: ");
        return getString();
    }

    public String chooseAnotherUsername(String username) throws UsernameTooShortException {
        println("Lo username " + username + " è già stato preso.");
        return askNewUsername();
    }

    public String askUsername() {
        println("Username: ");
        return getString();
    }

    public String askPassword() {
        println("Password: ");
        return getString();
    }

    private String newUser() {
        println("Scegli un nuovo Username: ");
        return getString();
    }
}
