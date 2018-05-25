package it.polimi.ingsw.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import it.polimi.ingsw.view.exceptions.UsernameTooShortException;
import it.polimi.ingsw.view.inputoutput.*;

import static it.polimi.ingsw.view.inputoutput.IOManager.*;

public class CLI implements View {
    private VPlayer player;
    private VGame game;
    private static VSettings settings;
    //private static String Yn = "[Y/n]";
    private static String Sn = "[S/n]";

    public void updateState(VGame game) {
        this.game = game;
    }

    public VMove move(VDice dice, VCoordinates xy) { // Add toolcard here
        return new VMove(dice, xy);
    }

    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.startCLI();

    }

    public void startCLI() {
        int i;
        this.splash();
        for (i = 0; i < 10; i++) {
            try {
                while (this.chooser(this.menu())) {
                    i = 0;
                }
            } catch (InputMismatchException e) {
                println("Devi inserire un intero positivo!");
            }
        }
        println("Uscendo per " + i + " tentativi falliti consecutivamente...");
        System.exit(1);
    }

    private boolean chooser(int i) {
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
                println("Riprova");
                break;
        }
        return true;
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
        if(user.length() >= 3) {
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

    private void playGame() {
        println("...start game here");
    }

    private void settings() {
        VSettings settings = new VSettings();

        // Customize settings here

        settings.reset();
        println("Sei sicuro di voler cambiare le impostazioni? " + Sn);
        if (Sn()) {
            this.settings = settings;
            println("Cambiamenti salvati.");
        } else {
            println("Nulla è stato salvato.");
        }
    }

    private void gameRules() {
        println("Show game rules here");
    }

    private void credits() {
        println("Game developed by Samuele Barbieri, Riccardo Cedroni, Matteo Chiari");
    }



    private boolean exit() {
        print("Are you sure you want to exit the game? " + Sn);
        if (Sn()) {
            println("Uscendo...");
            return true;
        }
        return false;
    }

    private int menu() {
        println("Choose your option:");
        println("1. Play");
        println("2. Settings");
        println("3. Game rules");
        println("4. Credits");
        println("5. Exit");

        return IOManager.getInt();
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
                "@                                                                              \n" +
                "                            Press ENTER to continue                            \n";

        print(splash);
        enter();
        clearScreen();
    }
}
