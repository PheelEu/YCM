package com.ycm.Gui;

import com.ycm.Classes.Race;
import com.ycm.Sockets.Client;
import com.ycm.Sockets.Message;
import com.ycm.Sockets.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.ycm.Gui.EmployeeGui.a;
import static com.ycm.Gui.EmployeeGui.viewRaces;

public class EmployeeViewRGuiController implements Initializable {

    @FXML
    private TableColumn<Race, LocalDate> raceDayCol;

    @FXML
    private TableColumn<Race, String> raceFeeCol;

    @FXML
    private TableColumn<Race, Double> raceNameCol;

    @FXML
    private TableView<Race> raceTable;

    @FXML
    static ObservableList<Race> racesObservableList = FXCollections.observableArrayList();

    @FXML
    void deleteRaceBtn(ActionEvent event) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        Race race = raceTable.getSelectionModel().getSelectedItem();
        if(race==null){
            a.setContentText("No race has been selected");
            a.showAndWait();
        }
        else {
            Object removeRace = new Client().run(new Request(new Message("removeRace", race.getName())));
            if((boolean) removeRace){
                a.setContentText("Race deleted");
            }
            else {
                a.setContentText("Race not deleted");
            }
            a.showAndWait();
        }
        Object races = new Client().run(new Request(new Message( "upcomingRaces")));
        if(!(races==null)){
            viewRaces = (ArrayList<Race>) races;
            if(!(racesObservableList.isEmpty())){
                racesObservableList.clear();
            }
            for(int i=0; i<viewRaces.size(); i++){
                racesObservableList.add(new Race(viewRaces.get(i).getName(), viewRaces.get(i).getCost(), viewRaces.get(i).getRaceDay()));
            }
        }
    }

    /**
     * This is the initialize method from the @class javafx.fxml.Initializable
     * @param location is the URL location
     * @param resources are the ResourceBundle resources
     * Here is used to set the products table, the values inside each column, the brand box brands and to get the products info
     **/
    @Override
    public void initialize(final URL location, final ResourceBundle resources){
        raceFeeCol.setCellValueFactory(new PropertyValueFactory<Race, String>("name"));
        raceNameCol.setCellValueFactory(new PropertyValueFactory<Race, Double>("cost"));
        raceDayCol.setCellValueFactory(new PropertyValueFactory<Race, LocalDate>("raceDay"));
        raceTable.setItems(racesObservableList);
    }
}

