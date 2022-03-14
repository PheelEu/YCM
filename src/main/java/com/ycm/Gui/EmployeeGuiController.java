package com.ycm.Gui;

import com.ycm.Classes.Race;
import com.ycm.Sockets.Client;
import com.ycm.Sockets.Message;
import com.ycm.Sockets.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

import static com.ycm.Classes.Club.getToday;
import static com.ycm.Gui.ClubGui.getMainStage;
import static com.ycm.Gui.EmployeeGui.*;
import static com.ycm.Gui.EmployeeViewRGuiController.racesObservableList;
import static com.ycm.Gui.MemberGui.a;

/**
 * The {@code Employee Gui Controller} class defines:
 *
 * All the methods and their implementations in the Employee Gui interface.
 *
 * @author Filippo Euclidi
 * @author Matteo Angeloni
 **/

public class EmployeeGuiController {

    @FXML
    private AnchorPane contentPane;

    /**
     * This method it's called by a GUI event, when the 'view members' Link gets pressed
     * Enables the Employee who clicked it to jump to the view members page, and view the members registered to the club
     * @param event it's the triggered event
     **/
    @FXML
    void viewMembers(ActionEvent event) {
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().add((EmployeeGui.viewMembers()));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method it's called by a GUI event, when the 'view boats' Link gets pressed
     * Enables the Employee who clicked it to jump to the view boats page, and view the boats registered to the club
     * @param event it's the triggered event
     **/
    @FXML
    void viewBoats(ActionEvent event) {
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().add((EmployeeGui.viewBoats()));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

     /**
     * This method it's called by a GUI event, when the 'planned races' Link gets pressed
     * Enables the Employee who clicked it to jump to the planned races page, and view the upcoming races
     * @param event it's the triggered event
     **/
    @FXML
    void plannedRaces(ActionEvent event) {
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().add((EmployeeGui.viewRaces()));
        } catch (
                IOException e) {
            e.printStackTrace();
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
        else{
            a.setContentText("No race Available");
            a.showAndWait();
        }
    }

    /**
     * Add Race GUI Components
     **/

    /**
     * This method it's called by a GUI event, when the 'add race' Link gets pressed
     * Enables the Employee who clicked it to jump to the add race page, and add new competitions to the club
     * @param event it's the triggered event
     **/
    @FXML
    void addRace(ActionEvent event) {
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().add((EmployeeGui.addRaces()));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private DatePicker datePicker;

    @FXML
    private Pane mainPane;

    @FXML
    private TextField raceFee;

    @FXML
    private TextField raceName;

    /**
     * This method it's called by a GUI event, when the 'add a race' button gets pressed
     * Enables the Employee who clicked it to add a new competition to the club
     *
     * @param event it's the triggered event
     * @throws IOException if any input/output exception gets thrown
     **/
    @FXML
    void addARace(ActionEvent event) {
        a.setAlertType(Alert.AlertType.INFORMATION);
        if(raceName.getText().isEmpty()){
            a.setContentText("The race name field is empty");
        }
        if(raceFee.getText().isEmpty()){
            a.setContentText("The race fee field is empty");
        }
        if(datePicker.getValue() == null) {
            a.setContentText("no date has been inserted");
        }
        if(!(datePicker.getValue() == null)) {
            if(datePicker.getValue().isBefore(getToday())) {
                a.setContentText("the date inserted is not valid");
            }
        }
        if(!(datePicker.getValue() == null ) && !raceFee.getText().isEmpty() && !raceName.getText().isEmpty()) {
            if(!datePicker.getValue().isBefore(getToday())){
                Race r = new Race(raceName.getText(), Double.parseDouble(raceFee.getText()), datePicker.getValue());
                Object addRace = new Client().run(new Request(new Message("addRace", r.getName(), String.valueOf(r.getCost()), String.valueOf(r.getRaceDay()))));
                if((boolean) addRace){
                    a.setContentText("Race added");
                }
                else{
                    a.setContentText("Race not added, name might be a duplicate");
                }
            }
            else{
                a.setContentText("Date not valid");
            }
        }
        a.showAndWait();
    }

    /**
     * This method it's called by a GUI event, when the 'view notifications' Link gets pressed
     * Enables the Employee who clicked it to jump to the view notifications page, and view all the notifications of the club
     * @param event it's the triggered event
     **/
    @FXML
    void viewNotifications(ActionEvent event) {
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().add((EmployeeGui.viewNotifications()));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method it's called by a GUI event, when the 'view payments' Link gets pressed
     * Enables the Employee who clicked it to jump to the view payments page, and view all the payments made to the club
     * @param event it's the triggered event
     **/
    @FXML
    void viewPayments(ActionEvent event) {
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().add((EmployeeGui.viewPayments()));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method it's called by a GUI event, when the 'Logout' Icon gets pressed
     * Enables the Employee who clicked it log out of the system
     *
     * @param event it's the triggered event
     * @throws IOException if any input/output exception gets thrown
     **/
    @FXML
    void logoutIcon(MouseEvent event) throws IOException{
        a.setAlertType(Alert.AlertType.INFORMATION);
        Object logout = new Client().run(new Request(new Message( "logout", getEmployee().getUsername(), getEmployee().getPassword())));
        if((boolean) logout){
            FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("welcome-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            setEmployee(null);
            getMainStage().setTitle("YCM Club");
            getMainStage().setScene(scene);
            getMainStage().show();
            a.setContentText("Logged out correctly!");
            a.showAndWait();
        }
        else{
            a.setContentText("Something went wrong, contact the Admin!");
            a.showAndWait();
        }
    }
}
