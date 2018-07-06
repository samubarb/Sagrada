package it.polimi.ingsw.gui_test;

import it.polimi.ingsw.controller.Adapter;
import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameConfigurator;
import it.polimi.ingsw.model.toolCards.ToolCard;
import it.polimi.ingsw.view.cards.VToolCard;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.getImage;
import static it.polimi.ingsw.inputoutput.IOManager.tools_path;

public class CardTest extends Application {
    @Test
    public void CardImportTest() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        new GameConfigurator(game).createToolCards();
        ToolCard[]  tools = game.getToolCards();
        VToolCard vtool = new AdapterCLI().toolCardToView(tools[5]);

        ImageView card = vtool.toGUI();


        Group group = new Group(card);
        Scene scene = new Scene(group);

        primaryStage.setTitle("Card");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
