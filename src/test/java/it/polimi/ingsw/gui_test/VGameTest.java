package it.polimi.ingsw.gui_test;

import it.polimi.ingsw.controller.Adapter;
import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.game_elements.VGame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class VGameTest extends Application {
    @Test
    public void VGameTest() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Adapter ada = new AdapterCLI();
        Game game = new Game();

        game.configureGame();

        Player p1 = new Player("TestPlayer1", Color.RED);
        Player p2 = new Player("TestPlayer2", Color.BLUE);
        Player p3 = new Player("TestPlayer3", Color.GREEN);
        Player p4 = new Player("TestPlayer4", Color.PURPLE);

        p1.setWindowPattern(new GameConfigurator(game).createWPCbatllo());
        p2.setWindowPattern(new GameConfigurator(game).createWPCViaLux());
        p3.setWindowPattern(new GameConfigurator(game).createWPCKaleidoscopicDream());
        p4.setWindowPattern(new GameConfigurator(game).createWPCindustria());

        p1.setFrame(new Frame());
        p2.setFrame(new Frame());
        p3.setFrame(new Frame());
        p4.setFrame(new Frame());

        game.setAddPlayer(p1);
        game.setAddPlayer(p2);
        game.setAddPlayer(p3);
        game.setAddPlayer(p4);

        GameConfigurator gconf = new GameConfigurator(game);

        VGame vgame = ada.gameToView(game);

        Scene scene = new Scene(vgame.toGUI());

        primaryStage.setTitle("Player");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
