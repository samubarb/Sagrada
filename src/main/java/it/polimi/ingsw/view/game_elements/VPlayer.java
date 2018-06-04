package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.cards.VWindowPattern;

public class VPlayer {
    private String name;
    private VColor color;
    private VFrame frame;
    private VWindowPattern wpattern;
    private VPrivateObjectives vPrivateObjectives;

    public VPlayer(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append(this.color.toString() + this.name + VColor.RESET)
                .append("\n")
                .append(this.frame.toString())
                .append("\n")
                .append(this.wpattern.toString());
                /*.append()
                .append()
                .append()
                .append()
                .append()
                .append()*/

        return string.toString();
    }
}
