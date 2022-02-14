package com.ycm.Gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;

import static com.ycm.Gui.ClubGui.setPopupTitle;

public class DeleteAccountGui {

    /**
     * This method creates a new stage and a new scene for the member delete account page.
     * @return the stage for the  member delete account page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Scene DeleteAccountScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("delete-account-page.fxml"));
        Scene deleteAccountScene = new Scene(fxmlLoader.load());
        setPopupTitle("Delete your Account");
        return deleteAccountScene;
    }

    /**
     * This is an alert that is shown to the client.
     * It can contain information or a warning.
     **/
    public static Alert a = new Alert(Alert.AlertType.NONE);
}
