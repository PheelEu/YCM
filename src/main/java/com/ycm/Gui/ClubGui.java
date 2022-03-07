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
     */
    public static void setScene(Scene scene) {
        mainStage.setScene(scene);
    }

    public static void setTitle(String title){mainStage.setTitle(title);}

    public static Stage getPopupStage(){return popupStage;}

    public static void setPopupScene(Scene popScene){popupStage.setScene(popScene);}

    public static int getType(){return typeof;}

    public static void setType(int t) {typeof = t;}

    public static void setPopupTitle(String title){popupStage.setTitle(title);}

    public static boolean stringToBool(String s) {
        if (s.equals("1"))
            return true;
        if (s.equals("0"))
            return false;
        throw new IllegalArgumentException(s+" is not a bool. Only 1 and 0 are.");
    }

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

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method creates a new pane for the member page.
     * @return the pane for the member add boat page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Pane ourStoryPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("story-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }

    /**
     * This method creates a new pane for the member page.
     * @return the pane for the member add boat page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Pane dockingFaresPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("docking-fares-page.fxml"));
        contentPane = (fxmlLoader.load());
        return contentPane;
    }

}