package it.polimi.ingsw.model;

import java.io.Serializable;

public class ToolCard implements Serializable {
    private int number;
    private String name;
    private Color color;
    private boolean alreadyUsed;

    public ToolCard() {
    }

    public ToolCard(int number, String name,Color color) {
        this.number = number;
        this.name = name;
        this.alreadyUsed = false;
        this.color=color;

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAlreadyUsed() {
        return alreadyUsed;
    }

    public void setAlreadyUsed(Boolean alreadyUsed) {
        this.alreadyUsed = alreadyUsed;
    }


}
