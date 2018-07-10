package it.polimi.ingsw.view;

import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.game_elements.VGame;
import it.polimi.ingsw.view.game_elements.VWindowPatterns;
import it.polimi.ingsw.view.other_elements.VConnectionStatus;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import it.polimi.ingsw.view.other_elements.VError;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class GUI extends Application implements View  {
    private String clientPlayer;
    private VGame game;
    private Label message;
    private Group gameGUI;
    private VBox table;
    private Scene scene;
    private Stage stage;
    private boolean flagFX;
    private int lastPressed;

    private final static int NONE = -1, DICE = 1, TOOL = 2, PASS = 3;

    private HBox buttons;
    private Button buttonDice, buttonTool, buttonPass;

    public GUI() {

    }

    public GUI(String clientPlayer) {
        this.clientPlayer = clientPlayer;
        this.game = new VGame();
        this.game.setClientPlayer(this.clientPlayer);
        this.flagFX = false;
        this.lastPressed = NONE;

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

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        this.message = new Label();
        this.message.setFont(Font.font(null, FontWeight.BOLD, 20));

        this.gameGUI = new Group();
        this.table = new VBox();
        this.stage.setTitle("Sagrada - " + this.clientPlayer);

        this.buttonDice = new Button("Piazza un dado");
        this.buttonTool = new Button("Usa una Carta Utensile");
        this.buttonPass = new Button("Passa il turno");

        setButtonListeners();

        this.buttons = new HBox();
        this.buttons.setSpacing(padding);
        this.buttons.setAlignment(Pos.CENTER);
        this.buttons.getChildren().addAll(this.buttonDice, this.buttonTool, this.buttonPass);

    }

    private void setButtonListeners() {
        this.buttonDice.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                setButton(DICE);
            }
        });

        this.buttonTool.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                setButton(TOOL);
            }
        });

        this.buttonPass.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                setButton(PASS);
            }
        });
    }

    synchronized private void setMessage(String message) {
        Platform.runLater(() -> {
            this.message.setText(message);
            setTrue();
        });
    }

    synchronized private void waitFX() {
        while (!flagFX) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
    }

    public int askDice() {
        this.flagFX = false;
        setMessage("Clicca sul dado da piazzare.");
        waitFX();

        return diceChooserListener();
    }

    private int diceChooserListener() {
        return this.game.diceChooserListener();
    }


    public VCoordinates askCoordinates() {
        this.flagFX = false;
        setMessage("Clicca sul punto in cui vuoi piazzarlo sulla tua vetrata.");
        waitFX();

        return coordinatesChooserListener();
    }

    private VCoordinates coordinatesChooserListener() {
        return this.game.coordinatesChooserListener();
    }

    public VCoordinates askCoordinates(int i) {
        this.flagFX = false;
        setMessage("Clicca sul punto in cui vuoi piazzare sulla tua vetrata il dado " + i + ".");

        waitFX();

        return coordinatesChooserListener();
    }


    public int askMove() {
        this.lastPressed = NONE;
        this.flagFX = false;
        setMessage("Clicca sul bottone corrispondente alla mossa che vuoi fare.");
        waitFX();
        waitButton();
        return this.lastPressed;
    }

    synchronized private void waitButton() {
        while (this.lastPressed == NONE) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
    }

    public int askWindowPattern(VWindowPatterns wpCards) {
        this.flagFX = false;
        synchronized(this) {
            Platform.runLater(() -> {
                this.table = new VBox();
                this.table.getChildren().add(wpCards.toGUI());
                this.stage.setScene(new Scene(this.table));
                setTrue();
            });
        }

        waitFX();

        return wpChooserListener(wpCards);
    }

    synchronized private void setTrue() {
        this.flagFX = true;
        notify();
    }

    synchronized private void setButton(int button) {
        this.lastPressed = button;
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
        return this.game.askToolCardGUI(this.stage);
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
        this.flagFX = false;
        setMessage("Il giocatore " + userName + " si è " + status);
        waitFX();
    }

    @Override
    public void notifyGreetings() {
        this.flagFX = false;
        setMessage("Benvenuto!");
        waitFX();
    }

    public void notifyError(VError error) {
        this.flagFX = false;
        setMessage(error.toString());
        waitFX();
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
