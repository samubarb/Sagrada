package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.exceptions.ColorIncompatibleException;
import it.polimi.ingsw.view.other_elements.VColor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VPrivateObjectiveCard extends VObjectiveCard{
    private VColor color;

    public VPrivateObjectiveCard(String name, VColor color) {
        super(name);
        this.color = color;
    }

    @Override
    protected String getDescription() {
        return getPrivateObjectiveDescription(this.color);
    }

    @Override
    public String toString() {
        return newline + this.color + this.getDescription() + VColor.RESET + newline ;
    }

    public Pane toGUI() {
        String number = null;
        try {
            number = "" + getPrivateObjectiveNumber(this.color);
        } catch (ColorIncompatibleException e) {
            println("Colore carta obiettivo privato non valido.");
            errorExit();
        }
        return new Pane(getImage(private_obj_path + "pri" + number + ".jpg"));
    }
}
