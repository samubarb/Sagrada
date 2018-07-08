package it.polimi.ingsw.view.game_elements;

import it.polimi.ingsw.view.cards.*;
import it.polimi.ingsw.view.exceptions.TooManyPlayersException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.*;

import java.util.ArrayList;

import static it.polimi.ingsw.inputoutput.IOManager.*;

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
    private String clientPlayer;

    /**
     * represents the whole game elements and players
     */
    public VGame() {
        this.players = new ArrayList<VPlayer>();
        this.publicObjectives = new ArrayList<VPublicObjectiveCard>();
    }

    /**
     * used by the CLI to make the user pick up a dice from currentDice
     * @return VDice
     */
    public int askDice() {
        int value;
        println(this.dice.toString());
        do {
            value = getInt(1, this.dice.size());
        } while (this.dice.get(value - 1) == null);
        return value - 1; // return the wanted index
    }

    /**
     * used by the CLI to make the user pick up a toolcard to use
     * @return int corresponding to the progressive toolcard number, minus one to be an array index
     */
    public int askToolCard() {
        println("Scegli quale Tool Card utilizzare" + newline);
        println(this.tools.toString());
        int value = getInt(1, this.tools.size());

        return this.tools.getToolCard(value - 1).getNumber() - 1;
    }

    /**
     * used by the CLI to make the user pick up a dice from the roundTrack
     * @return int corresponding to the round number of that dice, minus one to be an array index
     */
    public int askToPickFromTrack() {
        println("Quale dado vuoi scegliere?");
        println(this.roundTrack.toString());
        int value = getInt(1, this.roundTrack.size());
        return value - 1; // return the wanted index
    }

    /**
     * add a player to the game
     * @param player the player to add
     * @throws TooManyPlayersException maximum number of player is 4
     */
    public void addVPlayer(VPlayer player) throws TooManyPlayersException { // add a player to the game
        if (this.players.size() > maxPlayer)
            throw new TooManyPlayersException();
        else
            this.players.add(player);
    }

    /**
     * set each player's score
     * @param playerName String with the player name
     * @param score int positive number
     */
    public void setScore(String playerName, int score) {
        for(VPlayer vp : this.players)
            if (vp.getName().equals(playerName))
                vp.setScore(score);
    }

    /**
     * add public objective to the game
     * @param objCard objective card
     */
    public void addPublicObjective(VPublicObjectiveCard objCard) {
        this.publicObjectives.add(objCard);
    }

    /**
     * set tool cards to the game
     * @param tools
     */
    public void setTools(VToolCards tools) {
        this.tools = tools;
    }

    /**
     * set patterns card to be chosen
     * @param patterns
     */
    public void setPatterns(VWindowPatterns patterns) {
        this.patterns = patterns;
    }

    /**
     * set the randomized current dice
     * @param dice
     */
    public void setVCurrentDice(VCurrentDice dice) { this.dice = dice; }

    /**
     * set the round track
     * @param track
     */
    public void setRoundTrack(VRoundTrack track) { this.roundTrack = track; }

    /**
     * set the actual round number
     * @param round
     */
    public void setRound(int round) { this.round = round; }

    /**
     * set the player whose turn it's happening
     * @param player
     */
    public void setTurn(VPlayer player) {
        this.turn = player;
    }

    /**
     * set the player name associated that's actually using that client
     * @param clientPlayer
     */
    public void setClientPlayer(String clientPlayer) { this.clientPlayer = clientPlayer; }

    /**
     * show the sorted ranking at the end of the game
     */
    public void notifyScore() {
        StringBuilder string = new StringBuilder();
        string.append("Classifica partita: ").append(newline);
        players.stream().sorted((p1, p2) -> p1.compareTo(p2)).forEach(p -> string.append(p.getReadyForPodium()));
        println(string.toString());
    }

    /**
     * get all the game elements in text form to be shown in CLI
     * @return
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.
                append("Round: " + this.round).append("\n\n").
                append(this.roundTrack.toString()).append(newline);

        if (this.turn != null && this.clientPlayer != null) {
            if (this.clientPlayer.equals(this.turn.getName()))
                string.append("È il tuo turno, ");
            else string.append("È il turno di ");
            string.append(this.turn.getName()).append(newline);
        }

        for (VPlayer vp : this.players) {
            if (vp != null) {
                string.append(vp.toString());
                if (this.clientPlayer.equals(vp.getName()))
                    if (vp.getvPrivateObjectives() != null)
                        string.append(vp.getvPrivateObjectives().toString());
            }
        }

        for (VPublicObjectiveCard objCard : this.publicObjectives) {
            string.append(objCard.toString());
        }

        string.append(newline).
                append(dice.toString()).append(newline);

        return string.toString();
    }

    /**
     * get all the game elements in graphic to be shown in the GUI
     * @return
     */
    public Group toGUI() {
        VPlayer clientPlayer = null;

        VBox organizer = new VBox();
        HBox playersChain = new HBox();
        HBox objCardsChain = new HBox();
        HBox roundTrack = this.roundTrack.toGUI();
        HBox currentDice = this.dice.toGUI();

        for (VPlayer p : this.players)
            if (this.clientPlayer.equals(p.getName())) {
                clientPlayer = p;
                playersChain.getChildren().add(clientPlayer.toGUI());
                objCardsChain.getChildren().add(clientPlayer.getvPrivateObjectives().toGUI());
            }

        for (VPlayer p : this.players)
            if (p != clientPlayer)
                playersChain.getChildren().add(p.toGUI());

        for (VPublicObjectiveCard card : publicObjectives)
            objCardsChain.getChildren().add(card.toGUI());

        // Add all elements
        organizer.getChildren().addAll(roundTrack , playersChain, objCardsChain, currentDice);

        // Alignment
        playersChain.setAlignment(Pos.CENTER);
        objCardsChain.setAlignment(Pos.CENTER);
        roundTrack.setAlignment(Pos.CENTER);
        currentDice.setAlignment(Pos.CENTER);

        // Paddings and spacings
        organizer.setSpacing(padding);
        playersChain.setSpacing(padding);
        objCardsChain.setSpacing(padding);
        roundTrack.setSpacing(padding);
        currentDice.setSpacing(padding);

        organizer.setPadding(new Insets(thinPadding));
        return new Group(organizer);
    }
}