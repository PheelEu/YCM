package com.ycm.Gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.io.IOException;

import static com.ycm.Gui.ClubGui.*;

public class MemberGuiController {

    @FXML
    void addBoat(ActionEvent event) {
        try {
            setScene(MemberGui.MemberWelcomeScene());
        } catch (
                IOException e) {
            e.printStackTrace();
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

    @FXML
    void subscription(ActionEvent event) {

    }

    @FXML
    void upcomingRaces(ActionEvent event) {

    }

}