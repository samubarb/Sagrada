package it.polimi.ingsw.model;

public class GameConfigurator {
    public static final int DEFAUTL_VALUE = 0;

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
    }

    public void createWindowPatternCards(){
        createWPCViaLux();
        createWPCVirtus();
        createWPCauroraeMgnificus();
        createWPCBellesguard();
        createWPCKaleidoscopicDream();
        createWPCsunCatcher();
    }


    public void createWPCVirtus(){
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
        game.addWindowPatternCard(virtus,0);

    }

    public void createWPCViaLux(){
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
        game.addWindowPatternCard(viaLux,1);
    }

    public void createWPCBellesguard(){
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
        game.addWindowPatternCard(bellesguard,2);
    }

    public void createWPCKaleidoscopicDream(){
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
        game.addWindowPatternCard(kaleidoscopicDream,3);

    }

    public void createWPCauroraeMgnificus(){
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
        game.addWindowPatternCard(auroraeMagnificus,4);

    }

    public void createWPCsunCatcher(){
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
        game.addWindowPatternCard(sunCatcher,5);

    }




}
