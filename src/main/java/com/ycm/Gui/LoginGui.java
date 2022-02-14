package com.ycm.Gui;

import com.ycm.Classes.Employee;
import com.ycm.Classes.Member;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;

import static com.ycm.Gui.ClubGui.setPopupTitle;

public class LoginGui{

    /**
     * This method creates a new stage and a new scene for the user login page.
     * @return the stage for the user login page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Scene LoginScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("login.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load());
        setPopupTitle("Login");
        return loginScene;
    }

    /**
     * This is an alert that is shown to the client.
     * It can contain information or a warning.
     **/
    public static Alert a = new Alert(Alert.AlertType.NONE);
}
