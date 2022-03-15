
package com.ycm.Gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.io.IOException;
import static com.ycm.Gui.ClubGui.*;

/**
 * The {@code Club Gui Controller} class defines:
 *
 * All the methods and their implementations in the Club interface.
 *
 * @author Filippo Euclidi
 * @author Matteo Angeloni
 **/

public class ClubGuiController {

    @FXML
    private Pane contentPane;

    /**
     * This method it's called by a GUI event, when the Login Icon gets pressed
     * Enables the person who clicked it to jump to the login page, and log into the system
     * @param event it's the triggered event
     **/
    @FXML
    void LoginIcon(MouseEvent event) {
        try {
            setPopupScene(LoginGui.LoginScene());
            getPopupStage().show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method it's called by a GUI event, when the Register Icon gets pressed
     * Enables the person who clicked it to jump to the register page, and register into the system as a Member
     * @param event it's the triggered event
     **/
    @FXML
    void registerIcon(MouseEvent event) {
        try {
            setPopupScene(RegisterGui.registerUserScene());
            getPopupStage().show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method it's called by a GUI event, when the 'our story' Link gets pressed
     * Enables the person who clicked it to jump to the 'our story' page, and view the history and presentation of the club
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

    /**
     * This method it's called by a GUI event, when the 'docking fares' Link gets pressed
     * Enables the person who clicked it to jump to the docking fares page, and view the docking fares of the club
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
}
