package it.polimi.ingsw.gui_test;

import it.polimi.ingsw.controller.Adapter;
import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameConfigurator;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.view.cards.VWindowPattern;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class WPcardTest extends Application {

    private Stage window;

    @Test
    public void WPcardTest() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        this.window.setTitle("Window Patter Card");

        GameConfigurator gconf = new GameConfigurator(new Game());
        WindowPattern wp = gconf.createWPCsunCatcher();
        Adapter adapt = new AdapterCLI();
        VWindowPattern vWP = adapt.patternToView(wp);
        GridPane wpGUI = vWP.toGUI();
        Scene scene = new Scene(wpGUI, 600,500);

        this.window.setScene(scene);
        this.window.show();
    }
}