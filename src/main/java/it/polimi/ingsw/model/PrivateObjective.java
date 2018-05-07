package it.polimi.ingsw.model;

import java.awt.*;

public class PrivateObjective implements iObjective{

    private String name;
    private Color color;


    public PrivateObjective(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "PrivateObjective{" +
                "name='" + name + '\'' +
                ", color=" + color +
                '}';
    }

    @Override
    public int calculateScore(Player player) {
        Frame fakeFrame;
        fakeFrame=player.getFrame();
        int totalValue=0;
        for (int i=0; i<4;i++){
            for(int j=0; j<5;j++)
                if(fakeFrame.getDice(i,j).getColor().equals(this.color))
                    totalValue+=fakeFrame.getDice(i,j).getValue();
        }
        return totalValue;


    }
}
