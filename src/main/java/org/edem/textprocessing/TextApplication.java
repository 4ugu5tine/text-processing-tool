package org.edem.textprocessing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.edem.textprocessing.utils.Utils;

import java.io.IOException;

public class TextApplication extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("layout"), 640,800);
        stage.setTitle("Text Processing Tool");
        stage.setScene(scene);
        stage.show();
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TextApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    static void setRoot(String fxml) throws IOException{
        scene.setRoot(loadFXML(fxml));
    }

    public static void main(String[] args) {
        launch();
        Utils.search("", "");
    }
}