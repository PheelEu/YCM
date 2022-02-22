package com.ycm.Gui;


import com.ycm.Classes.Boat;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.ycm.Gui.MemberGui.*;
import static com.ycm.Gui.MemberGui.a;

public class MemberRacesGuiController implements Initializable {

    /**
     * UPCOMING RACES OBJECTS
     **/

    @FXML
    private TableView<Race> raceTable;

    @FXML
    private TableColumn<Race, LocalDate> raceDayCol;

    @FXML
    private TableColumn<Race, Double> raceNameCol;

    @FXML
    private TableColumn<Race, String> raceFeeCol;

    @FXML
    static ObservableList<Race> racesObservableList = FXCollections.observableArrayList();

    @FXML
    void selectRaceBtn(ActionEvent event) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        Race race = raceTable.getSelectionModel().getSelectedItem();
        if(race==null){
            a.setContentText("No race has been selected");
            a.showAndWait();
        }
        else {
            try {
                contentPane.getChildren().clear();
                contentPane.getChildren().add((SelectBoatPane()));
                setRace(race);
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            Object boats = new Client().run(new Request(new Message("memberBoats", getMember().getUsername())));
            if (!(boats == null)) {
                memberBoats = (ArrayList<Boat>) boats;
                if (!(MemberSelectBoatGuiController.boatsObservableList.isEmpty())) {
                    MemberSelectBoatGuiController.boatsObservableList.clear();
                }
                for (int i = 0; i < memberBoats.size(); i++) {
                    MemberSelectBoatGuiController.boatsObservableList.add(new Boat(memberBoats.get(i).getName(), memberBoats.get(i).getID(), memberBoats.get(i).getLength()));
                }
            }
        }
        raceTable.getItems().clear();
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
