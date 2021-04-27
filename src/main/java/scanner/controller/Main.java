package scanner.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainScene.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 700, 700);

        stage.setTitle("Compilador 2021/01 - Ipsis literis");
        stage.setScene(scene);
        stage.show();
    }
}
