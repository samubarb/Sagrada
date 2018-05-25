package it.polimi.ingsw.view;

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
        VMove move = new VMove(dice, xy);

        return move;
    }

    public static void main(String[] args) {
        CLI cli = new CLI();
        cli.startCLI();

        int i = 1;
        for (VColor vc : VColor.values()) {
            println(new VDice(i, vc).toString());
            i++;
        }
    }

    public void startCLI() {
        this.splash();
        this.chooser(this.menu());
    }

    private void chooser(int i) {
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
                exit();
                break;

            default:
                println("Riprova");
                break;
        }
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



    private void exit() {
        String ask = " [Y/n]";
        print("Are you sure you want to exit the game?" + ask);

    }

    private int menu() {
        println("Choose your option:");
        println("1. Play");
        println("2. Settings");
        println("3. Credits");
        println("4. Exit");

        return 1;
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
    }

    private static void println(String toPrint) {
        System.out.println(toPrint);
        System.out.flush();
    }

    private static void print(String toPrint) {
        System.out.print(toPrint);
        System.out.flush();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
