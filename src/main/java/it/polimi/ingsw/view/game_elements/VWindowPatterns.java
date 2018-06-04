package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.VWindowPattern;
import it.polimi.ingsw.view.exceptions.InvalidPositionException;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public class VWindowPatterns {
    VWindowPattern[] patterns;

    public VWindowPatterns(int size) {
        patterns = new VWindowPattern[size];
    }

    public void add(VWindowPattern pattern, int position) throws InvalidPositionException{
        if (position > this.patterns.length) {
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
            string.append("(" + (i + 1) + ") ").append(patterns[i].toString()).append(newline);
        return string.toString();
    }
}
