package Presentation.FXML;

import Presentation.Controlleur.Call;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class Gestionaire_Interface {

    private Stage PS;
    static double xOffset = 0;
    static double yOffset = 0;

    public Gestionaire_Interface(Stage ps) {
        PS = ps;
        if(!PS.isShowing())
            PS.initStyle(StageStyle.TRANSPARENT);
    }

    public Call switchPanel(String fichier, String titre) throws IOException {
        FXMLLoader loader = new FXMLLoader ( );
        loader.setLocation ( Main.class.getResource ( fichier ) );
        Parent root = loader.load ( );
        Call FOP = loader.getController ( );
        PS.setTitle ( titre );
        moveStage(PS,root);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        PS.setResizable(true);
        PS.setScene(scene);
        PS.show ( );
        return FOP;
    }


    public static void moveStage(Stage PS,Parent root){

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                xOffset = event.getSceneX();
                yOffset = event.getSceneY();

            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PS.setX(event.getScreenX() - xOffset);
                PS.setY(event.getScreenY() - yOffset);
            }
        });
    }

    public static void  customResize(TableView<?> view) {

        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth() + ((tableWidth - width.get()) / (view.getColumns().size())));
            });
        }
    }
}
