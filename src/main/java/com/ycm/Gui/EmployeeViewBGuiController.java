package com.ycm.Gui;

import com.ycm.Classes.Boat;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.ycm.Gui.EmployeeGui.a;
import static com.ycm.Gui.EmployeeGui.*;

public class EmployeeViewBGuiController implements Initializable{

    @FXML
    private TableColumn<Boat, Integer> boatIDCol;

    @FXML
    private TableColumn<Boat, Double> boatLengthCol;

    @FXML
    private TableColumn<Boat, String> boatNameCol;

    @FXML
    private TableColumn<Boat, Double> boatStorageCol;

    @FXML
    private TableColumn<Boat, String> ownerCol;

    @FXML
    private TableView<Boat> boatTable;

    @FXML
    private static ObservableList<Boat> boatsObservableList = FXCollections.observableArrayList();

    /**
     * This method it's called by a GUI event, when the 'remove boat' button gets pressed
     * Enables the Employee who clicked it remove a boat from the club
     *
     * @param event it's the triggered event
     **/
    @FXML
    void removeBoatBtn(ActionEvent event) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        Boat boat = boatTable.getSelectionModel().getSelectedItem();
        if(boat==null){
            a.setContentText("No boat has been selected");
            a.showAndWait();
        }
        else {
            Object removeBoat = new Client().run(new Request(new Message("removeBoat", String.valueOf(boat.getID()))));
            if ((boolean) removeBoat) {
                a.setContentText("Boat removed correctly");
                Object removeNotification = new Client().run(new Request(new Message("removeBoatNotification", String.valueOf(boat.getID()))));
                Object boats = new Client().run(new Request(new Message("boats")));
                if (!(boats == null)) {
                    mBoats = (ArrayList<Boat>) boats;
                    if (!(boatsObservableList.isEmpty())) {
                        boatsObservableList.clear();
                    }
                    for (int i = 0; i < mBoats.size(); i++) {
                        boatsObservableList.add(new Boat(mBoats.get(i).getName(), mBoats.get(i).getID(), mBoats.get(i).getLength()));
                    }
                }
                if(!(boolean) removeNotification){
                    a.setContentText("Boat notifications not removed! \nAn Error Occurred.");
                }

            } else {
                a.setContentText("Boat not removed! \nAn Error Occurred.");
            }
            a.showAndWait();
        }
    }

    /**
     * This is the initialize method from the @class javafx.fxml.Initialize
     * @param location is the URL location
     * @param resources are the ResourceBundle resources
     * Here is used to set the boat table and the values inside each column for each boat
     **/
    @Override
    public void initialize(final URL location, final ResourceBundle resources){
        Object boats = new Client().run(new Request(new Message("boats")));
        if (!(boats == null)) {
            mBoats = (ArrayList<Boat>) boats;
            if (!(boatsObservableList.isEmpty())) {
                boatsObservableList.clear();
            }
            for (int i = 0; i < mBoats.size(); i++) {
                boatsObservableList.add(new Boat(mBoats.get(i).getID(), mBoats.get(i).getName(), mBoats.get(i).getLength(),
                        mBoats.get(i).getOwner()));
            }
        }
        boatNameCol.setCellValueFactory(new PropertyValueFactory<Boat, String>("name"));
        boatIDCol.setCellValueFactory(new PropertyValueFactory<Boat, Integer>("ID"));
        boatLengthCol.setCellValueFactory(new PropertyValueFactory<Boat, Double>("length"));
        boatStorageCol.setCellValueFactory(new PropertyValueFactory<Boat, Double>("boatStorage"));
        ownerCol.setCellValueFactory(new PropertyValueFactory<Boat, String>("owner"));
        boatTable.setItems(boatsObservableList);
    }
}

