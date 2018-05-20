package it.polimi.ingsw.view;


import java.util.ArrayList;

public class VGame {
    private ArrayList<VPlayer> players;
    private VPlayer turn, clientPlayer;
    private int round;

    public VGame() {
        players = new ArrayList<VPlayer>();
    }

    public void addVPlayer(VPlayer player) {
        this.players.add(player);
    }

    public void setTurn(VPlayer player) {
        this.turn = player;
    }

    public void setClientPlayer(String clientPlayer) {
        this.clientPlayer = new VPlayer(clientPlayer);
    }

    public VPlayer getClientPlayer() {
        return clientPlayer;
    }

    public VPlayer getTurn() {
        return turn;
    }

    @Override
    public String toString() {
            StringBuilder string = new StringBuilder();
            string.append("Round: " + this.round);
            if (turn == clientPlayer)
                string.append("È il tuo turno, " + this.turn);
            else
                string.append("È il turno di " + this.turn);

        return string.toString();
    }
}
