package it.polimi.ingsw.model;

import it.polimi.ingsw.model.toolCards.*;

import java.io.*;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameConfigurator implements Serializable {
    public static final int DEFAUTL_VALUE = 0;
    public static final String COLUMN="column";
    public static final String LINE="line";
    public static final String LIGHT_SHADES="lightShades";
    public static final String MEDIUM_SHADES="mediumShades";
    public static final String DARK_SHADES="darkShades";

    private Game game;

    private final Dice redDice = new Dice(Color.RED, DEFAUTL_VALUE);
    private final Dice greenDice = new Dice(Color.GREEN, DEFAUTL_VALUE);
    private final Dice purpleDice=new Dice(Color.PURPLE,DEFAUTL_VALUE);
    private final Dice yellowDice=new Dice(Color.YELLOW,DEFAUTL_VALUE);
    private final Dice blueDice=new Dice(Color.BLUE,DEFAUTL_VALUE);
    private final Dice dice1=new Dice(Color.UNCOLORED,1);
    private final Dice dice2=new Dice(Color.UNCOLORED,2);
    private final Dice dice3=new Dice(Color.UNCOLORED,3);
    private final Dice dice4=new Dice(Color.UNCOLORED,4);
    private final Dice dice5=new Dice(Color.UNCOLORED,5);
    private final Dice dice6=new Dice(Color.UNCOLORED,6);


    public GameConfigurator(Game game){
        this.game = game;
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

        }

    }

    /**
     * @param game
     */
    private void createWindowPatternCards(Game game){
        game.addWindowPatternCard(createWPCVirtus(),0);
        game.addWindowPatternCard(createWPCViaLux(),1);
        game.addWindowPatternCard(createWPCauroraeMgnificus(),2);
        game.addWindowPatternCard(createWPCBellesguard(),3);
        game.addWindowPatternCard(createWPCKaleidoscopicDream(),4);
        game.addWindowPatternCard(createWPCsunCatcher(),5);
        game.addWindowPatternCard(createWPCsymphonyOfLight(),6);
        game.addWindowPatternCard(createWPCindustria(),7);
        game.addWindowPatternCard(createWPCbatllo(),8);
        game.addWindowPatternCard(createWPCfirmitas(),9);
        game.addWindowPatternCard(createWPCauroraSagradis(),10);
        game.addWindowPatternCard(createWPCshadowThief(),11);
        game.addWindowPatternCard(createFractalDrops(),12);
        game.addWindowPatternCard(createChromaticSplendor(),13);
        game.addWindowPatternCard(createLuzCelestial(),14);
        game.addWindowPatternCard(createGravitas(),15);
        game.addWindowPatternCard(createLuxAstram(),16);
        game.addWindowPatternCard(createFirelight(),17);
        game.addWindowPatternCard(createRipplesOfLight(),18);
        game.addWindowPatternCard(createComitas(),19);
        game.addWindowPatternCard(createFulgorDelCielo(),20);
        game.addWindowPatternCard(createLuxMundi(),21);
        game.addWindowPatternCard(createSunsGlory(),22);
        game.addWindowPatternCard(createWaterOfLife(),23);
        shuffleWindowPatternCards(game.getWindowPatternCards());
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



    public void shuffleObjective(PublicObjective[] objective) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = objective.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            PublicObjective a;
            a = objective[index];
            objective[index] = objective[i];
            objective[i] = a;
        }
    }

    public void shuffleWindowPatternCards(WindowPattern[] windowPatterns){
        Random rnd = ThreadLocalRandom.current();
        for (int i = windowPatterns.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            WindowPattern a;
            a = windowPatterns[index];
            windowPatterns[index] = windowPatterns[i];
            windowPatterns[i] = a;
        }

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
        Frame frame=new Frame();
        frame.setPositionDice(dice4,0,0);
        frame.setPositionDice(dice2,0,2);
        frame.setPositionDice(dice5,0,3);
        frame.setPositionDice(greenDice,0,4);
        frame.setPositionDice(dice6,1,2);
        frame.setPositionDice(greenDice,1,3);
        frame.setPositionDice(dice2,1,4);
        frame.setPositionDice(dice3,2,1);
        frame.setPositionDice(greenDice,2,2);
        frame.setPositionDice(dice4,2,3);
        frame.setPositionDice(dice5,3,0);
        frame.setPositionDice(greenDice,3,1);
        frame.setPositionDice(dice1,3,2);
        WindowPattern virtus=new WindowPattern(5,"Virtus",frame);
        return virtus;

    }

    public WindowPattern createWPCViaLux(){
        Frame frame=new Frame();
        frame.setPositionDice(yellowDice,0,0);
        frame.setPositionDice(dice6,0,2);
        frame.setPositionDice(dice1,1,1);
        frame.setPositionDice(dice5,1,2);
        frame.setPositionDice(dice2,1,4);
        frame.setPositionDice(dice3,2,0);
        frame.setPositionDice(yellowDice,2,1);
        frame.setPositionDice(redDice,2,2);
        frame.setPositionDice(purpleDice,2,3);
        frame.setPositionDice(dice4,3,2);
        frame.setPositionDice(dice3,3,3);
        frame.setPositionDice(redDice,3,4);
        WindowPattern viaLux =new WindowPattern(4,"ViaLux",frame);
        return viaLux;
    }

    public WindowPattern createWPCBellesguard(){
        Frame frame=new Frame();
        frame.setPositionDice(blueDice,0,0);
        frame.setPositionDice(dice6,0,1);
        frame.setPositionDice(yellowDice,0,4);
        frame.setPositionDice(dice3,1,1);
        frame.setPositionDice(blueDice,1,2);
        frame.setPositionDice(dice5,2,1);
        frame.setPositionDice(dice6,2,2);
        frame.setPositionDice(dice2,2,3);
        frame.setPositionDice(dice4,3,1);
        frame.setPositionDice(dice1,3,3);
        frame.setPositionDice(greenDice,3,4);
        WindowPattern bellesguard=new WindowPattern(3,"Bellesguard",frame);
        return bellesguard;
    }

    public WindowPattern createWPCKaleidoscopicDream(){
        Frame frame=new Frame();
        frame.setPositionDice(yellowDice,0,0);
        frame.setPositionDice(blueDice,0,1);
        frame.setPositionDice(dice1,0,4);
        frame.setPositionDice(greenDice,1,0);
        frame.setPositionDice(dice5,1,2);
        frame.setPositionDice(dice4,1,4);
        frame.setPositionDice(dice3,2,0);
        frame.setPositionDice(redDice,2,2);
        frame.setPositionDice(greenDice,2,4);
        frame.setPositionDice(dice2,3,0);
        frame.setPositionDice(blueDice,3,3);
        frame.setPositionDice(yellowDice,3,4);
        WindowPattern kaleidoscopicDream=new WindowPattern(4,"Kaleidoscopic Dream",frame);
        return kaleidoscopicDream;

    }

    public WindowPattern createWPCauroraeMgnificus(){
        Frame frame=new Frame();
        frame.setPositionDice(dice5,0,0);
        frame.setPositionDice(greenDice,0,1);
        frame.setPositionDice(blueDice,0,2);
        frame.setPositionDice(purpleDice,0,3);
        frame.setPositionDice(dice2,0,4);
        frame.setPositionDice(purpleDice,1,0);
        frame.setPositionDice(yellowDice,1,4);
        frame.setPositionDice(yellowDice,2,0);
        frame.setPositionDice(dice6,2,2);
        frame.setPositionDice(purpleDice,2,4);
        frame.setPositionDice(dice1,3,0);
        frame.setPositionDice(greenDice,3,3);
        frame.setPositionDice(dice4,3,4);
        WindowPattern auroraeMagnificus=new WindowPattern(5,"Aurorae Magnificus",frame);
        return auroraeMagnificus;

    }

    public WindowPattern createWPCsunCatcher(){
        Frame frame=new Frame();
        frame.setPositionDice(blueDice,0,1);
        frame.setPositionDice(dice2,0,2);
        frame.setPositionDice(yellowDice,0,4);
        frame.setPositionDice(dice4,1,1);
        frame.setPositionDice(redDice,1,3);
        frame.setPositionDice(dice5,2,2);
        frame.setPositionDice(yellowDice,2,3);
        frame.setPositionDice(greenDice,3,0);
        frame.setPositionDice(dice3,3,1);
        frame.setPositionDice(purpleDice,3,4);
        WindowPattern sunCatcher=new WindowPattern(3,"Sun Catcher",frame);
        return sunCatcher;

    }

    public WindowPattern createWPCsymphonyOfLight(){
        Frame frame=new Frame();
        frame.setPositionDice(dice3,0,0);
        frame.setPositionDice(dice5,0,2);
        frame.setPositionDice(dice1,0,4);
        frame.setPositionDice(yellowDice,1,0);
        frame.setPositionDice(dice6,1,1);
        frame.setPositionDice(purpleDice,1,2);
        frame.setPositionDice(dice2,1,3);
        frame.setPositionDice(redDice,1,4);
        frame.setPositionDice(blueDice,2,1);
        frame.setPositionDice(dice4,2,2);
        frame.setPositionDice(greenDice,2,3);
        frame.setPositionDice(dice3,3,1);
        frame.setPositionDice(dice5,3,3);
        WindowPattern symphonyOfLight=new WindowPattern(6,"Symphony Of Light",frame);
        return symphonyOfLight;

    }

    public WindowPattern createWPCindustria(){
        Frame frame=new Frame();
        frame.setPositionDice(dice1,0,0);
        frame.setPositionDice(redDice,0,1);
        frame.setPositionDice(dice3,0,2);
        frame.setPositionDice(dice6,0,4);
        frame.setPositionDice(dice5,1,0);
        frame.setPositionDice(dice4,1,1);
        frame.setPositionDice(redDice,1,2);
        frame.setPositionDice(dice2,1,3);
        frame.setPositionDice(dice5,2,2);
        frame.setPositionDice(redDice,2,3);
        frame.setPositionDice(dice1,2,4);
        frame.setPositionDice(dice3,3,3);
        frame.setPositionDice(redDice,3,4);
        WindowPattern industria=new WindowPattern(5,"Industria",frame);
        return industria;
    }

    public WindowPattern createWPCbatllo(){
        Frame frame=new Frame();
        frame.setPositionDice(dice6,0,2);
        frame.setPositionDice(dice5,1,1);
        frame.setPositionDice(blueDice,1,2);
        frame.setPositionDice(dice4,1,3);
        frame.setPositionDice(dice3,2,0);
        frame.setPositionDice(greenDice,2,1);
        frame.setPositionDice(yellowDice,2,2);
        frame.setPositionDice(purpleDice,2,3);
        frame.setPositionDice(dice2,2,4);
        frame.setPositionDice(dice1,3,0);
        frame.setPositionDice(dice4,3,1);
        frame.setPositionDice(redDice,3,2);
        frame.setPositionDice(dice5,3,3);
        frame.setPositionDice(dice3,3,4);
        WindowPattern batllo=new WindowPattern(5,"Batllo",frame);
        return batllo;

    }

    public WindowPattern createWPCfirmitas(){
        Frame frame=new Frame();
        frame.setPositionDice(purpleDice,0,0);
        frame.setPositionDice(dice6,0,1);
        frame.setPositionDice(dice3,0,4);
        frame.setPositionDice(dice5,1,0);
        frame.setPositionDice(purpleDice,1,1);
        frame.setPositionDice(dice3,1,2);
        frame.setPositionDice(dice2,2,1);
        frame.setPositionDice(purpleDice,2,2);
        frame.setPositionDice(dice1,2,3);
        frame.setPositionDice(dice1,3,1);
        frame.setPositionDice(dice5,3,2);
        frame.setPositionDice(purpleDice,3,3);
        frame.setPositionDice(dice4,3,4);
        WindowPattern firmitas=new WindowPattern(5,"Firmitas",frame);
        return firmitas;

    }

    public WindowPattern createWPCauroraSagradis(){
        Frame frame=new Frame();
        frame.setPositionDice(redDice,0,0);
        frame.setPositionDice(blueDice,0,2);
        frame.setPositionDice(yellowDice,0,4);
        frame.setPositionDice(dice4,1,0);
        frame.setPositionDice(purpleDice,1,1);
        frame.setPositionDice(dice3,1,2);
        frame.setPositionDice(greenDice,1,3);
        frame.setPositionDice(dice2,1,4);
        frame.setPositionDice(dice1,2,1);
        frame.setPositionDice(dice5,2,3);
        frame.setPositionDice(dice6,3,2);
        WindowPattern auroraSagradis=new WindowPattern(4,"Aurora Sagradis",frame);
        return auroraSagradis;

    }

    public WindowPattern createWPCshadowThief(){
        Frame frame =new Frame();
        frame.setPositionDice(dice3,0,0);
        frame.setPositionDice(dice4,0,1);
        frame.setPositionDice(dice5,0,2);
        frame.setPositionDice(redDice,0,3);
        frame.setPositionDice(yellowDice,0,4);
        frame.setPositionDice(purpleDice,1,1);
        frame.setPositionDice(dice6,1,3);
        frame.setPositionDice(redDice,1,4);
        frame.setPositionDice(purpleDice,2,2);
        frame.setPositionDice(dice5,2,4);
        frame.setPositionDice(dice5,3,0);
        frame.setPositionDice(purpleDice,3,3);
        frame.setPositionDice(dice6,3,4);
        WindowPattern shadowThief =new WindowPattern(5,"Shadow Thief",frame);
        return shadowThief;
    }

    public WindowPattern createFractalDrops(){
        Frame frame =new Frame();
        frame.setPositionDice(dice4,0,1);
        frame.setPositionDice(yellowDice,0,3);
        frame.setPositionDice(dice6,0,4);
        frame.setPositionDice(redDice,1,0);
        frame.setPositionDice(dice2,1,2);
        frame.setPositionDice(redDice,2,2);
        frame.setPositionDice(purpleDice,2,3);
        frame.setPositionDice(dice1,2,4);
        frame.setPositionDice(blueDice,3,0);
        frame.setPositionDice(yellowDice,3,1);
        WindowPattern fractalDrops =new WindowPattern(3,"Fractal Drops",frame);
        return fractalDrops;

    }

    public WindowPattern createChromaticSplendor(){
        Frame frame =new Frame();
        frame.setPositionDice(greenDice,0,2);
        frame.setPositionDice(dice2,1,0);
        frame.setPositionDice(yellowDice,1,1);
        frame.setPositionDice(dice5,1,2);
        frame.setPositionDice(blueDice,1,3);
        frame.setPositionDice(dice1,1,4);
        frame.setPositionDice(redDice,2,1);
        frame.setPositionDice(dice3,2,2);
        frame.setPositionDice(purpleDice,2,3);
        frame.setPositionDice(dice1,3,0);
        frame.setPositionDice(dice6,3,2);
        frame.setPositionDice(dice4,3,4);
        WindowPattern chromaticSplendor =new WindowPattern(4,"Chromatic Splendor",frame);
        return chromaticSplendor;
    }

    public WindowPattern createLuzCelestial(){
        Frame frame =new Frame();
        frame.setPositionDice(redDice,0,2);
        frame.setPositionDice(dice5,0,3);
        frame.setPositionDice(purpleDice,1,0);
        frame.setPositionDice(dice4,1,1);
        frame.setPositionDice(greenDice,1,3);
        frame.setPositionDice(dice3,1,4);
        frame.setPositionDice(dice6,2,0);
        frame.setPositionDice(blueDice,2,3);
        frame.setPositionDice(yellowDice,3,1);
        frame.setPositionDice(dice2,3,2);
        WindowPattern luzCelestial =new WindowPattern(3,"Luz Celestial",frame);
        return luzCelestial;
    }

    public WindowPattern createGravitas(){
        Frame frame =new Frame();
        frame.setPositionDice(dice1,0,0);
        frame.setPositionDice(dice3,0,2);
        frame.setPositionDice(blueDice,0,3);
        frame.setPositionDice(dice2,1,1);
        frame.setPositionDice(blueDice,1,2);
        frame.setPositionDice(dice6,2,0);
        frame.setPositionDice(blueDice,2,1);
        frame.setPositionDice(dice4,2,3);
        frame.setPositionDice(blueDice,3,0);
        frame.setPositionDice(dice5,3,1);
        frame.setPositionDice(dice2,3,2);
        frame.setPositionDice(dice1,3,4);
        WindowPattern gravitas =new WindowPattern(5,"Gravitas",frame);
        return gravitas;
    }

    public WindowPattern createLuxAstram(){
        Frame frame =new Frame();
        frame.setPositionDice(dice1,0,1);
        frame.setPositionDice(greenDice,0,2);
        frame.setPositionDice(purpleDice,0,3);
        frame.setPositionDice(dice4,0,4);
        frame.setPositionDice(dice6,1,0);
        frame.setPositionDice(purpleDice,1,1);
        frame.setPositionDice(dice2,1,2);
        frame.setPositionDice(dice5,1,3);
        frame.setPositionDice(greenDice,1,4);
        frame.setPositionDice(dice1,2,0);
        frame.setPositionDice(greenDice,2,1);
        frame.setPositionDice(dice5,2,2);
        frame.setPositionDice(dice3,2,3);
        frame.setPositionDice(purpleDice,2,4);
        WindowPattern luxAstram =new WindowPattern(5,"Lux Astram",frame);
        return luxAstram;
    }

    public WindowPattern createFirelight(){
        Frame frame =new Frame();
        frame.setPositionDice(dice3,0,0);
        frame.setPositionDice(dice4,0,1);
        frame.setPositionDice(dice1,0,2);
        frame.setPositionDice(dice5,0,3);
        frame.setPositionDice(dice6,1,1);
        frame.setPositionDice(dice2,1,2);
        frame.setPositionDice(yellowDice,1,4);
        frame.setPositionDice(yellowDice,2,3);
        frame.setPositionDice(redDice,2,4);
        frame.setPositionDice(dice5,3,0);
        frame.setPositionDice(yellowDice,3,3);
        frame.setPositionDice(redDice,3,3);
        frame.setPositionDice(dice6,3,4);
        WindowPattern fireLight =new WindowPattern(5,"Firelight",frame);
        return fireLight;
    }

    public WindowPattern createRipplesOfLight(){
        Frame frame =new Frame();
        frame.setPositionDice(redDice,0,3);
        frame.setPositionDice(dice5,0,4);
        frame.setPositionDice(purpleDice,1,2);
        frame.setPositionDice(dice4,1,3);
        frame.setPositionDice(blueDice,1,4);
        frame.setPositionDice(blueDice,2,1);
        frame.setPositionDice(dice3,2,2);
        frame.setPositionDice(yellowDice,2,3);
        frame.setPositionDice(dice6,2,4);
        frame.setPositionDice(yellowDice,3,0);
        frame.setPositionDice(dice2,3,1);
        frame.setPositionDice(greenDice,3,2);
        frame.setPositionDice(dice1,3,3);
        frame.setPositionDice(redDice,3,4);
        WindowPattern ripplesOfLight =new WindowPattern(5,"Ripples of Light",frame);
        return ripplesOfLight;
    }

    public WindowPattern createComitas(){
        Frame frame =new Frame();
        frame.setPositionDice(yellowDice,0,0);
        frame.setPositionDice(dice2,0,2);
        frame.setPositionDice(dice6,0,4);
        frame.setPositionDice(dice4,1,1);
        frame.setPositionDice(dice5,1,3);
        frame.setPositionDice(yellowDice,1,4);
        frame.setPositionDice(yellowDice,2,3);
        frame.setPositionDice(dice5,2,4);
        frame.setPositionDice(dice1,3,0);
        frame.setPositionDice(dice2,3,1);
        frame.setPositionDice(yellowDice,3,2);
        frame.setPositionDice(dice3,3,3);
        WindowPattern comitas =new WindowPattern(5,"Comitas",frame);
        return comitas;
    }

    public WindowPattern createFulgorDelCielo(){
        Frame frame =new Frame();
        frame.setPositionDice(blueDice,0,1);
        frame.setPositionDice(redDice,0,2);
        frame.setPositionDice(dice4,1,1);
        frame.setPositionDice(dice5,1,2);
        frame.setPositionDice(blueDice,1,4);
        frame.setPositionDice(blueDice,2,0);
        frame.setPositionDice(dice2,2,1);
        frame.setPositionDice(redDice,2,3);
        frame.setPositionDice(dice5,2,4);
        frame.setPositionDice(dice6,3,0);
        frame.setPositionDice(redDice,3,1);
        frame.setPositionDice(dice3,3,2);
        frame.setPositionDice(dice1,3,3);
        WindowPattern fulgorDelCielo =new WindowPattern(5,"Fulgor del Cielo",frame);
        return fulgorDelCielo;
    }

    public WindowPattern createWaterOfLife(){
        Frame frame =new Frame();
        frame.setPositionDice(dice6,0,0);
        frame.setPositionDice(blueDice,0,1);
        frame.setPositionDice(dice1,0,4);
        frame.setPositionDice(dice5,1,1);
        frame.setPositionDice(blueDice,1,2);
        frame.setPositionDice(dice4,2,0);
        frame.setPositionDice(redDice,2,1);
        frame.setPositionDice(dice2,2,2);
        frame.setPositionDice(blueDice,2,3);
        frame.setPositionDice(greenDice,3,0);
        frame.setPositionDice(dice6,3,1);
        frame.setPositionDice(yellowDice,3,2);
        frame.setPositionDice(dice3,3,3);
        frame.setPositionDice(purpleDice,3,4);
        WindowPattern waterOfLife =new WindowPattern(6,"Water of Life",frame);
        return waterOfLife;
    }

    public WindowPattern createLuxMundi(){
        Frame frame =new Frame();
        frame.setPositionDice(dice1,0,2);
        frame.setPositionDice(dice1,1,0);
        frame.setPositionDice(greenDice,1,1);
        frame.setPositionDice(dice3,1,2);
        frame.setPositionDice(blueDice,1,3);
        frame.setPositionDice(dice2,1,4);
        frame.setPositionDice(blueDice,2,0);
        frame.setPositionDice(dice5,2,1);
        frame.setPositionDice(dice4,2,2);
        frame.setPositionDice(dice6,2,3);
        frame.setPositionDice(greenDice,2,4);
        frame.setPositionDice(blueDice,3,1);
        frame.setPositionDice(dice5,3,2);
        frame.setPositionDice(greenDice,3,3);
        WindowPattern luxMundi =new WindowPattern(6,"Lux Mundi",frame);
        return luxMundi;

    }

    public WindowPattern createSunsGlory(){
        Frame frame =new Frame();
        frame.setPositionDice(dice1,0,0);
        frame.setPositionDice(purpleDice,0,1);
        frame.setPositionDice(yellowDice,0,2);
        frame.setPositionDice(dice4,0,4);
        frame.setPositionDice(purpleDice,1,0);
        frame.setPositionDice(yellowDice,1,1);
        frame.setPositionDice(dice6,1,4);
        frame.setPositionDice(yellowDice,2,0);
        frame.setPositionDice(dice5,2,3);
        frame.setPositionDice(dice3,2,4);
        frame.setPositionDice(dice5,3,1);
        frame.setPositionDice(dice4,3,2);
        frame.setPositionDice(dice2,3,3);
        frame.setPositionDice(dice1,3,4);
        WindowPattern sunsGlory =new WindowPattern(6,"Suns's Glory",frame);
        return sunsGlory;
    }










}
