package it.polimi.ingsw.view;

import it.polimi.ingsw.view.exceptions.UsernameTooShortException;
import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.game_elements.VGame;
import it.polimi.ingsw.view.game_elements.VWindowPatterns;
import it.polimi.ingsw.view.other_elements.VConnectionStatus;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import it.polimi.ingsw.view.other_elements.VError;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static it.polimi.ingsw.inputoutput.IOManager.println;

public class GUI extends Application implements View  {

    private String clientPlayer;
    private VGame game;
    private Label message;
    private Group gameGUI;
    private VBox table;

    public GUI(String clientPlayer) {
        this.message = new Label();
        this.clientPlayer = clientPlayer;
        this.game = new VGame();
        this.game.setClientPlayer(this.clientPlayer);
        this.gameGUI = new Group();
        this.table = new VBox();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(this.table);
        primaryStage.setTitle("Sagrada");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public int askDice() {
        return 0;
    }

    public int askDice(int i) {
        return 0;
    }

    public VCoordinates askCoordinates() {
        return null;
    }

    public VCoordinates askCoordinates(int i) {
        return null;
    }

    public int askMove() {
        return 0;
    }

    public int askWindowPattern(VWindowPatterns wpCards) {
        this.table.getChildren().add(wpCards.toGUI());
        println("here i am");
        launch();
        return 1;
    }

    public int askWindowPattern() {
        return 0;
    }

    public int askToolCard() {
        return 0;
    }

    public boolean askAction() {
        return false;
    }

    public int askToPickFromRoundTrack() {
        return 0;
    }

    public boolean askConfirmDice(VDice dice) {
        return false;
    }

    public int askDiceNumber(VDice dice) {
        return 0;
    }

    @Override
    public void notifyConnectionStatus(String userName, VConnectionStatus status) {

    }

    @Override
    public void notifyGreetings() {
        this.message = new Label("Benvenuto!");
    }

    public void notifyError(VError error) {

    }

    public void notifyScore() {

    }

    public void notifyWin() {

    }

    public void notifyLose() {

    }

    public void updateState(VGame game) {
        game.setClientPlayer(this.clientPlayer);
        this.game = game;
        show();
    }

    private void show() {
        this.gameGUI = this.game.toGUI();
        try {
            launch();
        } catch (IllegalStateException e) {

        }
    }

    public void notifyMessage(String message) {
        this.message.setText(message);
    }

    public void splash() {

    }

    public String askNewUsername() throws UsernameTooShortException {
        return null;
    }

    public String chooseAnotherUsername(String user) throws UsernameTooShortException {
        return null;
    }


}
