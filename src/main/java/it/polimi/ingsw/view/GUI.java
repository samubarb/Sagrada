package it.polimi.ingsw.view;

import it.polimi.ingsw.view.exceptions.UsernameTooShortException;
import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.game_elements.VGame;
import it.polimi.ingsw.view.game_elements.VPlayer;
import it.polimi.ingsw.view.game_elements.VWindowPatterns;
import it.polimi.ingsw.view.other_elements.VConnectionStatus;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import it.polimi.ingsw.view.other_elements.VError;
import it.polimi.ingsw.view.other_elements.VSettings;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application implements View  {

    private String clientPlayer;
    private VGame game;
    private static VSettings settings;
    private VSettings tempSettings;

    public GUI(String clientPlayer) {
        this.clientPlayer = clientPlayer;
        this.settings = new VSettings(); // reset settings to the default
        this.game = new VGame();
        this.game.setClientPlayer(clientPlayer);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

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
        return 0;
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
