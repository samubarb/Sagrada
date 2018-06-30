package it.polimi.ingsw.gui_test;

import it.polimi.ingsw.controller.Adapter;
import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameConfigurator;
import it.polimi.ingsw.model.WindowPattern;
import it.polimi.ingsw.view.cards.VWindowPattern;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.inputoutput.IOManager.*;

public class WPcardTest extends Application {

    @Test
    public void WPcardTest() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        GameConfigurator gconf = new GameConfigurator(new Game());
        WindowPattern wp = gconf.createWPCsunCatcher();
        Adapter adapt = new AdapterCLI();
        VWindowPattern vWP = adapt.patternToView(wp);
        Group wpGUI = vWP.toGUI();

        Scene scene = new Scene(wpGUI);

        primaryStage.setTitle("WPcard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}