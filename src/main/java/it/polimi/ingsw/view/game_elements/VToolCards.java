package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.VToolCard;
import it.polimi.ingsw.view.exceptions.InvalidPositionException;
import javafx.scene.layout.HBox;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public class VToolCards {
    private VToolCard[] toolCards;

    public VToolCards(int size) {
        this.toolCards = new VToolCard[size];
    }

    public void add(VToolCard tool, int position) throws InvalidPositionException {
        if (position < 0 || position > this.toolCards.length) {
            throw new InvalidPositionException();
        }
        else
            this.toolCards[position] = tool;
    }

    public int size() {
        return this.toolCards.length;
    }

    VToolCard getToolCard(int i) throws ArrayIndexOutOfBoundsException {
        if (i < toolCards.length)
            return toolCards[i];
        else throw new ArrayIndexOutOfBoundsException();
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

    public HBox toGUI() {
        HBox tools = new HBox();
        for (VToolCard tool : this.toolCards)
            tools.getChildren().add(tool.toGUI());
        return tools;
    }
}
