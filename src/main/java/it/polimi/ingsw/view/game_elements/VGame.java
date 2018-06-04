package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.VObjectiveCard;
import it.polimi.ingsw.view.cards.VPrivateObjectiveCard;
import it.polimi.ingsw.view.cards.VPublicObjectiveCard;
import it.polimi.ingsw.view.cards.VWindowPattern;
import it.polimi.ingsw.view.exceptions.TooManyPlayersException;
import it.polimi.ingsw.view.game_elements.VCurrentDice;
import it.polimi.ingsw.view.game_elements.VPlayer;

import java.util.ArrayList;

import static it.polimi.ingsw.inputoutput.IOManager.getInt;
import static it.polimi.ingsw.inputoutput.IOManager.newline;
import static it.polimi.ingsw.inputoutput.IOManager.println;

public class VGame {
    private ArrayList<VPlayer> players;
    private ArrayList<VPublicObjectiveCard> publicObjectives;
    private VCurrentDice dice;
    private VWindowPatterns patterns;

    private VRoundTrack roundTrack;
    private int round;
    private VPlayer turn, clientPlayer;


    public VGame() {
        this.players = new ArrayList<VPlayer>();
        this.publicObjectives = new ArrayList<VPublicObjectiveCard>();
    }

    public int askDice() {
        int value;
        do {
            println("Quale dei dadi vuoi pescare?");
            println(this.dice.toString());
            println("Inserisci il numero corrispondente: ");
            value = getInt();
        } while (value <= 0 || value > this.dice.size());

        return value - 1; // return the index
    }

    public int askWindowPattern() {
        println("Scegli il tuo Window Pattern tra questi: ");
        println(patterns.toString());
        int value;
        do {
            println("Inserisci il numero corrispondente: ");
            value = getInt();
        } while(value <= 0 || value > patterns.size());

        return value - 1; // return the index
    }

    public int askToolCard() {

        return 0;
    }

    public void addVPlayer(VPlayer player) throws TooManyPlayersException { // add a player to the game
        if (this.players.size() >= 5)
            throw new TooManyPlayersException();
        else
            this.players.add(player);
    }

    public void addPublicObjective(VPublicObjectiveCard objCard) {
        this.publicObjectives.add(objCard);
    }

    public void setVCurrentDice(VCurrentDice dice) { this.dice = dice; }
    public void setRoundTrack(VRoundTrack track) { this.roundTrack = track; }
    public void setRound(int round) { this.round = round; }
    public void setTurn(VPlayer player) {
        this.turn = player;
    }
    public void setClientPlayer(String clientPlayer) {
        this.clientPlayer = new VPlayer(clientPlayer);
    }

    public void removePlayer(VPlayer player) { this.players.remove(player); } // remove player from the game
    public VPlayer getClientPlayer() {
        return this.clientPlayer;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.
                append("Round: " + this.round).append(newline).
                append(this.roundTrack.toString()).append(newline);
        if (this.turn.getName().equals(this.clientPlayer.getName()))
            string.append("È il tuo turno, ");
        else
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
