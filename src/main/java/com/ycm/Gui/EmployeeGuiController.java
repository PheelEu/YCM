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
import java.time.LocalDate;
import java.util.ArrayList;

import static com.ycm.Classes.Club.getToday;
import static com.ycm.Gui.ClubGui.getMainStage;
import static com.ycm.Gui.EmployeeGui.*;
import static com.ycm.Gui.EmployeeViewRGuiController.racesObservableList;
import static com.ycm.Gui.MemberGui.a;


public class EmployeeGuiController {

    @FXML
    private AnchorPane contentPane;

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
