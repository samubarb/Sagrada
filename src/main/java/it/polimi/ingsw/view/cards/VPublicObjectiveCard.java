package it.polimi.ingsw.view.cards;

import it.polimi.ingsw.view.exceptions.NameIncompatibleException;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VPublicObjectiveCard extends VObjectiveCard{
    private int points;

    public VPublicObjectiveCard(String name, int points) {
        super(name);
        this.points = points;
    }

    @Override
    protected String getDescription() {
        return getPublicObjectiveDescription(this.getName());
    }

    @Override
    public String toString() {
        return newline + super.getName() + " " + "Punti: " + points + newline + this.getDescription() + newline;
    }

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
