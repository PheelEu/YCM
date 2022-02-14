package com.ycm.Gui;

import com.ycm.Classes.Boat;
import com.ycm.Classes.Race;
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
import java.util.ResourceBundle;

import static com.ycm.Gui.ClubGui.*;
import static com.ycm.Gui.ClubGui.getPopupStage;
import static com.ycm.Gui.MemberGui.*;
import static com.ycm.Gui.MemberGui.a;
import static com.ycm.Gui.PaymentGui.setCost;

public class MemberSelectBoatGuiController implements Initializable {

    /**
     * SELECT BOAT OBJECTS
     **/

    @FXML
    private TableColumn<Boat, Integer> boatIDCol;

    @FXML
    private TableColumn<Boat, Double> boatLengthCol;

    @FXML
    private TableColumn<Boat, String> boatNameCol;

    @FXML
    private TableView<Boat> boatTable;

    @FXML
    static ObservableList<Boat> boatsObservableList = FXCollections.observableArrayList();

    @FXML
    void paymentRaceBtn(ActionEvent event) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        Boat boat = boatTable.getSelectionModel().getSelectedItem();
        if(boat==null){
            a.setContentText("No boat has been selected");
            a.showAndWait();
        }
        else {
            try {
                setBoat(boat);
                setType(3);
                setPaid(true);
                setPopupScene(PaymentGui.paymentScene());
                setPopupTitle("Race Fee Payment");
                getPopupStage().show();
            } catch (IOException e) {
                e.printStackTrace();
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
        boatNameCol.setCellValueFactory(new PropertyValueFactory<Boat, String>("name"));
        boatIDCol.setCellValueFactory(new PropertyValueFactory<Boat, Integer>("ID"));
        boatLengthCol.setCellValueFactory(new PropertyValueFactory<Boat, Double>("length"));
        boatTable.setItems(boatsObservableList);
    }
}
