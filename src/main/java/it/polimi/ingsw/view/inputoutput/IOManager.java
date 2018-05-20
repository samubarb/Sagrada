package it.polimi.ingsw.view.inputoutput;

import java.util.Scanner;

public final class IOManager {


    public static String getString() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }

    public static int getInt() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextInt();
    }

    public static void enter() {
        while (!getString().equals(""));
        return;
    }

    public static boolean Sn() {
        String answer = getString().toLowerCase();

        if (answer.equals("") || answer.equals("s") || answer.equals("si") || answer.equals("sì"))
            return true;
        return false;
    }

    public static boolean Yn() {
        String answer = getString().toLowerCase();

        if (answer.equals("") || answer.equals("y") || answer.equals("yes"))
            return true;
        return false;
    }

}