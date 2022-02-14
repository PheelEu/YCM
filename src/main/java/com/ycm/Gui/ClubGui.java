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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClubGui extends Application {

    private static boolean serverNotFound = false;

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
}