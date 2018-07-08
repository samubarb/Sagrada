package it.polimi.ingsw.model;


public class PuODifferentShades extends PublicObjective implements iObjective {

    private String flagColumnLine;
    public static final int LINE_SIZE= 4;
    public static final int COLUMN_SIZE = 5;

    //flagColumnLine 0--->line 1--->column

    public PuODifferentShades(String name, String flagColumnLine, int points) {
        super(name,points);
        this.flagColumnLine = flagColumnLine;

    }

    public String getFlagColumnLine() {
        return flagColumnLine;
    }

    public void setFlagColumnLine(String flagColumnLine) {
        this.flagColumnLine = flagColumnLine;
    }

    @Override
    public String toString() {
        return "PuODifferentShades{" +
                "flagColumnLine=" + flagColumnLine +
                ", points=" + getPoints() +
                '}';
    }

    /**
     * calculate Player's score
     * @param player
     * @return
     */
    @Override
    public int calculateScore(Player player) {
        Frame fakeFrame= player.getFrame();
        boolean allDifferent=true;
        int score=0;
        if (flagColumnLine.equals("line")){
            for(int i=0;i<LINE_SIZE;i++){
                for(int j=0;j<COLUMN_SIZE;j++){
                    for(int x=j+1;x<COLUMN_SIZE&& allDifferent;x++){
                        if(fakeFrame.getDice(i,j).getValue()==fakeFrame.getDice(i,x).getValue())
                            allDifferent=false; }
                }

                if(allDifferent)
                    score+=this.getPoints();
                else allDifferent=true;
            }
        }
        if (flagColumnLine.equals("column")){
            for(int i=0;i<COLUMN_SIZE;i++) {
                for (int j = 0; j < LINE_SIZE; j++) {
                    for (int x = j + 1; x < 4 && allDifferent; x++) {
                        if (fakeFrame.getDice(j, i).getValue() == fakeFrame.getDice(x, i).getValue())
                            allDifferent = false;
                    }
                }
                if (allDifferent)
                    score += this.getPoints();
                else allDifferent = true;
            }
        }
        return score;
    }
}
