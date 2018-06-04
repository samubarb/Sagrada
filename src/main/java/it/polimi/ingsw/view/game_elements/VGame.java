package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.VObjectiveCard;
import it.polimi.ingsw.view.cards.VPrivateObjectiveCard;
import it.polimi.ingsw.view.cards.VPublicObjectiveCard;
import it.polimi.ingsw.view.exceptions.TooManyPlayersException;
import it.polimi.ingsw.view.game_elements.VCurrentDice;
import it.polimi.ingsw.view.game_elements.VPlayer;

import java.util.ArrayList;

import static it.polimi.ingsw.inputoutput.IOManager.newline;

public class VGame {
    private ArrayList<VPlayer> players;
    private ArrayList<VPublicObjectiveCard> publicObjectives;
    private VCurrentDice dice;

    private VRoundTrack roundTrack;
    private int round;
    private VPlayer turn, clientPlayer;


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


    public void removePlayer(VPlayer player) { this.players.remove(player); } // remove player to the game
    public VPlayer getClientPlayer() {
        return this.clientPlayer;
    }

    @Override
    public String toString() {
            StringBuilder string = new StringBuilder();
            string.append("Round: " + this.round).append(newline);
            if (this.turn.getName().equals(this.clientPlayer.getName()))
                string.append("È il tuo turno, " + this.turn);
            else
                string.append("È il turno di " + this.turn);
        return string.toString();
    }
}
