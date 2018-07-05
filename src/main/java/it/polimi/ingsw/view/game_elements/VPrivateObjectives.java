package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.VPrivateObjectiveCard;

import java.util.ArrayList;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public class VPrivateObjectives {
    private ArrayList<VPrivateObjectiveCard> privateObjetive;

    public VPrivateObjectives() {
        this.privateObjetive = new ArrayList<VPrivateObjectiveCard>();
    }

    public void add(VPrivateObjectiveCard vPrivateObjectiveCard) {
        this.privateObjetive.add(vPrivateObjectiveCard);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (VPrivateObjectiveCard privObj : privateObjetive)
            string.append(privObj).append(newline);
        return string.toString();
    }
}
