package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.VPrivateObjectiveCard;

import java.util.ArrayList;

public class VPrivateObjectives {
    private ArrayList<VPrivateObjectiveCard> privateObjetive;

    public VPrivateObjectives() {
        this.privateObjetive = new ArrayList<VPrivateObjectiveCard>();
    }

    public void add(VPrivateObjectiveCard vPrivateObjectiveCard) {
        this.privateObjetive.add(vPrivateObjectiveCard);
    }

}
