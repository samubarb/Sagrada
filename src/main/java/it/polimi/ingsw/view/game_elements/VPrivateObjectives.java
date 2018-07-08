package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.VPrivateObjectiveCard;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public class VPrivateObjectives {
    private ArrayList<VPrivateObjectiveCard> privateObjetives;

    /**
     * represents a set of private objective cards
     */
    public VPrivateObjectives() {
        this.privateObjetives = new ArrayList<>();
    }

    /**
     * add a privat objective card to the set
     * @param vPrivateObjectiveCard
     */
    public void add(VPrivateObjectiveCard vPrivateObjectiveCard) {
        this.privateObjetives.add(vPrivateObjectiveCard);
    }

    /**
     * get all the cards in the set, to be shown in CLI
     * @return
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (VPrivateObjectiveCard privObj : privateObjetives)
            string.append(privObj).append(newline);
        return string.toString();
    }

    /**
     * get all the cards in the set, to be shown in GUI
     * @return
     */
    public HBox toGUI() {
        HBox privateObjs = new HBox();

        for (int i = 0; i < this.privateObjetives.size(); i++)
            privateObjs.getChildren().add(this.privateObjetives.get(i).toGUI());

        return privateObjs;
    }
}

