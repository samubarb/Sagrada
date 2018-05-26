package it.polimi.ingsw.inputoutput;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public final class IOManager {
    public final static String newline = "\n";

    public static String fileToString(String filePath)
    {
        StringBuilder string = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEachOrdered(s -> string.append(s).append(newline));
        }
        catch (IOException e)
        {
            println("Il file " + filePath + " non esiste. Controlla la correttezza del percorso o del nome.");
            println("Uscendo...");
            System.exit(2);
        }

        return string.toString();
    }

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
/*
    public static boolean Yn() {
        String answer = getString().toLowerCase();

        if (answer.equals("") || answer.equals("y") || answer.equals("yes"))
            return true;
        return false;
    }
*/
    public static void println(String toPrint) {
        System.out.println(toPrint);
        System.out.flush();
    }

    public static void print(String toPrint) {
        System.out.print(toPrint);
        System.out.flush();
    }

    public static void newline() {
        System.out.println(newline);
        System.out.flush();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}