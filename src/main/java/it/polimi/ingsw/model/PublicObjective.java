package it.polimi.ingsw.model;


import java.io.Serializable;

public class PublicObjective implements Serializable{

    private String name;
    private int points;
    //private Color color;

    public PublicObjective() {
    }

    public PublicObjective(String name,int points) {
        this.name = name;
        this.points=points;
       // this.color = color;
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

    /*public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }*/
}
