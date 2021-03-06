package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = new File("src/main/java/view/Books.fxml").toURL();
        Parent root= FXMLLoader.load(url);
        primaryStage.setScene(new Scene(root,680,510));
        primaryStage.setTitle("");
        primaryStage.show();

    }
}
