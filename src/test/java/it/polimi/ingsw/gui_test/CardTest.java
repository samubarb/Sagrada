package it.polimi.ingsw.gui_test;

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
    public void WPcardTest() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        String number = "" + 3;
        ImageView img = getImage(tools_path + "tool" + number + ".jpg");


        Group group = new Group(img);
        Scene scene = new Scene(group);

        primaryStage.setTitle("Image");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
