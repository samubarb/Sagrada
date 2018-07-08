package it.polimi.ingsw.gui_test;

import it.polimi.ingsw.controller.RMIApi.Adapter;
import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameConfigurator;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.view.cards.VWindowPattern;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
public class WPcardTest extends Application {
    public void WPcardTest() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        game.setAddPlayer(new Player("TestPlayer"));
        GameConfigurator gconf = new GameConfigurator(game);
        WindowPattern wp = gconf.createWPCsunCatcher();
        Adapter adapt = new AdapterCLI();
        VWindowPattern vWP = adapt.patternToView(wp);
        VBox wpGUI = vWP.toGUI();

        Scene scene = new Scene(wpGUI);

        primaryStage.setTitle("WPcard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
*/