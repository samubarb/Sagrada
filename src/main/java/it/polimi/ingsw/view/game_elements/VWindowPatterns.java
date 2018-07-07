package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.VWindowPattern;
import it.polimi.ingsw.view.exceptions.InvalidPositionException;
import javafx.scene.layout.HBox;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VWindowPatterns {
    VWindowPattern[] patterns;

    public VWindowPatterns(int size) {
        patterns = new VWindowPattern[size];
    }

    public void add(VWindowPattern pattern, int position) throws InvalidPositionException{
        if (position < 0 || position > this.patterns.length) {
            throw new InvalidPositionException();
        }
        else
            this.patterns[position] = pattern;
    }

    public int size() {
        return this.patterns.length;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < this.patterns.length; i++)
            string.append("(" + (i + 1) + ") ").append(patterns[i]).append(newline);

        int height = patterns[0].toString().split(newline).length + 1;

        return makeHorizontal(string.toString(), height, gridSpace);
    }

    public HBox toGUI() {
        HBox tools = new HBox();
        for (VWindowPattern pattern : this.patterns)
            tools.getChildren().add(pattern.toGUI());
        return tools;
    }
}
