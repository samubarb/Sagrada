package it.polimi.ingsw.model;


public class PuOColoredDiagonals extends PublicObjective implements iObjective {

    public static final int LINE_SIZE = 4;
    public static final int COLUMN_SIZE = 5;
    private ColorFrame[][] colorFrame;


    public PuOColoredDiagonals(String name, int points) {
        super(name, points);
        this.colorFrame = new ColorFrame[LINE_SIZE][COLUMN_SIZE];
        fillColorFrame();
    }

    /**
     * fill ColorFrame
     */
    public void fillColorFrame() {
        for (int i = 0; i < LINE_SIZE; i++) {
            for (int j = 0; j < COLUMN_SIZE; j++) {
                this.colorFrame[i][j] = new ColorFrame();
            }
        }
    }

    /**
     * copy frame in ColorFrame
     * @param player
     */
    public void copyColorFrame(Player player) {
        for (int i = 0; i < LINE_SIZE; i++) {
            for (int j = 0; j < COLUMN_SIZE; j++) {
                this.colorFrame[i][j].setColor(player.getFrame().getDice(i, j).getColor());
            }
        }
    }

    /**
     *
     * check diagonals of the same color
     * @param x
     * @param y
     * @return
     */
    public int checkDiagonal(int x, int y){
        int copyX=x;
        int copyY=y;
        int score=0;
        if(this.colorFrame[x][y].getColor().equals(this.colorFrame[copyX-1][copyY-1].getColor())&&!this.colorFrame[x][y].getColor().equals(Color.UNCOLORED)){
            if(this.colorFrame[x][y].isAlreadyUsed())
                if(!this.colorFrame[copyX-1][copyY-1].isAlreadyUsed()){
                    score+=1;
                    this.colorFrame[copyX-1][copyY-1].setAlreadyUsed(true);}
            if(!this.colorFrame[x][y].isAlreadyUsed())
                if(!this.colorFrame[copyX-1][copyY-1].isAlreadyUsed()){
                    score+=2;
                    this.colorFrame[x][y].setAlreadyUsed(true);
                    this.colorFrame[copyX-1][copyY-1].setAlreadyUsed(true);}
                else {
                    score += 1;
                    this.colorFrame[x][y].setAlreadyUsed(true);}
        }

        if(this.colorFrame[x][y].getColor().equals(this.colorFrame[copyX-1][copyY+1].getColor())&&!this.colorFrame[x][y].getColor().equals(Color.UNCOLORED)){
            if(this.colorFrame[x][y].isAlreadyUsed())
                if(!this.colorFrame[copyX-1][copyY+1].isAlreadyUsed()){
                    score+=1;
                    this.colorFrame[copyX-1][copyY+1].setAlreadyUsed(true);}
            if(!this.colorFrame[x][y].isAlreadyUsed())
                if(!this.colorFrame[copyX-1][copyY+1].isAlreadyUsed()){
                    score+=2;
                    this.colorFrame[x][y].setAlreadyUsed(true);
                    this.colorFrame[copyX-1][copyY+1].setAlreadyUsed(true);}
                else {
                    score += 1;
                    this.colorFrame[x][y].setAlreadyUsed(true);}
        }

        if(this.colorFrame[x][y].getColor().equals(this.colorFrame[copyX+1][copyY-1].getColor())&&!this.colorFrame[x][y].getColor().equals(Color.UNCOLORED)){
            if(this.colorFrame[x][y].isAlreadyUsed())
                if(!this.colorFrame[copyX+1][copyY-1].isAlreadyUsed()){
                    score+=1;
                    this.colorFrame[copyX+1][copyY-1].setAlreadyUsed(true);}
            if(!this.colorFrame[x][y].isAlreadyUsed())
                if(!this.colorFrame[copyX+1][copyY-1].isAlreadyUsed()){
                    score+=2;
                    this.colorFrame[x][y].setAlreadyUsed(true);
                    this.colorFrame[copyX+1][copyY-1].setAlreadyUsed(true);}
                else {
                    score += 1;
                    this.colorFrame[x][y].setAlreadyUsed(true);}
        }

        if(this.colorFrame[x][y].getColor().equals(this.colorFrame[copyX+1][copyY+1].getColor())&&!this.colorFrame[x][y].getColor().equals(Color.UNCOLORED)){
            if(this.colorFrame[x][y].isAlreadyUsed())
                if(!this.colorFrame[copyX+1][copyY+1].isAlreadyUsed()){
                    score+=1;
                    this.colorFrame[copyX+1][copyY+1].setAlreadyUsed(true);}
            if(!this.colorFrame[x][y].isAlreadyUsed())
                if(!this.colorFrame[copyX+1][copyY+1].isAlreadyUsed()){
                    score+=2;
                    this.colorFrame[x][y].setAlreadyUsed(true);
                    this.colorFrame[copyX+1][copyY+1].setAlreadyUsed(true);}
                else {
                    score += 1;
                    this.colorFrame[x][y].setAlreadyUsed(true);}
        }
        return score;
    }

    /**
     * check lateral diagonals of the same color
     * @return
     */
    public int checkLateralDiagonal(){
        int x=0;
        int y=1;
        int score2=0;
        if(this.colorFrame[y][x].getColor().equals(this.colorFrame[y-1][x+1].getColor())&&!this.colorFrame[y][x].getColor().equals(Color.UNCOLORED)){
            if(this.colorFrame[y][x].isAlreadyUsed())
                if(!this.colorFrame[y-1][x+1].isAlreadyUsed())
                    score2+=1;

            if(!this.colorFrame[y][x].isAlreadyUsed())
                if(!this.colorFrame[y-1][x+1].isAlreadyUsed())
                    score2+=2;

                else
                    score2 +=1;

        }
        y=2;
        if(this.colorFrame[y][x].getColor().equals(this.colorFrame[x+1][y+1].getColor())&&!this.colorFrame[y][x].getColor().equals(Color.UNCOLORED)) {
            if (this.colorFrame[y][x].isAlreadyUsed())
                if (!this.colorFrame[x + 1][y + 1].isAlreadyUsed())
                    score2 += 1;

            if (!this.colorFrame[y][x].isAlreadyUsed())
                if (!this.colorFrame[x + 1][y + 1].isAlreadyUsed())
                    score2+= 2;

                else
                    score2 += 1;
        }
        x=4;
        if(this.colorFrame[y][x].getColor().equals(this.colorFrame[y+1][x-1].getColor())&&!this.colorFrame[y][x].getColor().equals(Color.UNCOLORED)){
            if(this.colorFrame[y][x].isAlreadyUsed())
                if(!this.colorFrame[y+1][x-1].isAlreadyUsed())
                    score2+=1;

            if(!this.colorFrame[y][x].isAlreadyUsed())
                if(!this.colorFrame[y+1][x-1].isAlreadyUsed())
                    score2+=2;

                else
                    score2 +=1;
        }
        y=1;
        if(this.colorFrame[y][x].getColor().equals(this.colorFrame[x-1][y-1].getColor())&&!this.colorFrame[y][x].getColor().equals(Color.UNCOLORED)) {
            if (this.colorFrame[y][x].isAlreadyUsed())
                if (!this.colorFrame[x - 1][y - 1].isAlreadyUsed())
                    score2 += 1;

            if (!this.colorFrame[y][x].isAlreadyUsed())
                if (!this.colorFrame[x - 1][y - 1].isAlreadyUsed())
                    score2 += 2;

                else
                    score2 += 1;
        }
        return score2;

    }

    /**
     * calculate Player's score
     * @param player
     * @return
     */
    @Override
    public int calculateScore(Player player) {
        int finalScore=0;
        copyColorFrame(player);
        for (int i = 1; i < LINE_SIZE - 1; i++) {
            for (int j = 1; j < COLUMN_SIZE - 1; j++) {
                finalScore+=checkDiagonal(i,j);
            }
        }
        finalScore+=checkLateralDiagonal();
        return finalScore;
    }
}