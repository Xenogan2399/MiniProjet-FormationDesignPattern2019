package Presentation.Controlleur;

import Presentation.FXML.Gestionaire_Interface;
import Presentation.FXML.MessageBox;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Controller extends Call implements Initializable {

    @FXML
    private JFXTextField expr;
    @FXML
    private JFXButton compiler;
    @FXML
    private JFXButton executer;
    @FXML
    private TableView<LinkedList<Boolean>> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        expr.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(verifSyntaxique(expr.getText())) {
                    expr.setStyle("-fx-border-color: #96c5b0");
                    compiler.setDisable(false);
                }
                else{
                    expr.setStyle("-fx-border-color: #a71d31");
                    compiler.setDisable(true);
                }
            }
        });
    }

    @FXML
    private void compilation(ActionEvent actionEvent){
        compiler(expr.getText());
        MessageBox a=new MessageBox();
        a.display("Information","Compilation Réussie",1);
        executer.setDisable(false);
    }

    @FXML
    private void execution(ActionEvent actionEvent){
        if(verifSatisf()){
            MessageBox a=new MessageBox();
            a.display("Information","L'expression Est Satisfaisable",2);
            if(MessageBox.genererT){
                remplirTable(table);
                Platform.runLater( () -> Gestionaire_Interface.customResize(table));
            }
        }else {
            MessageBox a=new MessageBox();
            a.display("Information","L'expression Est Non Satisfaisable",1);
        }
    }

    @FXML
    private void fermerFenetre() {
        Stage S = (Stage) compiler.getScene().getWindow();
        S.close();
    }

    @FXML
    private void Iconify() {
        Stage S = (Stage) compiler.getScene().getWindow();
        S.setIconified(true);
    }

}
