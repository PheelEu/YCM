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

public class LoginGuiController {

    static Employee employee = null;
    static Member member = null;

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
                System.out.println(person);
                if(person instanceof Employee){
                    System.out.println("sono una employee");
                    employee = (Employee) person;
                }
                if(person instanceof Member){
                    System.out.println("sono un memeber");
                    member = (Member) person;
                }
            }
            if(employee != null){
                System.out.println("Sono un employee");
                //TODO Chiamata ad employee gui e set dell' employee e della scena

            }
            if(member != null){
                System.out.println("Sono un member");
                try {
                    setScene(MemberGui.MemberWelcomeScene());
                    MemberGui.setMember(member);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ClubGui.getPopupStage().close();
        }
        else{
            LoginGui.a.setAlertType(Alert.AlertType.INFORMATION);
            LoginGui.a.setContentText("Username or password are not correct \nPress Register button if you re not already registered");
            LoginGui.a.showAndWait();
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
