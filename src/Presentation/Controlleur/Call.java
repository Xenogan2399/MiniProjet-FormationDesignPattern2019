package Presentation.Controlleur;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Call {

    protected HashMap<LinkedList<Boolean>,Boolean> dictionnaire = new HashMap<>();
    protected LinkedList<String> listNomColonnes = new LinkedList<>();
    protected ObservableList<LinkedList<Boolean>> o = FXCollections.observableArrayList();

    protected boolean verifSyntaxique(String expression){
        return true;
    }

    protected boolean verifSatisf(){
        return false;
    }

    protected void compiler(String expression){

    }

    protected void remplirTable(TableView<LinkedList<Boolean>> Table){
        listNomColonnes.add("A");
        listNomColonnes.add("B");
        LinkedList<Boolean> H = new LinkedList<>();

        H.add(true);
        H.add(true);
        dictionnaire.put(H,false);
        o.addAll(dictionnaire.keySet());
        Table.setItems(o);
        for (int i = 0; i <listNomColonnes.size(); i++) {
            TableColumn<LinkedList<Boolean>,String> T = new TableColumn<>(listNomColonnes.get(i));
            int finalI = i;
            T.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().get(finalI)+""));
            Table.getColumns().add(T);
        }

        TableColumn<LinkedList<Boolean>,String> valeur = new TableColumn<>("Valeur");
        valeur.setCellValueFactory(cell -> (new SimpleStringProperty(String.valueOf(dictionnaire.get(cell.getValue())))));
        Table.getColumns().add(valeur);
    }

}
