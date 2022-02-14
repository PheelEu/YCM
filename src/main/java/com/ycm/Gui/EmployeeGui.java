package com.ycm.Gui;

import com.ycm.Classes.Employee;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class EmployeeGui {

    static Employee e = null;

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
     * This method creates a new stage and a new scene for the employee welcome page.
     * @return the stage for the employee welcome page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Scene EmployeeWelcomeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("welcome-page-employee.fxml"));
        Scene employeeWelcomeScene = new Scene(fxmlLoader.load());
        return employeeWelcomeScene;
    }
}
