package it.polimi.ingsw.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    private ArrayList<Player> players;
    private Dice[] rolledDice;
    private Player[] turnOrder;
    //private ToolCards[] toolCards;
    private PublicObjective[] publicObjectives;
    private Dice[] currentDice;
    private int round;

    public Game() {
        this.players = new ArrayList<Player>();
        this.rolledDice = new Dice[90];
        this.turnOrder = new Player[4];
       // this.toolCards = new ToolCards[12];
        this.publicObjectives = new PublicObjective[3];
        this.currentDice = new Dice[9];
        this.round=1;
        setRolledDice();
        shuffleRolledDice();
        setCurrentDice();
    }

    public void setAddPlayer(Player player){
        players.add(player);
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Dice[] getRolledDice() {
        return rolledDice;
    }

    public void setRolledDice(Dice[] rolledDice) {
        this.rolledDice = rolledDice;
    }

    public Player[] getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(Player[] turnOrder) {
        this.turnOrder = turnOrder;
    }

   /* public ToolCards[] getToolCards() {
        return toolCards;
    }

    public void setToolCards(ToolCards[] toolCards) {
        this.toolCards = toolCards;
    }
    */
    public PublicObjective[] getPublicObjectives() {
        return publicObjectives;
    }

    public void setPublicObjectives(PublicObjective[] publicObjectives) {
        this.publicObjectives = publicObjectives;
    }

    public Dice[] getRemainingDice() {
        return currentDice;
    }

    public void setRemainingDice(Dice[] remainingDice) {
        this.currentDice = remainingDice;
    }

    public Dice[] getCurrentDice() {
        return currentDice;
    }

    public void setCurrentDice(Dice[] currentDice) {
        this.currentDice = currentDice;
    }
    public void setCurrentDice(){
        for(int i=0;i<currentDice.length;i++)
            currentDice[i]=new Dice();
    }

    public void setRolledDice(){
        Dice dice=new Dice();
        Random random=new Random();
        for(int i=0;i<rolledDice.length;i++)
            rolledDice[i]=new Dice();
        int j=1;
        for(int i=0;i< rolledDice.length;i++){
            rolledDice[i].setValue(random.nextInt(5)+1);
            j++;
        }
        for(int x=0;x<18;x++)
            rolledDice[x].setColor(Color.green);
        for(int y=18;y<36;y++)
            rolledDice[y].setColor(Color.red);
        for(int z=36;z<54;z++)
            rolledDice[z].setColor(Color.pink);
        for(int a=54;a<72;a++)
            rolledDice[a].setColor(Color.yellow);
        for(int w=72;w<90;w++)
            rolledDice[w].setColor(Color.blue);
    }


    public void shuffleRolledDice() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = rolledDice.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Dice a=new Dice();
            a = rolledDice[index];
            rolledDice[index] = rolledDice[i];
            rolledDice[i] = a;
        }
    }

    public void setNewRolledDice(int round){
        int numberOfPlayers=players.size();
        int k=((numberOfPlayers*2)+1);
        int j=0;
        for(int i=(k*(round-1)); i<(k*round);i++){
            currentDice[j]=rolledDice[i];
            j++;
        }


    }
    public void nextRound(){
        round++;
        setNewRolledDice(round);
    }



    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", rolledDice=" + Arrays.toString(rolledDice) +
                ", turnOrder=" + Arrays.toString(turnOrder) +
                //", toolCards=" + Arrays.toString(toolCards) +
                ", publicObjectives=" + Arrays.toString(publicObjectives) +
                ", remainingDice=" + Arrays.toString(currentDice) +
                '}';
    }
}
