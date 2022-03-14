package com.ycm.Gui;

import com.ycm.Classes.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class EmployeeGui {

    static Pane contentPane = new Pane();

    static Employee e = null;

    public static ArrayList<Boat> mBoats = new ArrayList<>();

    public static ArrayList<Race> viewRaces = new ArrayList<>();

    public static ArrayList<Member> membersInfo = new ArrayList<>();

    public static ArrayList<Payment> payments = new ArrayList<>();

    public static ArrayList<Notification> notifications = new ArrayList<>();

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
     * @return the pane to view all the members of the club.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Pane viewMembers() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("view-members-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }

    /**
     * This method creates a new pane for the employee page.
     * @return the pane to select a boat for the member.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Pane viewBoats() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("view-all-boats-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }

    /**
     * This method creates a new pane for the employee page.
     * @return the pane to view all the upcoming races of the club.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Pane viewRaces() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("view-races-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }


    /**
     * This method creates a new pane for the employee page.
     * @return the pane to add a race.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Pane addRaces() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("add-race-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }

    /**
     * This method creates a new pane for the employee page.
     * @return the pane to view the payments made.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Pane viewPayments() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("view-payments-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }

    /**
     * This method creates a new pane for the employee page.
     * @return the pane to view the notifications to be sent.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Pane viewNotifications() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("employee-notifications-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }
}
