package it.polimi.ingsw.view;

public class VPlayer {
    private String name;
    private VColor color;
    private VFrame frame;
    private VWindowPattern wpattern;

    public VPlayer(String name) {
        this.name = name;
    }

    public VPlayer(String name, VColor color, VWindowPattern wpattern) {
        this.name = name;
        this.color = color;
        this.wpattern = wpattern;
        this.frame = new VFrame();

    }


    public String getName() {
        return this.name;
    }
    public VColor getColor() {
        return this.color;
    }
    public VWindowPattern getWpattern() { return this.wpattern; }
    public VFrame getFrame() { return this.frame; }

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
