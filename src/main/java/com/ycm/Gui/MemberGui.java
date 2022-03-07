package com.ycm.Gui;

import com.ycm.Classes.Boat;
import com.ycm.Classes.Member;
import com.ycm.Classes.Notification;
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

    public static ArrayList<Notification> mNotifications = new ArrayList<Notification>();

    private static Member m = null;

    private static Boat b = null;

    private static Notification n = null;

    private static int selectBoatGuiType;

    private static boolean registered;

    private static boolean boatRegistered;

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
     * It's a set method to set the gui race once a boat is selected
     * @param sbt is the select boat gui type
     **/
    public static void setSelectBoatGuiType(int sbt){ selectBoatGuiType = sbt;}

    /**
     * It's a get method to get the gui boat once a boat is created
     * @return the boat which has been created
     **/
    public static int getSelectBoatGuiType(){return selectBoatGuiType;}


    /**
     * It's a set method to set the if the member is already registered or not
     * @param reg is the registered boolean to be set
     **/
    public static void setRegistered(boolean reg){registered = reg;}

    /**
     * It's a get method to check if member is already registered.
     * @return the registered boolean
     **/
    public static boolean isRegistered(){return registered;}

    /**
     * It's a set method to set the if a boat is already registered or not
     * @param reg is the registered boolean to be set
     **/
    public static void setBoatRegistered(boolean reg){boatRegistered = reg;}

    /**
     * It's a get method to check if a boat is already registered.
     * @return the boat registered boolean
     **/
    public static boolean isBoatRegistered(){return boatRegistered;}

    /**
     * It's a set method to set the notification
     * @param notification is the notification to be set
     **/
    public static void setNotification(Notification notification){n = notification;}

    /**
     * It's a get method to get the notification
     * @return the notification which has been set
     **/
    public static Notification getNotification(){return n;}

    /**
     * This is an alert that is shown to the member.
     * it can contain information or a warning.
     **/
    static Alert a = new Alert(Alert.AlertType.NONE);

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
     * This method creates a new pane for the member page.
     * @return the pane for the member add boat page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Pane MemberAddBoatPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("add-boat-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }


    /**
     * This method creates a new pane for the member page.
     * @return the pane for the member annual subscription page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Pane MemberSubscriptionPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("annual-sub-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }

    /**
     * This method creates a new pane for the member page.
     * @return the pane for the upcoming races page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Pane UpcomingRacesPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("upcoming-races-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }

    /**
     * This method creates a new pane for the member page.
     * @return the pane to select a boat for the member.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Pane SelectBoatPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("select-boat-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }


    /**
     * This method creates a new pane for the member page.
     * @return the pane to select a boat for the member.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Pane memberNotificationsPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("member-notifications-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }
}
