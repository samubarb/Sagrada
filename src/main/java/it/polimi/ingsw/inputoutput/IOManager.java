package it.polimi.ingsw.inputoutput;

import com.google.gson.Gson;
import it.polimi.ingsw.view.exceptions.ColorIncompatibleException;
import it.polimi.ingsw.view.other_elements.VColor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public final class IOManager {
    public final static String img_path = "./img/";
    public final static String tools_path = img_path + "toolcards/";
    public final static String objectives_path = img_path + "objectives/";
    public final static String private_obj_path = objectives_path + "private/";
    public final static String public_obj_path = objectives_path + "public/";

    private final static String json_path = "./JSONconf/";
    private final static String tool_description_path = json_path + "ToolCardDescription.json";
    private final static String private_obj_description_path = json_path + "PrivateObjectiveDescription.json";

    public final static String newline = "\n";
    public static String Sn = "[S/n]";
    public static int gridSpace = 34; // tune it for horizontal spacing of Frames and WindowPatterns

    public final static int rows = 4;
    public final static int cols = 5;

    public final static int padding = 10;
    public final static int thinPadding = 2;

    public final static int cellWidth = 64;
    public final static int cellHeight = 64;
    public final static int thirdOfCell = cellWidth / 3;
    public final static int dotRadius = thirdOfCell * 4 / 5 / 2;
    public final static int cardWidth = 300;



    public static ImageView getImage(String filePath) {
        ImageView imageView = new ImageView();
        try {
            Image image;
            image = new Image(new FileInputStream(filePath));
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            println("Il file " + filePath + " non esiste. Controlla la correttezza del percorso o del nome.");
            println("Uscendo...");
            System.exit(2);
        }

        // Set dimensions
        imageView.setFitWidth(cardWidth);
        imageView.setPreserveRatio(true);
        return imageView;
    }

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

    public static String centerText(String toCenter, int maxLen) {
        int len = toCenter.replaceAll("\u001B\\[[;\\d]*m", "").length();
        int spaces = (maxLen - len) / 2;

        if (maxLen <= 0 || maxLen < len)
            return "";
        if (len == maxLen)
            return toCenter;

        StringBuilder centered = new StringBuilder();
        for (int i = 0; i < spaces; i++)
            centered.append(" ");
        centered.append(toCenter);
        for (int i = 0; i < spaces; i++)
            centered.append(" ");

        return centered.toString();
    }

    public static String makeHorizontal(String input, int rows, int columnSpace) {
        String[] chopped = input.split("\n");

        StringBuilder string = new StringBuilder();
        for(int i = 0; i < rows; i++) {
            for (int j = i ; j < chopped.length; j += rows) {
                string.append(centerText(chopped[j], columnSpace));
            }
            string.append(newline);
        }

        return string.toString();
    }

    private static String[] getToolDescriptions() {
        String file = fileToString(tool_description_path);
        return new Gson().fromJson(file, String[].class);
    }

    public static String getToolDescription(int i) {
        return getToolDescriptions()[i - 1];
    }

    private static String[] getPrivateObjectiveDescriptions() {
        String file = fileToString(private_obj_description_path);
        return new Gson().fromJson(file, String[].class);
    }

    public static String getPrivateObjectiveDescriptions(VColor color) {
        String description = "";

        try {
            description = getPrivateObjectiveDescriptions()[getPrivateObjectiveNumber(color)];
        } catch (ColorIncompatibleException e) {
            println(" Color is incompatible.");
            errorExit();
        }
        return description;
    }

    private static int getPrivateObjectiveNumber(VColor color) throws ColorIncompatibleException {
        switch (color) {
            case RED:
                return 1;
            case YELLOW:
                return 2;
            case GREEN:
                return 3;
            case BLUE:
                return 4;
            case PURPLE:
                return 5;
                default:
                    throw new ColorIncompatibleException();
        }
    }

    public static String getString() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }

    public static int getInt() {
        int value = 0;
        boolean flag = true;

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext())
            scanner.next();

        int i = 0;
        while (flag) {
            flag = false;
            try {
                value = scanner.nextInt();
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

    public static boolean getEnter() {
        return getString().equals("");
    }

    public static void enter() {
        while (!getEnter());
    }

    public static boolean Sn() {
        String answer = getString().toLowerCase();

        return (answer.equals("") || answer.equals("s") || answer.equals("si") || answer.equals("sì"));
    }

    public static void retry() {
        println("Riprova con un numero tra quelli mostrati.");
    }

    public static void println(Object toPrint) {
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