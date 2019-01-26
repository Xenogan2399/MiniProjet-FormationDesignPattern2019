package Presentation.FXML;

import Presentation.Controlleur.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Gestionaire_Interface G = new Gestionaire_Interface(primaryStage);
        Controller C = (Controller) G.switchPanel("GUI.fxml","Mini-Compilateur");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
