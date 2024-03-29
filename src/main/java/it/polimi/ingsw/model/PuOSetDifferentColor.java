package it.polimi.ingsw.model;




public class PuOSetDifferentColor extends PublicObjective implements iObjective{


    public PuOSetDifferentColor(String name,int points) {
        super(name,points);

    }

    /**
     * calculate Player's score
     * @param player
     * @return
     */
    @Override
    public int calculateScore(Player player) {
        Frame fakeFrame= player.getFrame();
        int size=5;
        int[] countColoredDice=new int[size];
        Dice[] fakeArrayDice=new Dice[size];
        for(int i=0;i<fakeArrayDice.length;i++)
            fakeArrayDice[i]=new Dice();
        fakeArrayDice[0].setColor(Color.RED);
        fakeArrayDice[1].setColor(Color.GREEN);
        fakeArrayDice[2].setColor(Color.PURPLE);
        fakeArrayDice[3].setColor(Color.BLUE);
        fakeArrayDice[4].setColor(Color.YELLOW);
        int minVal=18;
        for(int i=0;i<size;i++){
            countColoredDice[i]=fakeFrame.getNumberOfDice(fakeArrayDice[i]);
            if (countColoredDice[i]<minVal)
                minVal=countColoredDice[i];
        }
        return (this.getPoints()*minVal);
    }
}
