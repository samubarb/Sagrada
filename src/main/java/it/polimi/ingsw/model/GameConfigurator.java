package it.polimi.ingsw.model;

import java.io.Serializable;

public class GameConfigurator implements Serializable {
    public static final int DEFAUTL_VALUE = 0;
    public static final String COLUMN="column";
    public static final String LINE="line";

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


    public GameConfigurator(Game game) {
        this.game = game;
        game.configureGame();
        createWindowPatternCards(game);
        for(int i=0;i<game.getTurnOrder().length;i++){
            game.getTurnOrder()[i].setWindowPattern(/*createWPCfirmitas()*/game.getWindoePatternCard(i));
            game.getTurnOrder()[i].setCurrentGame(game);
        }

    }

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
    }


    private void createPublicObjective(){
        PublicObjective[] allPublicObjective=new PublicObjective[10];
        allPublicObjective[0]=new PuODifferentColor("Different Color-Column",COLUMN,5);



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
        WindowPattern kaleidoscopicDream=new WindowPattern(4,"KaleidoscopicDream",frame);
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
        WindowPattern auroraeMagnificus=new WindowPattern(5,"AuroraeMagnificus",frame);
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
        WindowPattern sunCatcher=new WindowPattern(3,"SunCatcher",frame);
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
        WindowPattern symphonyOfLight=new WindowPattern(6,"SymphonyOfLight",frame);
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
        WindowPattern auroraSagradis=new WindowPattern(4,"AuroraSagradis",frame);
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
        WindowPattern shadowThief =new WindowPattern(5,"ShadowThief",frame);
        return shadowThief;
    }





}
