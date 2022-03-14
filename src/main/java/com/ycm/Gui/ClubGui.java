package com.ycm.Gui;

import com.ycm.Sockets.Client;
import com.ycm.Sockets.Message;
import com.ycm.Sockets.Request;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The {@code Club Gui} class defines:
 *
 * The main stage of the application and the first scene of the application.
 * It's the starting point of our application and enables the other classes in the GUI to change between different scenes
 * and switch between panes.
 *
 * @author Filippo Euclidi
 * @author Matteo Angeloni
 **/

public class ClubGui extends Application {

    private static boolean serverNotFound = false;

    static Pane contentPane = new Pane();

    private static Stage mainStage;

    private static Stage popupStage;

    private static int typeof;

    public static Stage getMainStage() {return mainStage;}

    /**
     * Set the scene of main stage.
     *
     * @param scene the scene.
     **/
    public static void setScene(Scene scene) {
        mainStage.setScene(scene);
    }

    /**
     * Sets the title of the maneStage
     *
     * @param title it's the title to be set for the mainStage
     **/
    public static void setTitle(String title){mainStage.setTitle(title);}


    /**
     * Gets the popup stage
     *
     * @return the Popup stage
     **/
    public static Stage getPopupStage(){return popupStage;}

    /**
     * Sets the popup scene
     *
     * @param popScene it's the scene to set for the popup stage
     **/
    public static void setPopupScene(Scene popScene){popupStage.setScene(popScene);}

    /**
     * Gets the type of payment to be made
     *
     * @return the type of payment that was set to be made
     **/
    public static int getType(){return typeof;}


    /**
     * Sets the type of payment to be made
     * @param t it's the type of payment so be set
     **/
    public static void setType(int t) {typeof = t;}

    /**
     * Sets the title of the popup stage
     * @param title the title to be set for the popup stage
     **/
    public static void setPopupTitle(String title){popupStage.setTitle(title);}

    /**
     *This method translates a string to a boolean
     * @param s it's the string to be translated
     * @return true or false depending on the value inserted. null if the input it's not 0 or 1.
     **/
    public static boolean stringToBool(String s) {
        if (s.equals("1"))
            return true;
        if (s.equals("0"))
            return false;
        throw new IllegalArgumentException(s+" is not a bool. Only 1 and 0 are.");
    }

    /**
     * This is the main stage, it's the starting point of our application regarding all the GUI classes.
     * @param stage it's the stage to be set when the application is executed.
     * @throws IOException if any exception gets thrown.
     **/
    @Override
    public void start(Stage stage) throws IOException {
        Object obj = new Client().run(new Request(new Message("")));

        if(obj instanceof Exception){
            serverNotFound = true;
        }
        mainStage = new Stage();
        popupStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("welcome-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage.setTitle("YCM Club");
        mainStage.setScene(scene);
        mainStage.show();
    }

    /**
     * This is the main of our GUI application.
     * @param args
     **/
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method creates a new pane for the Club page.
     * @return the pane for the story page on the club gui.
     * @throws IOException it's an exception thrown if something in the GUI does not work.
     **/
    public static Pane ourStoryPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("story-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }

    /**
     * This method creates a new pane for the Club page.
     * @return the pane for the docking fares page.
     * @throws IOException it's an exception thrown if something in the GUI does not work
     **/
    public static Pane dockingFaresPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("docking-fares-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }

}