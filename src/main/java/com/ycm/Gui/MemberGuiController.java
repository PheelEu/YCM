package com.ycm.Gui;

import static com.ycm.Classes.Boat.getPriceForMeter;
import static com.ycm.Gui.ClubGui.*;
import static com.ycm.Gui.MemberGui.*;

import com.ycm.Classes.Boat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MemberGuiController {

    @FXML
    private AnchorPane contentPane;

    /**
     * ADD-BOAT-PAGE JAVAFX OBJECTS
     **/

    @FXML
    void addBoat(ActionEvent event) {
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().add((MemberAddBoatPane()));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField boatLength;

    @FXML
    private TextField boatName;

    @FXML
    private Label liveCost;

    @FXML
    void CostBtn(ActionEvent event) {
        String cost = "Incorrect Value";
        if(boatLength.getText().isEmpty()){
            cost = "Field is empty";
            liveCost.setText(cost);
        }
        if(!boatLength.getText().isEmpty()){
            try {
                Double boatCost = Double.parseDouble(boatLength.getText())*getPriceForMeter();
                setDockingCost(boatCost);
                cost = String.valueOf(boatCost);
                liveCost.setText(cost + " €");
            }catch(Exception e){
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Value inserted is not a number");
                a.showAndWait();
            }
        }
        else{
            liveCost.setText(cost);
        }
    }

    @FXML
    void dockingPaymentBtn(ActionEvent event) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        if(boatName.getText().isEmpty()){
            a.setContentText("The boat name field is empty");
            a.showAndWait();
        }
        if(boatLength.getText().isEmpty()){
            a.setContentText("The boat length field is empty");
            a.showAndWait();
        }
        if(boatName.getText().isEmpty() && !boatLength.getText().isEmpty()) {
            a.setContentText("both fields are empty");
            a.showAndWait();
        }
        if(!boatLength.getText().isEmpty() && !boatName.getText().isEmpty()){
            try {
                Double boatCost = Double.parseDouble(boatLength.getText())*getPriceForMeter();
                setDockingCost(boatCost);
            }catch(Exception e){
                a.setContentText(e.getMessage());
                a.showAndWait();
            }
            try {
                setType(1);
                setPaid(true);
                setPopupScene(PaymentGui.paymentScene());
                setPopupTitle("Boat Storage Payment");
                Boat boat = new Boat(boatName.getText(), Double.parseDouble(boatLength.getText()));
                setBoat(boat);
                getPopupStage().show();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    void dockingFares(ActionEvent event) {

    }

    @FXML
    void raceResults(ActionEvent event) {

    }

    @FXML
    void removeBoat(ActionEvent event) {

    }

    /**
     * Annual Subscription Objects
     **/
    @FXML
    void subscription(ActionEvent event) {
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().add((MemberSubscriptionPane()));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Label dateLabel;

    @FXML
    private Label nextPaymentLabel;
    
    @FXML
    void checkNextPaymentLink(ActionEvent event) {
        nextPaymentLabel.setVisible(true);

    }

    @FXML
    void subscriptionPayBtn(ActionEvent event) {

    }


    @FXML
    void upcomingRaces(ActionEvent event) {

    }

}