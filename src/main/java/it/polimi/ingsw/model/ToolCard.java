package it.polimi.ingsw.model;

public class ToolCard {
    private int number;
    private String name;
    private boolean alreadyUsed;


    public ToolCard(int number, String name) {
        this.number = number;
        this.name = name;
        this.alreadyUsed = false;

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
