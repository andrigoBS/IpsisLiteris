package scanner.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private static Stage STAGE;
    private static Class<?> CLASS;

    public static void main(String[] args) {
        launch(args);
    }

    public static void setFileTitle(String fileTitle){
        setTitle(STAGE, fileTitle);
    }

    public static void resetFileTile(){ setFileTitle("Arquivo não salvo"); }

    public static void newWindow(Parent root, String title){
        Stage secondStage = new Stage();
        configWindow(secondStage, root,450, 450);
        setTitle(secondStage, title);
        secondStage.setX(STAGE.getX() + 150);
        secondStage.setY(STAGE.getY() + 150);
    }

    private static void setTitle(Stage stage, String title){
        stage.setTitle("Ipsis literis (Compilador 2021/01) - "+title);
    }

    private static void configWindow(Stage stage, Parent root, int width, int height){
        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(CLASS.getResource("/styles/main.css").toExternalForm());

        Image image = new Image(CLASS.getResource("/icons/icon.png").toExternalForm());
        stage.getIcons().add(image);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        AbstractController.stopAll();
    }

    @Override
    public void start(Stage stage) throws Exception {
        STAGE = stage;
        CLASS = getClass();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainScene.fxml"));

        resetFileTile();
        configWindow(stage, fxmlLoader.load(),750,700);
    }
}
