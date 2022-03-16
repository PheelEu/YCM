package com.ycm.Gui;

import com.ycm.Sockets.Client;
import com.ycm.Sockets.Message;
import com.ycm.Sockets.Request;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
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

public class ClubGui extends Application{

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
        SceneSize(scene, mainStage);
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
    public static void setPopupScene(Scene popScene){
        popupStage.setScene(popScene);
        //SceneSize(popScene, popupStage);
    }

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

        if (obj instanceof Exception) {
            serverNotFound = true;
        }

        //Creating a new stage and Popup stage
        mainStage = new Stage();
        popupStage = new Stage();

        //Loading first Scene from a fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("welcome-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        //Setting the scene and the title, making window resizable
        mainStage.setTitle("YCM Club");
        mainStage.setScene(scene);
        mainStage.setResizable(true);
        mainStage.show();
        SceneSize(scene, mainStage);
    }

    public static void SceneSize(Scene scene, Stage stage){
        //Getting screen bounds
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        final double initWidth = scene.getWidth();
        final double initHeight = scene.getHeight();
        final double ratio = initWidth / initHeight;

        //Setting max height and max width to screen bounds
        stage.setMaxHeight(screenBounds.getHeight());
        stage.setMaxWidth(screenBounds.getWidth());

        //Modifying stage size to resized value and scaling the whole scene
        stage.minWidthProperty().bind(scene.heightProperty().multiply(1.45));
        stage.minHeightProperty().bind(scene.widthProperty().divide(1.45));
        SceneSizeChangeListener sizeListener = new SceneSizeChangeListener(scene, ratio, initHeight, initWidth, stage);
        scene.widthProperty().addListener(sizeListener);
        scene.heightProperty().addListener(sizeListener);
    }

    record SceneSizeChangeListener(Scene scene, double ratio, double initHeight, double initWidth,
                                   Stage stage) implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
            final double newWidth = stage.getWidth();
            final double newHeight = stage.getHeight();

            double scaleFactor =
                    newWidth / newHeight > ratio
                            ? newHeight / initHeight
                            : newWidth / initWidth;

            Scale scale = new Scale(scaleFactor, scaleFactor);
            scale.setPivotX(0);
            scale.setPivotY(0);
            scene.getRoot().getTransforms().setAll(scale);
        }
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