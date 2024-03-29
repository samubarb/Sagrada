package it.polimi.ingsw.gui_test;

import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameConfigurator;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.toolCards.ToolCard;
import it.polimi.ingsw.view.cards.VToolCard;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

/*
public class CardTest extends Application {
    public void CardImportTest() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        game.setAddPlayer(new Player("TestPlayer"));
        new GameConfigurator(game).createToolCards();
        ToolCard[]  tools = game.getToolCards();
        VToolCard vtool = new AdapterCLI().toolCardToView(tools[0]);

        ImageView card = vtool.toGUI();


        Group group = new Group(card);
        Scene scene = new Scene(group);

        primaryStage.setTitle("Card");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
*/