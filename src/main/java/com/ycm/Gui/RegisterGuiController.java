package com.ycm.Gui;

import com.ycm.Sockets.Client;
import com.ycm.Sockets.Message;
import com.ycm.Sockets.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Objects;

import static com.ycm.Gui.ClubGui.getPopupStage;

/**
 * The {@code Register Gui Controller} class defines:
 *
 * All the methods and their implementations in the Register Gui interface.
 *
 * @author Filippo Euclidi
 * @author Matteo Angeloni
 **/

public class RegisterGuiController {

    @FXML
    private TextField FCField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passField;

    @FXML
    private PasswordField passField1;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField userField;

    /**
     * This method is connected to the register button.
     * Once the button is pressed, the input given by the user is checked and if it fits the input requested from the
     * system, then the filters are fulfilled and the user can be registered.
     * Passwords must match, username must be unique and all the fields must not be empty.
     * An alert is shown to confirm the registration or to give a specific warning.
     * @param event is the right click action on the button.
     **/
    @FXML
    void registerBtn(ActionEvent event) {
        RegisterGui.a.setAlertType(Alert.AlertType.INFORMATION);
        if(userField.getText().isEmpty()){
            RegisterGui.a.setContentText("Username field is Empty");
        }
        if(passField.getText().isEmpty()){
            RegisterGui.a.setContentText("password field is Empty");
        }
        if(passField1.getText().isEmpty() && !passField.getText().isEmpty()) {
            RegisterGui.a.setContentText("password check field is Empty");
        }
        if(!Objects.equals(passField.getText(), passField1.getText())) {
            RegisterGui.a.setContentText("The passwords are not the same");
        }
        /*
        if(!userField.getText().isEmpty()) {
            if ((userField.getText())) {
                RegisterGui.a.setContentText("Username is already in use!");
            }
        }

         */
        if(!userField.getText().isEmpty() && !passField.getText().isEmpty() && !passField1.getText().isEmpty()
            && Objects.equals(passField.getText(), passField1.getText())){
            Object obj = new Client().run(new Request(new Message( "register", userField.getText(), passField.getText(), nameField.getText(), surnameField.getText(), addressField.getText(), FCField.getText())));
            RegisterGui.a.setContentText("Member registered successfully!"); //TODO NON glielo abbiamo passeto ? maybe
            RegisterGui.a.showAndWait();
            getPopupStage().close();
        }
        else {
            RegisterGui.a.showAndWait();
        }
    }
}
