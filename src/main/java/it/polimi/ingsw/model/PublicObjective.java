package it.polimi.ingsw.model;


import java.io.Serializable;

public class PublicObjective implements Serializable,iObjective{

    private String name;
    private int points;

    public PublicObjective() {
    }

    public PublicObjective(String name,int points) {
        this.name = name;
        this.points=points;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int calculateScore(Player player) {
    return 0;
    }

}
