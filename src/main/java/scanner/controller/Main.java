package scanner.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage STAGE;

    public static void main(String[] args) {
        launch(args);
    }

    public static void setFileTitle(String fileTitle){
        STAGE.setTitle("Ipsis literis (Compilador 2021/01) - "+fileTitle);
    }

    public static void resetFileTile(){ setFileTitle("Arquivo não salvo"); }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainScene.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 700, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        STAGE = stage;
        resetFileTile();

        Image image = new Image(getClass().getResource("/icons/icon.png").toExternalForm());

        stage.getIcons().add(image);

        stage.setScene(scene);
        stage.show();
    }
}
