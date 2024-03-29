package it.polimi.ingsw.model;

import java.awt.*;
import java.io.Serializable;

public class Dice implements Serializable{
    //private Color color;
    private Color color;
    private int value;

    public Dice() {
        this.value = 0;
        this.color=Color.UNCOLORED;
    }

    public Dice(int value) {
        this.value = value;
    }

    public Dice(Color color, int value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color=color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void copyDice(Dice dice){
        this.setValue(dice.getValue());
        this.setColor(dice.getColor());
    }

    @Override
    public String toString() {
        return "Dice{" +
                "color=" + color +
                ", value=" + value +
                '}';
    }
}
