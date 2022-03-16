package com.ycm.Gui;

import com.ycm.Classes.Employee;
import com.ycm.Classes.Member;
import com.ycm.Classes.Person;
import com.ycm.Sockets.Client;
import com.ycm.Sockets.Message;
import com.ycm.Sockets.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

import static com.ycm.Gui.ClubGui.*;
import static com.ycm.Gui.EmployeeGui.getEmployee;
import static com.ycm.Gui.EmployeeGui.setEmployee;
import static com.ycm.Gui.MemberGui.*;

/**
 * The {@code Login Gui Controller} class defines:
 *
 * All the methods and their implementations in the Login Gui interface.
 *
 * @author Filippo Euclidi
 * @author Matteo Angeloni
 **/

public class LoginGuiController {

    @FXML
    private PasswordField passField;

    @FXML
    private TextField userField;

    /**
     * This method it's called by a GUI event, when the 'login' button gets pressed
     * Enables the Person who clicked it to log in to the system and sets the GUI according to the type of person who logged
     *
     * @param event it's the triggered event
     **/
    @FXML
    void loginBtn(ActionEvent event) {
        if(userField.getText().isEmpty()){
            LoginGui.a.setAlertType(Alert.AlertType.INFORMATION);
            LoginGui.a.setContentText("Username field is empty!");
            LoginGui.a.showAndWait();
        }
        else if(passField.getText().isEmpty()){
            LoginGui.a.setAlertType(Alert.AlertType.INFORMATION);
            LoginGui.a.setContentText("Password field is empty!");
            LoginGui.a.showAndWait();
        }
        else if(!userField.getText().isEmpty() && !passField.getText().isEmpty()){
            Object person = new Client().run(new Request(new Message( "login", userField.getText(), passField.getText())));
            if(person!=null){
                if(person instanceof Employee){
                    setEmployee((Employee) person);
                    if(getEmployee() != null){
                        try {
                            setScene(EmployeeGui.EmployeeWelcomeScene());
                            ClubGui.getPopupStage().close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(person instanceof Member){
                    setMember((Member) person);
                    if(getMember() != null){
                        try {
                            setScene(MemberGui.MemberWelcomeScene());
                            SceneSize(MemberWelcomeScene(), getMainStage());
                            ClubGui.getPopupStage().close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            else{
                LoginGui.a.setAlertType(Alert.AlertType.INFORMATION);
                LoginGui.a.setContentText("Username or password are not correct \nPress Register button if you re not already registered");
                LoginGui.a.showAndWait();
            }
        }
    }

    /**
     * This method it's called by a GUI event, when the Register link gets pressed
     * Enables the person who clicked it to jump to the register page, and register into the system as a Member
     * @param event it's the triggered event
     **/
    @FXML
    void registerLink(ActionEvent event) {
        try{
            getPopupStage().close();
            setPopupScene(RegisterGui.registerUserScene());
            getPopupStage().show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
