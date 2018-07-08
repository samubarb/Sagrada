package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.VToolCard;
import it.polimi.ingsw.view.exceptions.InvalidPositionException;
import javafx.scene.layout.HBox;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public class VToolCards {
    private VToolCard[] toolCards;

    /**
     * represents a set of tool cards
     * @param size maximum size
     */
    public VToolCards(int size) {
        this.toolCards = new VToolCard[size];
    }

    /**
     * add a tool card to the set
     * @param tool
     * @param position
     * @throws InvalidPositionException if position exceeds the maximum size
     */
    public void add(VToolCard tool, int position) throws InvalidPositionException {
        if (position < 0 || position > this.toolCards.length) {
            throw new InvalidPositionException();
        }
        else
            this.toolCards[position] = tool;
    }

    /**
     * get the maximum size of the set
     * @return
     */
    public int size() {
        return this.toolCards.length;
    }

    /**
     * get a tool card given an index
     * @param i index of the wanted tool card
     * @return the tool card wanted
     * @throws ArrayIndexOutOfBoundsException if the index exceeds the maximum size
     */
    VToolCard getToolCard(int i) throws ArrayIndexOutOfBoundsException {
        if (i < toolCards.length)
            return toolCards[i];
        else throw new ArrayIndexOutOfBoundsException();
    }

    /**
     * get the text version of the tool card set, to be shown in the CLI
     * @return
     */
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

    /**
     * get the graphic version of the tool car set, to be shown in the GUI
     * @return
     */
    public HBox toGUI() {
        HBox tools = new HBox();
        for (VToolCard tool : this.toolCards)
            tools.getChildren().add(tool.toGUI());
        return tools;
    }
}
