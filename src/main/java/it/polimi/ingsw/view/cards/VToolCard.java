package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.other_elements.VColor;
import javafx.scene.image.ImageView;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VToolCard {
    private String name;
    private VColor color;
    private int number;

    /**
     * represents the tool card to be shown
     * @param name set card name
     * @param color set card color
     * @param number set card progressive number
     */
    public VToolCard(String name, VColor color, int number) {
        this.name = name;
        this.color = color;
        this.number = number;
    }

    /**
     * get the card description
     * @return a String
     */
    private String getDescription() {
        return getToolDescription(this.number);
    }

    /**
     * get the card name
     * @return a String
     */
    public String getName() {
        return this.name;
    }

    /**
     * get the card progressive number
     * @return an int positive value, between 1 and 12
     */
    public int getNumber() {
        return number;
    }

    /**
     * get the toolcard color
     * @return one of the VColor enum values
     */
    public VColor getColor() {
        return color;
    }

    /**
     * get a formatted version for CLI
     * @return a printable String
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(this.color).append(this.name).append(VColor.RESET).append(newline).
                append(this.getDescription()).append(newline);
        return string.toString();
    }

    /**
     * get a formatted version for GUI
     * @return an ImageView with the card image
     */
    public ImageView toGUI() {
        String number = "" + this.number;
        return getImage(tools_path + "tool" + number + ".jpg");
    }
}
