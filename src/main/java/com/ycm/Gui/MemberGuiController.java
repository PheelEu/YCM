package com.ycm.Gui;

import static com.ycm.Classes.Boat.getPriceForMeter;
import static com.ycm.Classes.Club.getToday;
import static com.ycm.Gui.ClubGui.*;
import static com.ycm.Gui.MemberGui.*;


import com.ycm.Classes.Boat;
import com.ycm.Classes.Race;
import com.ycm.Sockets.Client;
import com.ycm.Sockets.Message;
import com.ycm.Sockets.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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
    private Button subscriptionPayBtn;
    
    @FXML
    void checkNextPaymentLink(ActionEvent event) {
        Object date = new Client().run(new Request(new Message( "expiryDate", m.getUsername(), "Annual subscription payment")));
        LocalDate data = LocalDate.parse(date.toString());
        LocalDate expiryDate = data.plusYears(1);
        if(expiryDate.isBefore(getToday())){
            nextPaymentLabel.setVisible(true);
            subscriptionPayBtn.setVisible(true);
            dateLabel.setText(expiryDate.toString());
            PaymentGui.setPaymentDate(expiryDate);
        }
        if(expiryDate.isAfter(getToday()) || expiryDate == getToday()){
            nextPaymentLabel.setVisible(true);
            subscriptionPayBtn.setVisible(false);
            dateLabel.setText(expiryDate.toString());
        }
    }

    @FXML
    void subscriptionPayBtn(ActionEvent event) {
        try{
            setType(2);
            setPaid(true);
            setPopupScene(PaymentGui.paymentScene());
            setPopupTitle("Annual Subscription Payment");
            getPopupStage().show();
            subscriptionPayBtn.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void deleteAccountLink(ActionEvent event) {
        try {
            setPopupScene(DeleteAccountGui.DeleteAccountScene());
            getPopupStage().show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * UPCOMING RACES OBJECTS
     **/


    @FXML
    void upcomingRaces(ActionEvent event) {
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().add((UpcomingRacesPane()));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        Object races = new Client().run(new Request(new Message( "upcomingRaces")));
        if(!(races==null)){
            upcomingRaces = (ArrayList<Race>) races;
            if(!(MemberRacesGuiController.racesObservableList.isEmpty())){
                MemberRacesGuiController.racesObservableList.clear();
            }
            for(int i=0; i<upcomingRaces.size(); i++){
                MemberRacesGuiController.racesObservableList.add(new Race(upcomingRaces.get(i).getName(), upcomingRaces.get(i).getCost(), upcomingRaces.get(i).getRaceDay()));
            }
        }
    }

    /**
     *NOTIFICATION OBJECTS
     **/

    @FXML
    void notificationIcon(MouseEvent event) {

    }

    /**
     *LOGOUT OBJECTS
     **/

    @FXML
    void logoutIcon(MouseEvent event) throws IOException {
        a.setAlertType(Alert.AlertType.INFORMATION);
        Object logout = new Client().run(new Request(new Message( "logout", m.getUsername(), m.getPassword())));
        if((boolean) logout){
            FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("welcome-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            getMainStage().setTitle("YCM Club");
            getMainStage().setScene(scene);
            getMainStage().show();
            a.setContentText("Logged out correctly!");
            a.showAndWait();
        }
        else{
            a.setContentText("Something went wrong, contact an employee!");
            a.showAndWait();
        }

    }

}