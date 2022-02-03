package com.ycm.Gui;

import com.ycm.Classes.Member;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MemberGui {

    /**
     * Creating a null Member for the GUI, to set afterwards
     **/
    public static Member m = null;

    /**
     * Is a set method to set the gui member once the member logs in
     * @param m is the user which just logged in
     **/
    public static void setMember(Member m){MemberGui.m = m;}

    /**
     * Is a get method to get the gui member which is logged.
     * @return the member which is logged in
     **/
    public static Member getMember(){return m;}

    /**
     * This method creates a new stage and a new scene for the user member welcome page.
     * @return the stage for the member welcome page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Scene MemberWelcomeScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("welcome-page-member.fxml"));
        Scene MemberWelcomeScene = new Scene(fxmlLoader.load());
        return MemberWelcomeScene;
    }
}
