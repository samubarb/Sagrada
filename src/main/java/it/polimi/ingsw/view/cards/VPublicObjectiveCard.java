package it.polimi.ingsw.view.cards;

import javafx.scene.image.ImageView;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VPublicObjectiveCard extends VObjectiveCard{
    private int points;

    public VPublicObjectiveCard(String name, int points) {
        super(name);
        this.points = points;
    }

    @Override
    protected String getDescription() {
        return "public description here";
    }

    @Override
    public String toString() {
        return newline + super.getName() + " " + "Punti: " + points + newline + this.getDescription() + newline;
    }

    public ImageView toGUI(int prog_number) {
        String number = "" + prog_number;
        return getImage(public_obj_path + "pub" + number + ".jpg");
    }
}
