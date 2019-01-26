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

    protected HashMap<HashSet<Boolean>,Boolean> dictionnaire = new HashMap<>();
    protected LinkedList<String> listNomColonnes = new LinkedList<>();
    protected ObservableList<HashSet<Boolean>> o = FXCollections.observableArrayList();

    static double xOffset = 0;
    static double yOffset = 0;

    protected boolean verifSyntaxique(String expression){
        return true;
    }

    protected boolean verifSatisf(){
        return true;
    }

    protected void compiler(String expression){

    }

    protected void remplirTable(TableView<HashSet<Boolean>> Table){
        o.addAll(dictionnaire.keySet());
        Table.setItems(o);
        for (int i = 0; i <listNomColonnes.size(); i++) {

            TableColumn<HashSet<Boolean>,String> T = new TableColumn<>(listNomColonnes.get(i));
            int finalI = i;
            T.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HashSet<Boolean>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<HashSet<Boolean>, String> param) {

                    SimpleStringProperty SS = null;
                    ArrayList<Boolean> A = (ArrayList<Boolean>) param.getValue().clone();
                    SS = new SimpleStringProperty(String.valueOf(A.get(finalI)));
                    return SS;

                }
            });
            Table.getColumns().add(T);

        }

        TableColumn<HashSet<Boolean>,String> valeur = new TableColumn<>("Valeur");
        valeur.setCellValueFactory(cell -> (new SimpleStringProperty(String.valueOf(dictionnaire.get(cell.getValue())))));
        Table.getColumns().add(valeur);
    }

}
