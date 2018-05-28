package it.polimi.ingsw.view;

import it.polimi.ingsw.view.exceptions.UsernameTooShortException;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class CLI implements View {
    private VPlayer player;
    private VGame game;
    private static VSettings settings;
    private VSettings tempSettings;
    private static String Sn = "[S/n]";

    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.startCLI();
    }

    public CLI() {
        this.settings = new VSettings();
    }

    public void startCLI() {
        this.splash();
        while(this.chooser(this.menu()));
    }

    public void updateState(VGame game) {
        this.game = game;
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
