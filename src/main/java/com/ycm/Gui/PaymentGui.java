package com.ycm.Gui;

import com.ycm.Classes.Boat;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentGui {

    private static LocalDate paymentDate;

    private static Double cost;

    public static void setPaymentDate(LocalDate paymentDate) {
        PaymentGui.paymentDate = paymentDate;
    }

    public static LocalDate getPaymentDate() {
        return paymentDate;
    }

    public static void setCost(Double cost) {
        PaymentGui.cost = cost;
    }

    public static Double getCost() {
        return cost;
    }

    /**
     * Regex Checks
     **/

    /**
     * CVV Regex Check
     * @param str is the input string
     * @return true or false if is acceptable or not
     **/

    public static boolean isValidCVVNumber(String str)
    {
        String regex = "^[0-9]{3,4}$";

        Pattern p = Pattern.compile(regex);

        if (str == null)
        {
            return false;
        }

        Matcher m = p.matcher(str);

        return m.matches();
    }

    /**
     * Card Number Regex Check
     * @param str is the input string
     * @return true or false if is acceptable or not
     **/
    public static boolean isValidCardNumber(String str)
    {
        String regex = "(\\d{4}[-. ]?){4}|\\d{4}[-. ]?\\d{6}[-. ]?\\d{5}";

        Pattern p = Pattern.compile(regex);

        if (str == null)
        {
            return false;
        }

        Matcher m = p.matcher(str);

        return m.matches();
    }

    /**
     * This is an alert that is shown to the user.
     * it can contain information or a warning.
     **/
    public static Alert a = new Alert(Alert.AlertType.NONE);

    /**
     * This method creates a new stage and a new scene for the user member welcome page.
     * @return the stage for the member welcome page.
     * @throws IOException is an exception thrown if something in the GUI does not work
     **/
    public static Scene paymentScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClubGui.class.getResource("payment-page.fxml"));
        Scene paymentScene = new Scene(fxmlLoader.load());
        return paymentScene;
    }
}
