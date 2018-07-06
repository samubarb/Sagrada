package it.polimi.ingsw.model;

import it.polimi.ingsw.model.toolCards.ToolCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements Serializable{


    private ArrayList<Player> players;
    private Dice[] rolledDice;
    private Player[] turnOrder;
    private ToolCard[] toolCards;
    private ToolCard[] gameToolCards;
    private PublicObjective[] publicObjectives;
    private Dice[] currentDice;
    private Dice[] roundTrack;
    private int round;
    private int currentPlayerIndex;
    private Player actualPlayer;
    private Stack<Player> turnStack;
    private WindowPattern[] windowPatternCards;
    private int  counnterForWindowPattern;
    private boolean diceRolled;
    private Player[] initialTurnOrder;
    private boolean isSecondTurn;

    public Game() {
        this.players = new ArrayList<Player>();
        this.rolledDice = new Dice[90];
        this.turnOrder = null;
        this.toolCards = new ToolCard[12];
        this.gameToolCards=new ToolCard[3];
        this.publicObjectives = new PublicObjective[3];
        this.currentDice = null;
        this.round=0;
        this.roundTrack= new Dice[10];
        this.currentPlayerIndex=0;
        this.turnStack=new Stack<Player>();
        this.windowPatternCards=new WindowPattern[24];
        this.counnterForWindowPattern=0;
        this.diceRolled=false;
        this.initialTurnOrder=null;
        this.actualPlayer=null;

        setDefaultDice(roundTrack);
        setRolledDice();
        shuffleRolledDice();
        //setCurrentDice();
    }

    /**
     * adds the player in the player array list
     * @param player
     */
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

    /**
     * @return array all rolled dice
     */
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

    public ToolCard[] getToolCards() {
        return toolCards;
    }

    public void setToolCards(ToolCard[] toolCards) {
        this.toolCards = toolCards;
    }

    public PublicObjective[] getPublicObjectives() {
        return publicObjectives;
    }

    public void setPublicObjectives(PublicObjective[] publicObjectives) {
        this.publicObjectives = publicObjectives;
    }

    public boolean isDiceRolled() {
        return diceRolled;
    }

    public void setDiceRolled(boolean diceRolled) {
        this.diceRolled = diceRolled;
    }

    /*public Dice[] getRemainingDice() {
        return currentDice;
    }

    public void setRemainingDice(Dice[] remainingDice) {
        this.currentDice = remainingDice;
    }*/

    public Dice[] getCurrentDice() {
        return currentDice;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public Dice[] getRoundTrack() {
        return roundTrack;
    }

    public void setRoundTrack(Dice[] roundTrack) {
        this.roundTrack = roundTrack;
    }

    public void setRoundTrack(Dice dice, int position){
        this.roundTrack[position]=dice;

    }

    public void setCurrentDice(Dice[] currentDice) {
        this.currentDice = currentDice;
    }

    public Stack<Player> getTurnStack() {
        return turnStack;
    }

    public void setTurnStack(Stack<Player> turnStack) {
        this.turnStack = turnStack;
    }

    public WindowPattern[] getWindowPatternCards() {
        return windowPatternCards;
    }
    public WindowPattern getWindoePatternCard(int position){
        return windowPatternCards[position];
    }

    public void setWindowPatternCards(WindowPattern[] windowPatternCards) {
        this.windowPatternCards = windowPatternCards;
    }
    public void addWindowPatternCard(WindowPattern card,int position){
        this.windowPatternCards[position]=card;
    }

    public boolean isSecondTurn() {
        return isSecondTurn;
    }

    public void setSecondTurn(boolean secondTurn) {
        isSecondTurn = secondTurn;
    }

    public Player getActualPlayer() {
        return actualPlayer;
    }

    public void setActualPlayer(Player actualPlayer) {
        this.actualPlayer = actualPlayer;
    }

    public ToolCard[] getGameToolCards() {
        return gameToolCards;
    }

    public void setGameToolCards(ToolCard[] gameToolCards) {
        this.gameToolCards = gameToolCards;
    }

    /**
     * set the current dice with a default dice
     */
    public void setCurrentDice(){
        for(int i=0;i<currentDice.length;i++)
            currentDice[i]=new Dice();
    }

    public void setCurrentDice(Dice dice,int position){
        this.currentDice[position]=dice;
    }
    public Dice getDiceFromCurrentDice(int position){
        Dice diceToReturn=currentDice[position];
        currentDice[position]=new Dice();
        return diceToReturn;

    }

    /**
     * check if in the roundTrack there is a die of the same color of dice
     * @param dice
     * @return
     */
    public boolean checkColorDice(Dice dice){
        for(Dice d : roundTrack)
            if(dice.getColor().equals(d.getColor()))
                return true;
        return false;
    }

    public void setDefaultDice(Dice[] arrayDice){
        for(int i=0;i<arrayDice.length;i++){
            arrayDice[i]=new Dice();
        }
    }

    public void configureGame(){
        int numberOfPlayer=players.size();
        this.turnOrder = new Player[numberOfPlayer];
        this.initialTurnOrder=new Player[numberOfPlayer];
        for(int i=0; i<turnOrder.length;i++){
            turnOrder[i]=players.get(i);
            initialTurnOrder[i]=players.get(i);
        }
        this.currentDice = new Dice[(numberOfPlayer*2)+1];
        setCurrentDice();
        //setNewRolledDice(round);

    }

    public void randomDice(Dice dice){
        Random random=new Random();
        dice.setValue(random.nextInt(6)+1);

    }


    public void randomDice(Dice[] dice){
        Random random=new Random();
        for(int i=0;i< dice.length;i++)
            dice[i].setValue(random.nextInt(6)+1);


    }

    public void setRolledDice(){

        for(int i=0;i<rolledDice.length;i++)
            rolledDice[i]=new Dice();
        randomDice(rolledDice);

        for(int x=0;x<18;x++)
            rolledDice[x].setColor(Color.RED);
        for(int y=18;y<36;y++)
            rolledDice[y].setColor(Color.BLUE);
        for(int z=36;z<54;z++)
            rolledDice[z].setColor(Color.GREEN);
        for(int a=54;a<72;a++)
            rolledDice[a].setColor(Color.YELLOW);
        for(int w=72;w<90;w++)
            rolledDice[w].setColor(Color.PURPLE);
       // setDiceRolled(true);
    }


    public void shuffleRolledDice() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = rolledDice.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Dice a;
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
        Dice lastDice=new Dice();
        if(round!=0) {
            for (int i = 0; i < currentDice.length; i++)
                if (currentDice[i].getColor() != Color.UNCOLORED)
                    lastDice = currentDice[i];

            roundTrack[round - 1] = lastDice;
        }
        round++;
        setNewRolledDice(round);
        setTurnOrder();
        currentPlayerIndex=0;
    }

    public void setTurnOrder(){
        Player[] newTurnOrder=new Player[players.size()];
        for(int i=0;i<turnOrder.length;i++){
            newTurnOrder[i]=initialTurnOrder[((i+round-1)%turnOrder.length)];
        }
        turnOrder=newTurnOrder;

    }

    public Player getCurrentPlayer(){


        Player currentPlayer=new Player();

        if (turnStack.empty()){
            nextRound();
            isSecondTurn=false;
        }
        if(this.currentPlayerIndex>=turnOrder.length&&!turnStack.empty()){
            currentPlayer=turnStack.pop();
            isSecondTurn=true;
            setCurrentPlayerIndex(this.currentPlayerIndex+1);
        }
        if(this.currentPlayerIndex<turnOrder.length){
            turnStack.push(turnOrder[currentPlayerIndex]);
            currentPlayer=turnOrder[currentPlayerIndex];
            setCurrentPlayerIndex(this.currentPlayerIndex+1);
        }

       // if (turnStack.empty())
               // nextRound();

        actualPlayer=currentPlayer;
        return currentPlayer;
    }

    public Player getPlayerByUsername(String nome){
        for(Player player:turnOrder)
            if(player.getName().equals(nome))
                return player;
        return new Player();
    }

    /**
     * resets the die in the current dice array
     * @param player
     * @param position
     */
    public void restoreDice(Player player,int position){
        Dice dice=player.getChosenNut();
        player.setChosenNut(new Dice());
        getCurrentDice()[position]=dice;

    }

    /**
     * @return
     */
    public WindowPattern[] getWindowPatternForAPlayer(){
        WindowPattern[] arrayForPlayer=new WindowPattern[4];
        for(int i=0;i<arrayForPlayer.length;i++){
            arrayForPlayer[i]=windowPatternCards[i+counnterForWindowPattern];
        }
        counnterForWindowPattern+=4;
        return arrayForPlayer;


    }





    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", rolledDice=" + Arrays.toString(rolledDice) +
                ", turnOrder=" + Arrays.toString(turnOrder) +
                ", toolCards=" + Arrays.toString(toolCards) +
                ", publicObjectives=" + Arrays.toString(publicObjectives) +
                ", remainingDice=" + Arrays.toString(currentDice) +
                '}';
    }
}
