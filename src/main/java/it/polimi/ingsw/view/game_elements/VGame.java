package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.*;
import it.polimi.ingsw.view.exceptions.TooManyPlayersException;

import java.util.ArrayList;

import static it.polimi.ingsw.inputoutput.IOManager.getInt;
import static it.polimi.ingsw.inputoutput.IOManager.newline;
import static it.polimi.ingsw.inputoutput.IOManager.println;

public class VGame {
    private final static int maxPlayer = 4;
    private ArrayList<VPlayer> players;
    private ArrayList<VPublicObjectiveCard> publicObjectives;
    private VCurrentDice dice;
    private VWindowPatterns patterns;
    private VToolCards tools;
    private VRoundTrack roundTrack;
    private int round;
    private VPlayer turn;

    public VGame() {
        this.players = new ArrayList<VPlayer>();
        this.publicObjectives = new ArrayList<VPublicObjectiveCard>();
    }

    public int askDice() {
        int value;
        println(this.dice.toString());
        do {
            value = getInt(1, this.dice.size());
        } while (this.dice.get(value - 1) == null);
        return value - 1; // return the wanted index
    }

    public int askToolCard() {
        println("Scegli quale Tool Card utilizzare" + newline);
        println(this.tools.toString());
        int value = getInt(1, this.tools.size());

        return this.tools.getToolCard(value - 1).getNumber() - 1;
    }

    /**
     * @return
     */
    public int askToPickFromTrack() {
        println("Quale dado vuoi scegliere?");
        println(this.roundTrack.toString());
        int value = getInt(1, this.roundTrack.size());
        return value - 1; // return the wanted index
    }

    public void addVPlayer(VPlayer player) throws TooManyPlayersException { // add a player to the game
        if (this.players.size() > maxPlayer)
            throw new TooManyPlayersException();
        else
            this.players.add(player);
    }

    public void setScore(String playerName, int score) {
        for(VPlayer vp : this.players)
            if (vp.getName() == playerName)
                vp.setScore(score);
    }

    public void addPublicObjective(VPublicObjectiveCard objCard) {
        this.publicObjectives.add(objCard);
    }
    public void setTools(VToolCards tools) {
        this.tools = tools;
    }
    public void setPatterns(VWindowPatterns patterns) {
        this.patterns = patterns;
    }
    public void setVCurrentDice(VCurrentDice dice) { this.dice = dice; }
    public void setRoundTrack(VRoundTrack track) { this.roundTrack = track; }
    public void setRound(int round) { this.round = round; }
    public void setTurn(VPlayer player) {
        this.turn = player;
    }

    public void removePlayer(VPlayer player) { this.players.remove(player); } // remove player from the game

    public void notifyScore() {
        StringBuilder string = new StringBuilder();
        string.append("Classifica partita: ").append(newline);
        players.stream().sorted((p1, p2) -> p1.compareTo(p2)).forEach(p -> string.append(p.getReadyForPodium()));
        println(string.toString());
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.
                append("Round: " + this.round).append("\n\n").
                append(this.roundTrack.toString()).append(newline);
        /*if (this.turn.getName().equals(this.clientPlayer.getName()))
            string.append("È il tuo turno, ");
        else*/
        string.append("È il turno di ");
        string.append(this.turn.getName()).append(newline);

        for (VPlayer vp : this.players) {
            string.append(vp.toString());
        }

        for (VPublicObjectiveCard objCard : this.publicObjectives) {
            string.append(objCard.toString());
        }

        string.append(newline).
                append(dice.toString()).append(newline);

        return string.toString();
    }
}