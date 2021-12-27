package com.ycm.Gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

import static com.ycm.Gui.ClubGui.*;

public class ClubController {

    @FXML
    public void registerBtn() {
        try {
            setPopupScene(RegisterGui.registerUserScene());
            getPopupStage().show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}