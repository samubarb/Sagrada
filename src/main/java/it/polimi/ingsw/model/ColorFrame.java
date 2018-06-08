package it.polimi.ingsw.model;

import java.io.Serializable;

public class ColorFrame implements Serializable {

    private Color color;
    private boolean alreadyUsed;

    public ColorFrame() {
        this.color = Color.UNCOLORED;
        this.alreadyUsed = false;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isAlreadyUsed() {
        return alreadyUsed;
    }

    public void setAlreadyUsed(boolean alreadyUsed) {
        this.alreadyUsed = alreadyUsed;
    }
}
