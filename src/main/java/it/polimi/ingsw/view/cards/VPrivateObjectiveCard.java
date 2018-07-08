package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.exceptions.ColorIncompatibleException;
import it.polimi.ingsw.view.other_elements.VColor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VPrivateObjectiveCard extends VObjectiveCard{
    private VColor color;

    /**
     * represents the private extension of the objective card to be shown
     * @param name set the card name
     * @param color set the card color
     */
    public VPrivateObjectiveCard(String name, VColor color) {
        super(name);
        this.color = color;
    }

    /**
     * get the private card formatted description
     * @return String with the description
     */
    @Override
    protected String getDescription() {
        return getPrivateObjectiveDescription(this.color);
    }

    /**
     * get the whole private card formatted for CLI
     * @return printalbe String
     */
    @Override
    public String toString() {
        return newline + this.color + this.getDescription() + VColor.RESET + newline ;
    }

    /**
     * get the gui version of the private card
     * @return Pane with the card image
     */
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
