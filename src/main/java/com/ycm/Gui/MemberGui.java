package com.ycm.Gui;

import com.ycm.Classes.Boat;
import com.ycm.Classes.Member;
import com.ycm.Classes.Race;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class MemberGui {

    static Pane contentPane = new Pane();

    private static boolean paid;

    private static double dockingCost;

    private static Race r;

    public static ArrayList<Race> upcomingRaces = new ArrayList<Race>();

    public static ArrayList<Boat> memberBoats = new ArrayList<Boat>();

    static Member m = null;

    static Boat b = null;

    /**
     * It's a set method to set the payment is has been payed or not
     * @param p is the paid boolean to be set
     **/
    public static void setPaid(boolean p){paid = p;}

    /**
     * It's a get method to check if payment has been made.
     * @return the is paid boolean
     **/
    public static boolean isPaid(){return paid;}

    /**
     * It's a set method to set the gui docking cost
     * @param c is the docking cost to be set
     **/
    public static void setDockingCost(double c){dockingCost = c;}

    /**
     * It's a get method to get the current calculated docking cost.
     * @return the current docking cost
     **/
    public static double getDockingCost(){return dockingCost;}

    /**
     * It's a set method to set the gui member once the member logs in
     * @param m is the user which just logged in
     **/
    public static void setMember(Member m){MemberGui.m = m;}

    /**
     * It's a get method to get the gui member which is logged.
     * @return the member which is logged in
     **/
    public static Member getMember(){return m;}

    /**
     * It's a set method to set the gui boat once a boat is created
     * @param boat is the boat to be created
     **/
    public static void setBoat(Boat boat){b = boat;}

    /**
     * It's a get method to get the gui boat once a boat is created
     * @return the boat which has been create
     **/
    public static Boat getBoat(){return b;}

    /**
     * It's a set method to set the gui race once a boat is selected
     * @param race is the race to be set
     **/
    public static void setRace(Race race){ r = race;}

    /**
     * It's a get method to get the gui boat once a boat is created
     * @return the boat which has been create
     **/
    public static Race getRace(){return r;}

    /**
     * This is an alert that is shown to the user.
     * it can contain information or a warning.
     **/
    public static Alert a = new Alert(Alert.AlertType.NONE);

    /**
     * This method creates a new stage and a new scene for the member welcome page.
     * @return the stage for the member welcome page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Scene MemberWelcomeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("welcome-page-member.fxml"));
        Scene memberWelcomeScene = new Scene(fxmlLoader.load());
        return memberWelcomeScene;
    }

    /**
     * This method creates a new pane for the user member page.
     * @return the pane for the member add boat page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/

    public static Pane MemberAddBoatPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("add-boat-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }


    /**
     * This method creates a new pane for the user member page.
     * @return the pane for the member annual subscription page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/

    public static Pane MemberSubscriptionPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("annual-sub-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }

    /**
     * This method creates a new pane for the user member page.
     * @return the pane for the upcoming races page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/

    public static Pane UpcomingRacesPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("upcoming-races-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }

    /**
     * This method creates a new pane for the user member page.
     * @return the pane to select a boat for the member.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/

    public static Pane SelectBoatPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("select-boat-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }
}
