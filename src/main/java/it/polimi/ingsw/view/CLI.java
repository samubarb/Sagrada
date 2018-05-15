package it.polimi.ingsw.view;

public class CLI implements View {
    private VGame game;
    private static Settings settings;

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
        //cli.settings();
    }

    public void startCLI() {
        this.splash();
        this.chooser(this.menu());
        this.settings();
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
                println("Retry");
                break;
        }
    }

    private void playGame() {
        println("...start game here");
    }

    private void settings() {
        boolean answer = true;
        Settings settings = new Settings();

        // Customize settings here
        settings.reset();
        println("Are you sure you want to change settings? [Y/n]" );

        if (answer == true) {
            this.settings = settings;
            println("Settings changed.");
        } else {
            println("Nothing changed.");
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

    private void println(String toPrint) {
        System.out.println(toPrint);
        System.out.flush();
    }

    private void print(String toPrint) {
        System.out.print(toPrint);
        System.out.flush();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
    }
}
