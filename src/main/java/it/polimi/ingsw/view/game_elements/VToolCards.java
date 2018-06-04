package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.VToolCard;
import it.polimi.ingsw.view.exceptions.InvalidPositionException;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public class VToolCards {
    private VToolCard[] toolCards;

    public VToolCards(int size) {
        this.toolCards = new VToolCard[size];
    }

    public void add(VToolCard tool, int position) throws InvalidPositionException {
        if (position <= 0 || position > this.toolCards.length) {
            throw new InvalidPositionException();
        }
        else
            this.toolCards = toolCards;
    }

    public int size() {
        return this.toolCards.length;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Tool Cards").append(newline);
        for(int i = 0; i < this.toolCards.length; i++)
            string.append("(" + (i + 1) + ") ").
                    append(this.toolCards[i].toString()).
                    append(newline);
        return string.toString();
    }
}