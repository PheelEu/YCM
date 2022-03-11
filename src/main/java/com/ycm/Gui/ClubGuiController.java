
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

}
