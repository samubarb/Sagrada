package it.polimi.ingsw.view;

import it.polimi.ingsw.view.cards.VWindowPattern;
import it.polimi.ingsw.view.exceptions.UsernameTooShortException;
import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.game_elements.VGame;
import it.polimi.ingsw.view.game_elements.VWindowPatterns;
import it.polimi.ingsw.view.other_elements.VConnectionStatus;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import it.polimi.ingsw.view.other_elements.VError;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class GUI extends Application implements View  {
    private String clientPlayer;
    private VGame game;
    private Label message;
    private Group gameGUI;
    private VBox table;
    private HBox buttons;
    private Scene scene;
    private Stage stage;
    private boolean flagFX;
    private Button buttonDice;
    private Button buttonTool;
    private Button buttonPass;
    private int pressedButton = 0;

    public GUI(String clientPlayer) {
        this.clientPlayer = clientPlayer;
        this.game = new VGame();
        this.game.setClientPlayer(this.clientPlayer);
        this.flagFX = false;

        new JFXPanel(); // needed to set the correct environment
        Platform.runLater(() -> {
            try {
                this.start(new Stage());
            } catch (Exception e) {
                println("Runtime Error.");
                errorExit();
            }
        });
    }

    public GUI() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.message = new Label();
        this.gameGUI = new Group();
        this.table = new VBox();
        this.buttons = new HBox();
        this.buttonDice = new Button("Piazza un dado");
        this.buttonTool = new Button("Scegli una toolcard");
        this.buttonPass = new Button("Passa il turno");

        this.buttonDice.setOnAction(e -> {
            System.out.println("Button pressed " + ((Button) e.getSource()).getText());
            this.pressedButton = 1;
        });

        this.buttonTool.setOnAction(e -> {
            System.out.println("Button pressed " + ((Button) e.getSource()).getText());
            this.pressedButton = 2;
        });

        this.buttonPass.setOnAction(e -> {
            System.out.println("Button pressed " + ((Button) e.getSource()).getText());
            this.pressedButton = 3;
        });

        this.buttons.getChildren().addAll(this.buttonDice, this.buttonTool, this. buttonPass);
        this.stage.setTitle("Sagrada - " + this.clientPlayer);
    }

    public int askDice() {
        this.flagFX = false;
        synchronized(this) {
            Platform.runLater(() -> {
                this.message.setText("Clicca sul dado da piazzare.");
                setTrue();
            });
        }
        synchronized(this) {
            while (!flagFX) {
                try {
                    wait();
                } catch (InterruptedException e) {

                }
            }
        }

        return diceChooserListener();
    }

    private int diceChooserListener() {
        return this.game.diceChooserListener();
    }


    public VCoordinates askCoordinates() {
        this.flagFX = false;
        synchronized(this) {
            Platform.runLater(() -> {
                this.message.setText("Clicca sul punto in cui vuoi piazzarlo sulla tua vetrata.");
                setTrue();
            });
        }

        synchronized(this) {
            while (!flagFX) {
                try {
                    wait();
                } catch (InterruptedException e) {

                }
            }
        }

        return coordinatesChooserListener();
    }

    private VCoordinates coordinatesChooserListener() {
        return this.game.coordinatesChooserListener(this.clientPlayer);
    }

    public VCoordinates askCoordinates(int i) {
        this.flagFX = false;
        synchronized(this) {
            Platform.runLater(() -> {
                this.message.setText("Clicca sul punto in cui vuoi piazzarlo sulla tua vetrata.");
                setTrue();
            });
        }

        synchronized(this) {
            while (!flagFX) {
                try {
                    wait();
                } catch (InterruptedException e) {

                }
            }
        }

        return coordinatesChooserListener();
    }

    public int askMove() {
        return 1;
    }

    public int askWindowPattern(VWindowPatterns wpCards) {
        this.flagFX = false;
        Platform.runLater(() -> {
            this.table = new VBox();
            this.table.getChildren().add(wpCards.toGUI());
            this.stage.setScene(new Scene (this.table));
            setTrue();
        });

        while(!this.flagFX) {
            println(this.flagFX);
        }

        //this.flagFX = false;
        return wpChooserListener(wpCards);
    }

    synchronized private void setTrue() {
        this.flagFX = true;
        notify();
    }

    private int wpChooserListener(VWindowPatterns wpCards) {
        while(true) {
            for (int i = 0; i < wpCards.getPatterns().length; i++) {
                if (wpCards.getPatterns()[i].gotClicked()) {
                    return i;
                }
            }
        }
    }

    public int askToolCard() {
        println("here i am 9");
        return 0;
    }


    public boolean askAction() {
        println("here i am 11");
        return false;
    }

    public int askToPickFromRoundTrack() {
        println("here i am 10");
        return 0;
    }



    public int askDiceNumber(VDice dice) {
        println("here i am 12");
        return 0;
    }

    @Override
    public void notifyConnectionStatus(String userName, VConnectionStatus status) {
        Platform.runLater(() -> {
            Label msg = new Label("Il giocatore " + userName + " si è " + status);
            this.table.getChildren().add(msg);
        });
    }

    @Override
    public void notifyGreetings() {
        Platform.runLater(() -> {
            this.message.setText("Benvenuto!");
        });
    }

    public void notifyError(VError error) {
        println("here i am 15");
    }

    public void notifyScore() {
        println("here i am 16");
    }

    public void notifyWin() {
        println("here i am 17");
    }

    public void notifyLose() {
        println("here i am 18");
    }

    public void updateState(VGame game) {
        Platform.runLater(() -> {
            game.setClientPlayer(this.clientPlayer);
            this.game = game;
            this.table = new VBox();
            this.gameGUI = this.game.toGUI();
            this.table.getChildren().addAll(this.gameGUI, this.buttons, this.message);
            this.stage.setScene(new Scene(this.table));
        });
    }

    public void splash() {
        Platform.runLater(() -> {
            ImageView splash = getImage(splash_path);
            splash.setFitWidth(splash_width);
            this.table.getChildren().addAll(splash, this.message);
            this.stage.setScene(new Scene(this.table));
            this.stage.show();
        });
    }


}
