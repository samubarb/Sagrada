package it.polimi.ingsw.view;

import it.polimi.ingsw.view.exceptions.IllegalCoordinatesException;
import it.polimi.ingsw.view.exceptions.UsernameTooShortException;
import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.game_elements.VGame;
import it.polimi.ingsw.view.game_elements.VPlayer;
import it.polimi.ingsw.view.game_elements.VWindowPatterns;
import it.polimi.ingsw.view.other_elements.*;

import static it.polimi.ingsw.inputoutput.IOManager.*;

/**
 *
 */
public class CLI implements View {
    private String clientPlayer;
    private VGame game;
    private static VSettings settings;
    private VSettings tempSettings;

    public CLI(String clientPlayer) {
        this.clientPlayer = clientPlayer;
        this.settings = new VSettings(); // reset settings to the default
        this.game = new VGame();
        this.game.setClientPlayer(clientPlayer);
    }

    public int askToPickFromRoundTrack() {
        return this.game.askToPickFromTrack();
    }

    public int askDice() {
        println("Scegli il dado da pescare");
        return this.game.askDice();
    }

    @Override
    public int askDiceNumber(VDice dice) {
        println("Scegli il numero per il tuo " + dice.getColor() + "DADO: " + VColor.RESET);
        return getInt(1, 6);
    }

    @Override
    public int askWindowPattern(VWindowPatterns vpCards) {
        println("Scegli la tua carta Window Pattern tra le seguenti: ");
        println(newline);
        println(vpCards);
        return getInt(1, vpCards.size()) - 1;
    }

    @Override
    public int askToolCard() {
        return this.game.askToolCard();
    }

    @Override
    public boolean askAction() {
        println("Incremento o decremento?");
        println("1. Incremento");
        println("2. Decremento");
        int value = getInt(1, 2);
        if (value == 1)
            return true;
        return false;
    }

    public int askMove() {
        println("Scegli cosa fare tra:");
        println("1. Piazzare un dado");
        println("2. Usare una Tool Card");
        println("3. Passare il turno");
        return getInt(1, 3);
    }

    public VCoordinates askCoordinates() {
        println("Inserisci le coordinate: ?");
        return askXy();
    }

    public VCoordinates askCoordinates(int i) {
        println("Inserisci le coordinate del dado numero " + i +"?");
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

    public void notifyWin() {
        println("Hai vinto " + this.clientPlayer + "!");
    }

    public void notifyLose() {
        println("Hai perso " + this.clientPlayer + "!");
    }
    public void notifyError(VError error) { println(error.toString()); }
    public void notifyScore() { game.notifyScore(); }

    public void updateState(VGame game) {
        game.setClientPlayer(this.clientPlayer);
        this.game = game;
        clearScreen();
        println(this.game.toString());
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

    public void splash() {
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

    public String askNewUsername() throws UsernameTooShortException {
        println("Inventa un nuovo Username: ");
        String user = getString();
        if (user.length() >= 3) {
            return user;
        } else {
            throw new UsernameTooShortException();
        }
    }


    public String chooseAnotherUsername(String username) throws UsernameTooShortException {
        println("Lo username " + username + " è già stato preso.");
        return askNewUsername();
    }

    public String askUsername() {
        println("Username: ");
        return getString();
    }
}
