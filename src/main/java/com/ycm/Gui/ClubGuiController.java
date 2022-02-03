
package com.ycm.Gui;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;

        import java.io.IOException;

        import static com.ycm.Gui.ClubGui.getPopupStage;
        import static com.ycm.Gui.ClubGui.setPopupScene;

public class ClubGuiController {

    @FXML
    void loginLink(ActionEvent event) {
        try {
            setPopupScene(LoginGui.LoginScene());
            getPopupStage().show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registerLink(ActionEvent event) {
        try {
            setPopupScene(RegisterGui.registerUserScene());
            getPopupStage().show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
