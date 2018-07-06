package it.polimi.ingsw.model;

import com.google.gson.Gson;
import it.polimi.ingsw.model.toolCards.*;

import java.io.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static it.polimi.ingsw.inputoutput.IOManager.fileToString;
import static it.polimi.ingsw.inputoutput.IOManager.json_windowPattern_path;

public class GameConfigurator implements Serializable {
    public static final int DEFAUTL_VALUE = 0;
    public static final String COLUMN="column";
    public static final String LINE="line";
    public static final String LIGHT_SHADES="lightShades";
    public static final String MEDIUM_SHADES="mediumShades";
    public static final String DARK_SHADES="darkShades";

    private Game game;
    private WindowPattern[] fakeWindowPatterns;
    private int numberColor;


    public GameConfigurator(Game game){

        this.game = game;
        this.fakeWindowPatterns=new WindowPattern[24];
        game.configureGame();
        createWindowPatternCards(game);
        createPublicObjective();
        createToolCards();
        PrivateObjective[] privateObjective=new PrivateObjective[4];
        privateObjective=createPrivateObjective();
        for(int i=0;i<game.getTurnOrder().length;i++){
            game.getTurnOrder()[i].setWindowPattern(game.getWindoePatternCard(i*4));
            game.getTurnOrder()[i].setFavorTokens(game.getWindoePatternCard(i*4).getFavorTokenToAssign());
            game.getTurnOrder()[i].setCurrentGame(game);
            game.getTurnOrder()[i].setPrivateObjective(privateObjective[i]);
            setPlayerColor(game.getTurnOrder()[i]);

        }

    }

    /**
     * @param game
     */
   private void createWindowPatternCards(Game game){
       Gson gson=new Gson();
       game.setWindowPatternCards(gson.fromJson(fileToString(json_windowPattern_path),WindowPattern[].class));
       for(int i=0;i<fakeWindowPatterns.length;i++)
           fakeWindowPatterns[i]=game.getWindowPatternCards()[i];
        shuffleObjective(game.getWindowPatternCards());
    }


    private void createPublicObjective(){
        PublicObjective[] allPublicObjective=new PublicObjective[10];
        allPublicObjective[0]=new PuODifferentColor("Different Color-Column",COLUMN,5);
        allPublicObjective[1]=new PuODifferentColor("Different Color-Line",LINE,6);
        allPublicObjective[2]=new PuODifferentShades("Different Shades-Column",COLUMN,4);
        allPublicObjective[3]=new PuODifferentShades("Different Shades-Line",LINE,5);
        allPublicObjective[4]=new PuOShades("Light Shades",2,LIGHT_SHADES);
        allPublicObjective[5]=new PuOShades("Medium Shades",2,MEDIUM_SHADES);
        allPublicObjective[6]=new PuOShades("Dark Shades",2,DARK_SHADES);
        allPublicObjective[7]=new PuOSetDifferentShades("Set Different Shades",5);
        allPublicObjective[8]=new PuOSetDifferentColor("Set Different Color",4);
        allPublicObjective[9]=new PuOColoredDiagonals("Colored Diagonals",1);
        shuffleObjective(allPublicObjective);
        PublicObjective[] objectivesExtracted=new PublicObjective[3];
        for(int i=0;i<3;i++)
            objectivesExtracted[i]=allPublicObjective[i];
        game.setPublicObjectives(objectivesExtracted);


    }

    public void shuffleObjective(Object[]objective) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = objective.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            Object a;
            a = objective[index];
            objective[index] = objective[i];
            objective[i] = a;
        }
    }

    public void setPlayerColor(Player player){
       Color[] color=new Color[6];
       shuffleObjective(color);
        int i=0;
        for(Color c: Color.values()) {
            color[i] = c;
            i++;
        }
       player.setColor(color[numberColor]);
        numberColor++;
    }

    public PrivateObjective[] createPrivateObjective(){
        PrivateObjective[] privateObjective=new PrivateObjective[6];//UNCOLORED
        int i=0;
        for(Color c: Color.values()) {
            privateObjective[i] = new PrivateObjective("Shades", c);
            i++;
        }
        return privateObjective;

    }

    public void createToolCards(){
        ToolCard[] toolCard= new ToolCard[12];
        toolCard[0]=new TcGrozingPliers(1,"Grozing Pliers",Color.PURPLE);
        toolCard[1]=new TcEglomiseBrush(2,"Eglomise Brush",Color.BLUE);
        toolCard[2]=new TcCopperFoilBurnisher(3,"Copper Foil Burnisher",Color.RED);
        toolCard[3]=new TcLathekin(4,"Lathekin",Color.YELLOW);
        toolCard[4]=new TcLensCutter(5,"Lens Cutter",Color.GREEN);
        toolCard[5]=new TcFluxBrush(6,"Flux Brush",Color.PURPLE);
        toolCard[6]=new TcGlazingHammer(7,"Glazing Hammer",Color.BLUE);
        toolCard[7]=new TcRunningPliers(8,"Running Pliers",Color.RED);
        toolCard[8]=new TcCorkBackedStraightedge(9,"Cork Backed Straightedge",Color.YELLOW);
        toolCard[9]=new TcGrindingStone(10,"Grinding Stone",Color.GREEN);
        toolCard[10]=new TcFluxRemover(11,"Flux Remover",Color.PURPLE);
        toolCard[11]=new TcTapWheel(12,"Tap Wheel",Color.BLUE);
        game.setToolCards(toolCard);
        ToolCard[] toolCards2= new ToolCard[12];
        for(int i=0;i<toolCard.length;i++)
            toolCards2[i]=toolCard[i];
        shuffleObjective(toolCards2);
        ToolCard[] toolcard3= new ToolCard[3];

        for(int i=0;i<toolcard3.length;i++)
            toolcard3[i]=toolCards2[i];
        game.setGameToolCards(toolcard3);

    }



    public WindowPattern createWPCVirtus(){

        return fakeWindowPatterns[0];

    }

    public WindowPattern createWPCViaLux(){

        return fakeWindowPatterns[1];
    }

    public WindowPattern createWPCBellesguard(){

        return fakeWindowPatterns[2];
    }

    public WindowPattern createWPCKaleidoscopicDream(){

        return fakeWindowPatterns[3];

    }

    public WindowPattern createWPCauroraeMgnificus(){

        return fakeWindowPatterns[4];

    }

    public WindowPattern createWPCsunCatcher(){

        return fakeWindowPatterns[5];

    }

    public WindowPattern createWPCsymphonyOfLight(){

        return fakeWindowPatterns[6];

    }

    public WindowPattern createWPCindustria(){

        return fakeWindowPatterns[7];
    }

    public WindowPattern createWPCbatllo(){

        return fakeWindowPatterns[8];

    }

    public WindowPattern createWPCfirmitas(){

        return fakeWindowPatterns[9];

    }

    public WindowPattern createWPCauroraSagradis(){

        return fakeWindowPatterns[10];

    }

    public WindowPattern createWPCshadowThief(){

        return fakeWindowPatterns[11];
    }

    public WindowPattern createFractalDrops(){

        return fakeWindowPatterns[12];

    }

    public WindowPattern createChromaticSplendor(){

        return fakeWindowPatterns[13];
    }

    public WindowPattern createLuzCelestial(){

        return fakeWindowPatterns[14];
    }

    public WindowPattern createGravitas(){

        return fakeWindowPatterns[15];
    }

    public WindowPattern createLuxAstram(){

        return fakeWindowPatterns[16];
    }

    public WindowPattern createFirelight(){

        return fakeWindowPatterns[17];
    }

    public WindowPattern createRipplesOfLight(){

        return fakeWindowPatterns[18];
    }

    public WindowPattern createComitas(){

        return fakeWindowPatterns[19];
    }

    public WindowPattern createFulgorDelCielo(){

        return fakeWindowPatterns[20];
    }

    public WindowPattern createWaterOfLife(){

        return fakeWindowPatterns[21];
    }

    public WindowPattern createLuxMundi(){

        return fakeWindowPatterns[22];

    }

    public WindowPattern createSunsGlory(){

        return fakeWindowPatterns[23];
    }




}
