package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.cards.VWindowPattern;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class VPlayer implements Comparable {
    private String name;
    private VColor color;
    private VFrame frame;
    private int favorTokens;
    private VWindowPattern wpattern;
    private VPrivateObjectives vPrivateObjectives;
    private int score;

    public VPlayer(String name) {
        this.name = name;
    }
    public VPlayer(String name, VColor color) { this.name = name; this.color = color; }

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
    public int getScore() {
        return this.score;
    }
    public VPrivateObjectives getvPrivateObjectives() { return this.vPrivateObjectives; }

    public void setColor(VColor color) {
        this.color = color;
    }
    public void setFrame(VFrame frame) {
        this.frame = frame;
    }
    public void setFavorTokens(int tokens) { this.favorTokens = tokens; }
    public void setWpattern(VWindowPattern wpattern) {
        this.wpattern = wpattern;
    }
    public void setvPrivateObjectives(VPrivateObjectives vPrivateObjectives) { this.vPrivateObjectives = vPrivateObjectives; }
    public void setScore(int score) {
        this.score = score;
    }

    public String getReadyForPodium() {
        return this.color + this.name + " " + this.score + VColor.RESET + newline;
    } // prepare Player and relative score to be sorted in the final ranking

    @Override
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        try {
            VPlayer other = (VPlayer) o;
            return this.getScore() > other.getScore() ? BEFORE : AFTER;
        } catch (ClassCastException e) {
            println("");
            errorExit();
        }
        return EQUAL;
    }

    @Override
    public String toString() {
        String frame[] = this.frame.toString().split(newline);
        String wpattern[] = this.wpattern.toString().split(newline);
        StringBuilder string = new StringBuilder();

        string.append(newline).
                append(centerText(this.color + this.name + VColor.RESET, gridSpace)).
                append(centerText(wpattern[0], gridSpace)).
                append(newline);

        for (int i = 0; i < frame.length; i++)
            string.append(centerText(frame[i], gridSpace)).append(centerText(wpattern[i + 1], gridSpace)).append(newline);

        return string.toString();
    }

    public Group toGUI() {
        Group root = new Group();

        GridPane grid = this.frame.buildGrid(this.wpattern);

        Label playerName = new Label(this.name);
        Label cardName = new Label(this.wpattern.getName());
        Label labelTokens = new Label("Segnalini favore: ");
        GridPane favorTokens = new GridPane();

        cardName.setFont(Font.font(null, FontWeight.NORMAL, 20));
        cardName.setPadding(new Insets(padding));
        playerName.setFont(Font.font(null, FontWeight.BOLD, 25));
        playerName.setPadding(new Insets(padding));

        labelTokens.setFont(Font.font(null, FontWeight.NORMAL, 14));

        favorTokens.setPadding(new Insets(padding));
        favorTokens.setHgap(thinPadding);

        GridPane player = new GridPane();
        player.setStyle("-fx-background-color: grey;");

        favorTokens.add(labelTokens, 0, 0);

        for (int i = 0; i < this.favorTokens; i++)
            favorTokens.add(new Circle(10, Color.WHITE), i + 1, 0);

        player.add(playerName, 0, 0);
        player.add(cardName, 0,1);
        player.add(grid, 0, 2);
        player.add(favorTokens, 0, 3);
        //player.add(this.vPrivateObjectives.toGUI(), 0, 4); // managed in game

        root.getChildren().addAll(player);

        return root;
    }
}
