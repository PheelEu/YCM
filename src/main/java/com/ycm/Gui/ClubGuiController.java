
package com.ycm.Gui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static com.ycm.Gui.ClubGui.getPopupStage;
import static com.ycm.Gui.ClubGui.setPopupScene;

public class ClubGuiController {

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

}
