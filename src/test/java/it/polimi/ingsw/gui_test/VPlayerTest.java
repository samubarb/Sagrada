package it.polimi.ingsw.gui_test;

import it.polimi.ingsw.controller.Adapter;
import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.game_elements.VPlayer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class VPlayerTest extends Application {

    @Test
    public void WPcardTest() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Adapter ada = new AdapterCLI();
        Game game = new Game();
        game.configureGame();

        Player p1 = new Player("TestPlayer1", Color.RED);
        p1.setWindowPattern(new GameConfigurator(game).createWPCbatllo());
        p1.setFavorTokens(3);
        Frame frame = new Frame();
        frame.setPositionDice(new Dice(Color.PURPLE, 5), 2, 2);
        p1.setFrame(frame);
        VPlayer player = ada.playerToView(p1);


        Group group = player.toGUI();
        Scene scene = new Scene(group);

        primaryStage.setTitle("Player");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}