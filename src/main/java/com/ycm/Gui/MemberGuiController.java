package com.ycm.Gui;

import static com.ycm.Classes.Boat.getPriceForMeter;
import static com.ycm.Classes.Club.getToday;
import static com.ycm.Gui.ClubGui.*;
import static com.ycm.Gui.MemberGui.*;
import static com.ycm.Gui.MemberNotificationGuiController.notificationsObservableList;

import com.ycm.Classes.Boat;
import com.ycm.Classes.Notification;
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

/**
 * The {@code Member Gui Controller} class defines:
 *
 * All the methods and their implementations in the Member Gui interface.
 *
 * @author Filippo Euclidi
 * @author Matteo Angeloni
 **/

public class MemberGuiController {

    @FXML
    private AnchorPane contentPane;

    /**
     * This method it's called by a GUI event, when the 'our story' Link gets pressed
     * Enables the member who clicked it to jump to the 'our story' page, and view the history and presentation of the club
     * @param event it's the triggered event
     **/
    @FXML
    void ourStory(ActionEvent event) {
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().add((ourStoryPane()));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * ADD-BOAT-PAGE JAVAFX OBJECTS
     */

    /**
     * This method it's called by a GUI event, when the 'add boat' Link gets pressed
     * Enables the member who clicked it to jump to the add boat page, and insert a new boat in the club
     * @param event it's the triggered event
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

    /**
     * This method it's called by a GUI event, when the 'cost' button gets clicked
     * Enables the member who clicked it to check how much will it cost to register a specific boat to the club
     *
     * @param event it's the triggered event
     **/
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

    /**
     * This method it's called by a GUI event, when the 'docking payment' button gets clicked
     * It enables the member who clicked it to add a new boat to the club, if the input is accepted by the filters
     * If input is accepted the payment page gets opened.
     *
     * @param event it's the triggered event
     **/
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
        if(boatName.getText().isEmpty() && boatLength.getText().isEmpty()) {
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
                setBoatRegistered(false);
                setType(1);
                setPaid(true);
                setPopupScene(PaymentGui.paymentScene());
                setPopupTitle("Boat Storage Payment");
                Object lastBoatID = new Client().run(new Request(new Message( "lastBoatID")));
                Boat boat;
                if(lastBoatID != null) {
                    boat = new Boat(boatName.getText(), Integer.parseInt(String.valueOf(lastBoatID)) + 1, Double.parseDouble(boatLength.getText()));
                }
                else{
                    boat = new Boat(boatName.getText(), 1, Double.parseDouble(boatLength.getText()));
                }
                setBoat(boat);
                getPopupStage().show();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method it's called by a GUI event, when the 'docking fares' Link gets pressed
     * Enables the member who clicked it to jump to the docking fares page, and view the docking fares of the club
     * @param event it's the triggered event
     **/
    @FXML
    void dockingFares(ActionEvent event) {
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().add((dockingFaresPane()));

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method it's called by a GUI event, when the 'remove boat' Link gets pressed
     * Enables the member who clicked it to jump to the remove boat page, and view all the boats registered to his account
     * and if he wants to he can remove one or more.
     *
     * @param event it's the triggered event
     **/
    @FXML
    void removeBoat(ActionEvent event) {
        setSelectBoatGuiType(2);
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().add((SelectBoatPane()));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Annual Subscription Objects
     */

    /**
     * This method it's called by a GUI event, when the 'subscription' Link gets pressed
     * Enables the member who clicked it to jump to the member subscription page.
     * @param event it's the triggered event
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

    /**
     * This method it's called by a GUI event, when the 'check next payment' link gets clicked
     * Enables the member who clicked it to check when is the next annual subscription payment due
     * If the expiry date is after the present day, a button to make the annual sub payment
     * it's enabled and the payment page opened
     *
     * @param event it's the triggered event
     **/
    @FXML
    void checkNextPaymentLink(ActionEvent event) {
        Object date = new Client().run(new Request(new Message( "expiryDate", getMember().getUsername(), "Annual subscription payment")));
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

    /**
     * This method it's called by a GUI event, when the 'subscription payment' button gets clicked
     * Enables the member who clicked it to pay for the annual subscription. If the expiry date is after the present day,
     * the payment page is opened and the pay button hidden.
     *
     * @param event it's the triggered event
     **/
    @FXML
    void subscriptionPayBtn(ActionEvent event) {
        try{
            setType(2);
            setRegistered(true);
            setPaid(true);
            setPopupScene(PaymentGui.paymentScene());
            setPopupTitle("Annual Subscription Payment");
            getPopupStage().show();
            subscriptionPayBtn.setVisible(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method it's called by a GUI event, when the 'delete account' link gets clicked
     * Enables the member who clicked it to jump to the 'delete account' page and if he wants to delete his account permanently
     *
     * @param event it's the triggered event
     **/
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


    /*
     * UPCOMING RACES OBJECTS
     */


    /**
     * This method it's called by a GUI event, when the 'upcoming races' link gets clicked
     * Enables the member who clicked it to jump to the 'upcoming races' page and select a race to join.
     *
     * @param event it's the triggered event
     **/
    @FXML
    void upcomingRaces(ActionEvent event) {
        setSelectBoatGuiType(1);
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
        else{
            a.setContentText("No race Available");
            a.showAndWait();
        }
    }

    /*
     *NOTIFICATION OBJECTS
     */

    /**
     * This method it's called by a GUI event, when the 'notifications' icon gets clicked
     * Enables the member who clicked it to jump to the 'notifications' page view his notifications if any are present.
     *
     * @param event it's the triggered event
     **/
    @FXML
    void notificationIcon(MouseEvent event) {
        mNotifications.clear();
        a.setAlertType(Alert.AlertType.INFORMATION);

        Object viewMemberNotifications = new Client().run(new Request(new Message("viewMemberNotifications", getMember().getUsername())));
        if(((ArrayList<Notification>) viewMemberNotifications).isEmpty()){
            a.setContentText("No notification Available");
            a.showAndWait();
            try {
                setScene(MemberWelcomeScene());
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                contentPane.getChildren().clear();
                contentPane.getChildren().add((memberNotificationsPane()));
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
            mNotifications = (ArrayList<Notification>) viewMemberNotifications;
            if (!(mNotifications.isEmpty())) {
                notificationsObservableList.clear();
            }
            for (int i = 0; i < mNotifications.size(); i++) {
                notificationsObservableList.add(new Notification(mNotifications.get(i).getPaymentID(), mNotifications.get(i).getUsername(),
                        mNotifications.get(i).getExpiringDate(), mNotifications.get(i).getTypeofPayment(), mNotifications.get(i).getAmount(),
                        mNotifications.get(i).getBoatID(), mNotifications.get(i).isSent()));
            }
        }
    }

    /*
     *LOGOUT OBJECTS
     */

    /**
     * This method it's called by a GUI event, when the 'logout' icon gets clicked
     * Enables the member who clicked it to log out of the system
     *
     * @param event it's the triggered event
     **/
    @FXML
    void logoutIcon(MouseEvent event) throws IOException {
        a.setAlertType(Alert.AlertType.INFORMATION);
        Object logout = new Client().run(new Request(new Message( "logout", getMember().getUsername(), getMember().getPassword())));
        if((boolean) logout){
            FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("welcome-page.fxml"));
            setMember(null);
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