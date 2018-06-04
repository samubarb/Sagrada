package it.polimi.ingsw.view;

import java.util.ArrayList;

public class VPrivateObjective {
    private ArrayList<VObjectiveCard> privateObjetive;

    public VPrivateObjective() {

    }

    public void add(VObjectiveCard vObjectiveCard) {
        this.privateObjetive.add(vObjectiveCard);
    }

    /*public ArrayList<VObjectiveCard> get() {
        return this.privateObjetive.clone();
    }*/
}
