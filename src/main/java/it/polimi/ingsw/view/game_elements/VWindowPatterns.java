package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.VWindowPattern;
import it.polimi.ingsw.view.exceptions.InvalidPositionException;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VWindowPatterns {
    VWindowPattern[] patterns;

    /**
     * represents a set of window pattern cards
     * @param size int maximum size
     */
    public VWindowPatterns(int size) {
        patterns = new VWindowPattern[size];
    }

    /**
     * add a window patter card to the set in the wanted position
     * @param pattern
     * @param position
     * @throws InvalidPositionException
     */
    public void add(VWindowPattern pattern, int position) throws InvalidPositionException{
        if (position < 0 || position > this.patterns.length) {
            throw new InvalidPositionException();
        }
        else
            this.patterns[position] = pattern;
    }

    public VWindowPattern[] getPatterns() {
        return patterns;
    }

    /**
     * ger the maximum size of the set
     * @return
     */
    public int size() {
        return this.patterns.length;
    }

    /**
     * get the text version of the set of wp cards, to be shown in the CLI
     * @return
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < this.patterns.length; i++)
            string.append("(" + (i + 1) + ") ").append(patterns[i]).append(newline);

        int height = patterns[0].toString().split(newline).length + 1;

        return makeHorizontal(string.toString(), height, gridSpace);
    }

    /**
     * get the graphic version of the set of wp cards, to be shown in the GUI
     * @return
     */
    public HBox toGUI() {
        HBox wpatterns = new HBox();
        for (VWindowPattern pattern : this.patterns)
            wpatterns.getChildren().add(pattern.toGUI());

        wpatterns.setSpacing(padding);

        return wpatterns;
    }
}
