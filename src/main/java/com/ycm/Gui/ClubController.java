package com.ycm.Gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClubController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}