package com.ycm.Gui;

import com.ycm.Classes.Employee;
import com.ycm.Classes.Member;
import com.ycm.Classes.Person;
import com.ycm.Sockets.Client;
import com.ycm.Sockets.Message;
import com.ycm.Sockets.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.ycm.Gui.ClubGui.*;
import static com.ycm.Gui.EmployeeGui.getEmployee;
import static com.ycm.Gui.EmployeeGui.setEmployee;
import static com.ycm.Gui.MemberGui.getMember;
import static com.ycm.Gui.MemberGui.setMember;

public class LoginGuiController {

    @FXML
    private PasswordField passField;

    @FXML
    private TextField userField;

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
