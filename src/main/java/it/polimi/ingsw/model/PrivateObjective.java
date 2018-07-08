package it.polimi.ingsw.model;


import java.io.Serializable;

public class PrivateObjective implements iObjective,Serializable{

    private String name;
    private Color color;
    public static final int LINE_SIZE= 4;
    public static final int COLUMN_SIZE = 5;

    public PrivateObjective() {
    }

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

    /**
     * calculate final player's score
     * @param player
     * @return
     */
    @Override
    public int calculateScore(Player player) {
        Frame fakeFrame;
        fakeFrame=player.getFrame();
        int totalValue=0;
        for (int i=0; i<LINE_SIZE;i++){
            for(int j=0; j<COLUMN_SIZE;j++)
                if(fakeFrame.getDice(i,j).getColor().equals(this.color))
                    totalValue+=fakeFrame.getDice(i,j).getValue();
        }
        return totalValue;


    }
}
