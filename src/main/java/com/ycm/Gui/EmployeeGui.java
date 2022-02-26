package com.ycm.Gui;

import com.ycm.Classes.Employee;
import com.ycm.Classes.Member;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class EmployeeGui {

    static Pane contentPane = new Pane();

    static Employee e = null;

    public static ArrayList<Member> membersInfo = new ArrayList<Member>();

    /**
     * It's a set method to set the gui employee after the login
     * @param e is the employee which just logged in
     **/
    public static void setEmployee(Employee e){EmployeeGui.e = e;}

    /**
     * It's a get method to get the gui employee currently logged.
     * @return the employee which is logged in
     **/
    public static Employee getEmployee(){return e;}

    /**
     * This is an alert that is shown to the employee.
     * it can contain information or a warning.
     **/
    static Alert a = new Alert(Alert.AlertType.NONE);

    /**
     * This method creates a new stage and a new scene for the employee welcome page.
     * @return the stage for the employee welcome page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Scene EmployeeWelcomeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("welcome-page-employee.fxml"));
        Scene employeeWelcomeScene = new Scene(fxmlLoader.load());
        return employeeWelcomeScene;
    }

    /**
     * This method creates a new pane for the employee page.
     * @return the pane to select a boat for the member.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Pane viewMembers() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("view-members-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }
}
