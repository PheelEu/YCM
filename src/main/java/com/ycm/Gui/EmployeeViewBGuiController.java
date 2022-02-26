package com.ycm.Gui;

import com.ycm.Classes.Boat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EmployeeViewBGuiController {

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
    void removeBoatBtn(ActionEvent event) {

    }

}

