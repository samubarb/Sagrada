package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.other_elements.VColor;
import javafx.scene.image.ImageView;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VPrivateObjectiveCard extends VObjectiveCard{
    private VColor color;

    public VPrivateObjectiveCard(String name, VColor color) {
        super(name);
        this.color = color;
    }

    @Override
    protected String getDescription() {
        return getPrivateObjectiveDescriptions(this.color);
    }

    @Override
    public String toString() {
        return newline + this.color + this.getDescription() + VColor.RESET + newline ;
    }

    public ImageView toGUI(int prog_number) {
        String number = "" + prog_number;
        return getImage(private_obj_path + "pri" + number + ".jpg");
    }
}
