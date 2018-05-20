package it.polimi.ingsw.view;

public class VPlayer {
    private String name;
    private VColor color;

    public VPlayer(String name) {
        this.name = name;
    }

    public VPlayer(String name, VColor color) {
        this.name = name;
        this.color = color;
    }

    public void setColor(VColor color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public VColor getColor() {
        return color;
    }

    @Override
    public String toString() {
        return this.color.toString() + this.name + VColor.RESET;
    }
}
