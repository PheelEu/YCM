package com.ycm.Gui;

import com.ycm.Classes.Employee;
import com.ycm.Classes.Member;
import com.ycm.Sockets.Client;
import com.ycm.Sockets.Message;
import com.ycm.Sockets.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.ycm.Gui.ClubGui.getMainStage;
import static com.ycm.Gui.ClubGui.setScene;
import static com.ycm.Gui.DeleteAccountGui.a;

public class DeleteAccountGuiController {

    @FXML
    private PasswordField passField;

    @FXML
    private TextField userField;

    @FXML
    void DeleteAccountBtn(ActionEvent event) throws IOException {
        a.setAlertType(Alert.AlertType.INFORMATION);
        if(userField.getText().isEmpty()){
            a.setContentText("Username field is empty!");
            a.showAndWait();
        }
        if(passField.getText().isEmpty()){
            a.setContentText("Password field is empty!");
            a.showAndWait();
        }
        if(!userField.getText().isEmpty() && !passField.getText().isEmpty()){
            Object person = new Client().run(new Request(new Message( "deleteAccount", userField.getText(), passField.getText())));
            System.out.println((boolean)person);
            if((boolean) person) {
                a.setContentText("Your Account has been deleted");
                a.showAndWait();
                FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("welcome-page.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                getMainStage().setTitle("YCM Club");
                getMainStage().setScene(scene);
                getMainStage().show();
            }
            else{
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Username or password are not correct \nAccount not deleted");
                a.showAndWait();
            }
        }
        else{
            a.setContentText("Unknown error");
            a.showAndWait();
        }
    }
}