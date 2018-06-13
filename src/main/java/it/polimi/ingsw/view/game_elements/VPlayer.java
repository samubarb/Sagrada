package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.cards.VWindowPattern;

import static it.polimi.ingsw.inputoutput.IOManager.errorExit;
import static it.polimi.ingsw.inputoutput.IOManager.newline;
import static it.polimi.ingsw.inputoutput.IOManager.println;

public class VPlayer implements Comparable {
    private String name;
    private VColor color;
    private VFrame frame;
    private VWindowPattern wpattern;
    private VPrivateObjectives vPrivateObjectives;
    private int score;

    public VPlayer(String name) {
        this.name = name;
    }
    public VPlayer(String name, VColor color) { this.name = name; this.color = color; }

    public VPlayer(String name, VColor color, VWindowPattern wpattern) {
        this.name = name;
        this.color = color;
        this.frame = new VFrame();
        this.wpattern = wpattern;
    }

    public String getName() {
        return this.name;
    }
    public VColor getColor() {
        return this.color;
    }
    public VWindowPattern getWpattern() { return this.wpattern; }
    public VFrame getFrame() { return this.frame; }
    public int getScore() {
        return this.score;
    }

    public void setColor(VColor color) {
        this.color = color;
    }
    public void setFrame(VFrame frame) {
        this.frame = frame;
    }
    public void setWpattern(VWindowPattern wpattern) {
        this.wpattern = wpattern;
    }
    public void setvPrivateObjectives(VPrivateObjectives vPrivateObjectives) { this.vPrivateObjectives = vPrivateObjectives; }
    public void setScore(int score) {
        this.score = score;
    }

    public String getReadyForPodium() {
        return this.color + this.name + " " + this.score + newline;
    }

    @Override
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        try {
            VPlayer other = (VPlayer) o;
            return this.getScore() > other.getScore() ? BEFORE : AFTER;
        } catch (ClassCastException e) {
            println("");
            errorExit();
        }

        return EQUAL;
    }

    /*
    public VPlayer compareTo(VPlayer other) {
        return this.getScore() > other.getScore() ? this : other;
    }
    */

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append(newline).
                append(this.color.toString() + this.name + VColor.RESET).append(newline).
                append(this.frame.toString()).append(newline).
                append(this.wpattern.toString()).append(newline);
        return string.toString();
    }
}
