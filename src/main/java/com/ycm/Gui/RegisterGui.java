package com.ycm.Gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;


import java.io.IOException;

import static com.ycm.Gui.ClubGui.*;

/**
 * The {@code Register Gui} class defines:
 *
 * All the methods and scenes to register a new user.
 *
 * @author Filippo Euclidi
 * @author Matteo Angeloni
 **/


public class RegisterGui {

    /**
     * This method creates a new stage and a new scene for the user register page.
     * @return the stage for the user register page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Scene registerUserScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("register.fxml"));
        Scene registerScene = new Scene(fxmlLoader.load());
        //setPopupTitle("Sign Up");
        return registerScene;
    }

    /**
     * This method creates a new stage and a new scene for the add employee page.
     * @return the stage for the add employee page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    /*
    public static Stage registerEmployeeStage() throws IOException {
        Stage registerStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("add-employee-page.fxml"));
        Scene registerScene = new Scene(fxmlLoader.load());
        registerStage.setTitle("Add Employee");
        registerStage.setScene(registerScene);
        return registerStage;
    }

     */

    /**
     * This is an alert that is shown to the user.
     * it can contain information or a warning.
     **/
    public static Alert a = new Alert(Alert.AlertType.NONE);
}
