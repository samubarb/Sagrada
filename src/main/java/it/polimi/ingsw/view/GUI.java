package it.polimi.ingsw.view;

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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class GUI extends Application implements View  {
    private String clientPlayer;
    private VGame game;
    private Label message;
    private Group gameGUI;
    private VBox table;
    private Stage stage;

    public GUI(String clientPlayer) {
        this.clientPlayer = clientPlayer;
        this.game = new VGame();
        this.game.setClientPlayer(this.clientPlayer);

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

        Scene scene = new Scene(this.table);
        this.stage.setTitle("Sagrada");
        this.stage.setScene(scene);
        this.stage.show();
    }

    public int askDice() {
        println("here i am 3");
        return 0;
    }

    public int askDice(int i) {
        println("here i am 4");
        return 0;
    }

    public VCoordinates askCoordinates() {
        println("here i am 5");
        return null;
    }

    public VCoordinates askCoordinates(int i) {
        println("here i am 6");
        return null;
    }

    public int askMove() {
        println("here i am 7");
        return 0;
    }

    public int askWindowPattern(VWindowPatterns wpCards) {
        Platform.runLater(() -> {
            this.table.getChildren().add(wpCards.toGUI());
            this.stage.setScene(new Scene (this.table));
            this.stage.show();
        });

        return wpChooserListener();
    }

    private int wpChooserListener() {


        return 1;
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
        println("here i am 7");
        game.setClientPlayer(this.clientPlayer);
        this.game = game;
    }

    public void notifyMessage(String message) {
        println("here i am 20");
        this.message.setText(message);
    }


    public void splash() {
        Platform.runLater(() -> {
            ImageView splash = getImage(splash_path);
            splash.setFitWidth(splash_width);
            this.table.getChildren().addAll(splash, this.message);
        });
    }


    public String askNewUsername() throws UsernameTooShortException {
        println("here i am 22");
        return null;
    }

    public String chooseAnotherUsername(String user) throws UsernameTooShortException {
        println("here i am 23");
        return null;
    }


}
