package it.polimi.ingsw.inputoutput;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public final class IOManager {
    public final static String newline = "\n";
    public static String Sn = "[S/n]";

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
        int value = 0;
        boolean flag = true;

        int i = 0;
        while (flag) {
            flag = false;
            try {
                value = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                flag = true;
                i++;
                if (i >= 10) {
                    println("Uscendo per " + i + " tentativi falliti consecutivamente...");
                    System.exit(1);
                }
                println("Devi inserire un intero positivo!");
            }
        }
        return value;
    }

    public static int getInt(int lower, int upper) {
        int value;
        boolean flag = true;

        do {
            if (flag)
                println("Inserisci il numero corrispondente: ");
            else
                println("Devi inserire un intero compreso tra " + lower + " e " + upper + ": ");
            value = getInt();
            flag = false;
        } while(value < lower || value > upper);

        return value;
    }

    public static void enter() {
        while (!getString().equals(""));
    }

    public static boolean Sn() {
        String answer = getString().toLowerCase();

        return (answer.equals("") || answer.equals("s") || answer.equals("si") || answer.equals("sì"));
    }

    public static void retry() {
        println("Riprova con un numero tra quelli mostrati.");
    }

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

    public static void waitKey() {
        print("Premi un tasto per continuare...");
        getString();
    }

    public static void fineExit() { // exit the program with no error state
        System.exit(0);
    }

    public static void errorExit() { // exit the program with an error state
        System.exit(1);
    }
}