package it.polimi.ingsw.gui_test;

import it.polimi.ingsw.controller.RMIApi.Adapter;
import it.polimi.ingsw.controller.Server.AdapterCLI;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.cards.VWindowPattern;
import it.polimi.ingsw.view.game_elements.VDice;
import it.polimi.ingsw.view.game_elements.VFrame;
import it.polimi.ingsw.view.other_elements.VColor;
import it.polimi.ingsw.view.other_elements.VCoordinates;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class VFrameTest extends Application {

    @Test
    public void VFrameTest() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        GameConfigurator gconf = new GameConfigurator(new Game());
        WindowPattern wp = gconf.createWPCsunCatcher();
        Adapter adapt = new AdapterCLI();
        VWindowPattern vWP = adapt.patternToView(wp);

        VFrame frame = new VFrame();
        frame.setDice(new VDice(5, VColor.BLUE), new VCoordinates(2, 2));
        frame.setDice(new VDice(1, VColor.YELLOW), new VCoordinates(4, 3));


        GridPane frameGUI = frame.buildGrid(vWP);

        Group group = new Group(frameGUI);
        Scene scene = new Scene(group);

        primaryStage.setTitle("WPcard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
