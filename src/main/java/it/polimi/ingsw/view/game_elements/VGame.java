package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.VObjectiveCard;
import it.polimi.ingsw.view.cards.VPrivateObjectiveCard;
import it.polimi.ingsw.view.cards.VPublicObjectiveCard;
import it.polimi.ingsw.view.exceptions.TooManyPlayersException;
import it.polimi.ingsw.view.game_elements.VCurrentDice;
import it.polimi.ingsw.view.game_elements.VPlayer;

import java.util.ArrayList;

public class VGame {
    private ArrayList<VPlayer> players;
    private ArrayList<VPublicObjectiveCard> publicObjectives;

    private VPlayer turn, clientPlayer;
    private VCurrentDice dice;
    private int round;

    public VGame() {
        this.players = new ArrayList<VPlayer>();
        this.publicObjectives = new ArrayList<VPublicObjectiveCard>();
    }

    public void addVPlayer(VPlayer player) throws TooManyPlayersException { // add a player to the game
        if (this.players.size() >= 5)
            throw new TooManyPlayersException();
        else
            this.players.add(player);
    }

    public void removePlayer(VPlayer player) { this.players.remove(player); } // remove player to the game
    public void setTurn(VPlayer player) {
        this.turn = player;
    }
    public void setVCurrentDice(VCurrentDice dice) { this.dice = dice; }
    public void setClientPlayer(String clientPlayer) {
        this.clientPlayer = new VPlayer(clientPlayer);
    }
    public VPlayer getClientPlayer() {
        return this.clientPlayer;
    }
    public VPlayer getTurn() {
        return this.turn;
    }

    public void addPublicObjective(VPublicObjectiveCard card) {
        this.publicObjectives.add(card);
    }

    @Override
    public String toString() {
            StringBuilder string = new StringBuilder();
            string.append("Round: " + this.round);
            if (this.turn == this.clientPlayer)
                string.append("È il tuo turno, " + this.turn);
            else
                string.append("È il turno di " + this.turn);
        return string.toString();
    }
}
