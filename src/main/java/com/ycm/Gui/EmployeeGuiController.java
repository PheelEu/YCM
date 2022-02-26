package com.ycm.Gui;

import com.ycm.Sockets.Client;
import com.ycm.Sockets.Message;
import com.ycm.Sockets.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static com.ycm.Gui.ClubGui.getMainStage;
import static com.ycm.Gui.EmployeeGui.getEmployee;
import static com.ycm.Gui.EmployeeGui.setEmployee;
import static com.ycm.Gui.MemberGui.*;


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
    void addRace(ActionEvent event) {

    }

    @FXML
    void annualSubscription(ActionEvent event) {

    }

    @FXML
    void dockingFares(ActionEvent event) {

    }



    @FXML
    void plannedRaces(ActionEvent event) {

    }

    @FXML
    void racePayments(ActionEvent event) {

    }

    @FXML
    void viewBoats(ActionEvent event) {
        try {
            contentPane.getChildren().clear();
            contentPane.getChildren().add((SelectBoatPane()));
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
