package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.exceptions.NameIncompatibleException;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VPublicObjectiveCard extends VObjectiveCard{
    private int points;

    /**
     * represents the private extension of the objective card to be shown
     * @param name set the card name
     * @param points set the card points
     */
    public VPublicObjectiveCard(String name, int points) {
        super(name);
        this.points = points;
    }

    /**
     * get the public card formatted description
     * @return String with the description
     */
    @Override
    protected String getDescription() {
        return getPublicObjectiveDescription(this.getName());
    }

    /**
     * get the whole public card formatted for CLI
     * @return printalbe String
     */
    @Override
    public String toString() {
        return newline + super.getName() + " " + "Punti: " + points + newline + this.getDescription() + newline;
    }

    /**
     * get the gui version of the public card
     * @return Pane with the card image
     */
    public Pane toGUI() {
        String number = null;
        try {
            number = "" + getPublicObjectiveNumber(this.getName());
        } catch (NameIncompatibleException e) {
            println("Nome della carta non valido.");
            errorExit();
        }
        return new Pane(getImage(public_obj_path + "pub" + number + ".jpg"));
    }
}
