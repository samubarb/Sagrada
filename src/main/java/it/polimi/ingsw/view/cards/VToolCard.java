package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.other_elements.VColor;
import javafx.scene.image.ImageView;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VToolCard {
    private String name;
    private VColor color;
    private int number;


    public VToolCard(String name, VColor color, int number) {
        this.name = name;
        this.color = color;
        this.number = number;
    }

    private String getDescription() {
        return getToolDescription(this.number);
    }

    public String getName() {
        return this.name;
    }

    public VColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(this.color).append(this.name).append(VColor.RESET).append(newline).
                append(this.getDescription()).append(newline);
        return string.toString();
    }

    public ImageView toGUI() {
        String number = "" + this.number;
        return getImage(tools_path + "tool" + number + ".jpg");
    }
}
