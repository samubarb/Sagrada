package it.polimi.ingsw.view;

import it.polimi.ingsw.view.exceptions.IllegalCoordinatesException;
import static it.polimi.ingsw.inputoutput.IOManager.*;

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
        this.frame = new VFrame();
        this.wpattern = wpattern;
    }

    public String getName() {
        return this.name;
    }
    public VColor getColor() {
        return this.color;
    }
    public VWindowPattern getWpattern() { return this.wpattern; }
    public VFrame getFrame() { return this.frame; }

    // public

    public VMove askMove() {
        println("È il tuo turno " + this.color.toString() + this.name + VColor.RESET);
        println("Vuoi usare una ToolCard?" + Sn);
        // handle tool card here
        println("Quale dado vuoi scegliere? ");
        VDice dice  = askDice();
        println("Dove lo vuoi posizionare");
        VCoordinates xy = askXy();

        return new VMove(dice, xy);
    }

    VDice askDice() {
        return new VDice(4, VColor.BLUE);
    }

    VCoordinates askXy() {
        boolean flag;
        VCoordinates xy;

        do {
            flag = false;

            print("Inserisci la coordinata X del piazzamento [1-5]: ");
            int x = getInt();
            print("Inserisci la coordinata Y del piazzamento [1-5]: ");
            int y = getInt();

            xy = new VCoordinates(x, y);

            try {
                xy.check();
            } catch (IllegalCoordinatesException e) {
                flag = true;
                println("Coordinate non valide.");
            }
        } while (flag);

        return xy;
    }

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
