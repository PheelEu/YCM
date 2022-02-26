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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.ycm.Gui.ClubGui.*;
import static com.ycm.Gui.ClubGui.getPopupStage;
import static com.ycm.Gui.MemberGui.*;
import static com.ycm.Gui.MemberGui.a;


public class MemberSelectBoatGuiController implements Initializable {

    /**
     * SELECT BOAT OBJECTS
     **/

    @FXML
    private Label boatListLabel;

    @FXML
    private Button selectBoatBtn;

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
    void selectBoatBtn(ActionEvent event) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        Boat boat = boatTable.getSelectionModel().getSelectedItem();
        if(boat==null){
            a.setContentText("No boat has been selected");
            a.showAndWait();
        }
        else {
            if(getSelectBoatGuiType() == 1) {
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
            if(getSelectBoatGuiType() == 2){
                Object removeBoat = new Client().run(new Request(new Message( "removeBoat", boat.getName(), String.valueOf(boat.getID()))));
                if((boolean) removeBoat){
                    a.setContentText("Boat removed correctly");
                    Object removeNotification = new Client().run(new Request(new Message("removeBoatNotification", getMember().getUsername(), String.valueOf(boat.getID()))));
                    Object boats = new Client().run(new Request(new Message("memberBoats", getMember().getUsername())));
                    if (!(boats == null)) {
                        memberBoats = (ArrayList<Boat>) boats;
                        if (!(boatsObservableList.isEmpty())) {
                            boatsObservableList.clear();
                        }
                        for (int i = 0; i < memberBoats.size(); i++) {
                            boatsObservableList.add(new Boat(memberBoats.get(i).getName(), memberBoats.get(i).getID(), memberBoats.get(i).getLength()));
                        }
                    }

                }
                else{
                    a.setContentText("Boat not removed! \nAn Error Occurred.");
                }
                a.showAndWait();
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
        if (getSelectBoatGuiType() == 1) {
            boatListLabel.setText("the Competition");
            selectBoatBtn.setText("Go to Payment");
        }
        if (getSelectBoatGuiType() == 2) {
            boatListLabel.setText("removal");
            selectBoatBtn.setText("Remove the boat");
            Object boats = new Client().run(new Request(new Message("memberBoats", getMember().getUsername())));
            if (!(boats == null)) {
                memberBoats = (ArrayList<Boat>) boats;
                if (!(boatsObservableList.isEmpty())) {
                    boatsObservableList.clear();
                }
                for (int i = 0; i < memberBoats.size(); i++) {
                    boatsObservableList.add(new Boat(memberBoats.get(i).getName(), memberBoats.get(i).getID(), memberBoats.get(i).getLength()));
                }
            }
        }
        boatNameCol.setCellValueFactory(new PropertyValueFactory<Boat, String>("name"));
        boatIDCol.setCellValueFactory(new PropertyValueFactory<Boat, Integer>("ID"));
        boatLengthCol.setCellValueFactory(new PropertyValueFactory<Boat, Double>("length"));
        boatTable.setItems(boatsObservableList);
    }
}
