package com.ycm.Gui;

import com.ycm.Sockets.Client;
import com.ycm.Sockets.Message;
import com.ycm.Sockets.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.ycm.Classes.Club.getToday;
import static com.ycm.Gui.ClubGui.*;
import static com.ycm.Gui.MemberGui.*;
import static com.ycm.Gui.MemberGui.isBoatRegistered;
import static com.ycm.Gui.PaymentGui.*;

/**
 * The {@code Payment Gui Controller} class defines:
 *
 * All the methods and their implementations in the Payment Gui interface.
 *
 * @author Filippo Euclidi
 * @author Matteo Angeloni
 **/

public class PaymentGuiController implements Initializable {

    public String typeOfPayment;

    @FXML
    private TextField cNameField;

    @FXML
    private TextField cSurnameField;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField cvvField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField bNameField;

    @FXML
    private TextField bSurnameField;

    @FXML
    private TextField bicSwiftField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField ibanField;

    @FXML
    private Label titleLabel;

    /**
     * This method it's called by a GUI event, when the 'pay' button if the credit card pane gets clicked
     * It enables the member who clicked it to make a new payment, if the input is accepted by the filters
     * If input is accepted the payment is added to the database and a new notification could be created based on the type
     * of payment that has been made.
     *
     * @param event it's the triggered event
     **/
    @FXML
    void cPayBtn(ActionEvent event) {
        PaymentGui.a.setAlertType(Alert.AlertType.INFORMATION);
        if(cNameField.getText().isEmpty()){
            PaymentGui.a.setContentText("Name field is Empty");
        }
        if(cSurnameField.getText().isEmpty()){
            PaymentGui.a.setContentText("Surname field is Empty");
        }
        if(cardNumber.getText().isEmpty()){
            PaymentGui.a.setContentText("Card number field is Empty");
        }
        if(!(dateField.getValue() == null)) {
            if (getToday().isAfter(LocalDate.parse(dateField.getValue().toString()))) {
                PaymentGui.a.setContentText("Card expiry date is not valid");
            }
        }
        if(dateField.getValue() == null){
            PaymentGui.a.setContentText("Card expiry date field is empty");
        }
        if(!isValidCVVNumber(cvvField.getText())) {
            PaymentGui.a.setContentText("CVV numbers are not acceptable");
        }
        if(!isValidCardNumber(cardNumber.getText())){
            PaymentGui.a.setContentText("Card number is not acceptable");
        }
        /*
            If all the input fields are correct, then proceed to the payment.
            Else some or all of the input is incorrect or unacceptable.
         */
        if(!cNameField.getText().isEmpty() && !cSurnameField.getText().isEmpty() && isValidCVVNumber(cvvField.getText()) &&
                !(dateField.getValue() == null) && isValidCVVNumber(cvvField.getText()) &&
                isValidCardNumber(cardNumber.getText()) && !getToday().isAfter(LocalDate.parse(dateField.getValue().toString()))){
            LocalDate date;
            Object obj;
            int paymentID = 1;
            //Checks for the last payment made and sets this payment new ID.
            Object lastPaymentID = new Client().run(new Request(new Message( "lastPaymentID")));
            if(lastPaymentID != null) {
                paymentID = Integer.parseInt(String.valueOf(lastPaymentID)) + 1;
            }
            //Checks if the type of payment is an annual subscription and adds the payment.
            if(Objects.equals(typeOfPayment, "Annual subscription payment")){
                date = getPaymentDate();
                obj = new Client().run(new Request(new Message( "addPayment", String.valueOf(paymentID),getMember().getUsername(),
                        String.valueOf(date), typeOfPayment, "Credit Card", String.valueOf(getCost()),
                        String.valueOf(isPaid()), String.valueOf(0))));
            }
            //for other types of payment we use a different constructor
            else{
                date = LocalDate.parse(getToday().toString());
                //If the boat is already in the system.
                if(isBoatRegistered()){
                    obj = new Client().run(new Request(new Message( "addPayment", String.valueOf(paymentID),getMember().getUsername(),
                            String.valueOf(date), typeOfPayment, "Credit Card", String.valueOf(getNotification().getAmount()),
                            String.valueOf(isPaid()), String.valueOf(getNotification().getBoatID()))));
                }
                //If a member is adding a new boat
                else{
                    obj = new Client().run(new Request(new Message( "addPayment", String.valueOf(paymentID),getMember().getUsername(),
                            String.valueOf(date), typeOfPayment, "Credit Card", String.valueOf(getCost()),
                            String.valueOf(isPaid()), String.valueOf(getBoat().getID()))));
                }
            }
            //Checks if the payment has been registered successfully
            if((boolean) obj) {
                PaymentGui.a.setContentText("Payment successful!");
                PaymentGui.a.showAndWait();
                //Depending on the type of payment a specific notification is made and a
                // unique alert containing information is shown to the Member
                if(getType()==1){
                    //If the payment was for a boat already registered
                    if(isBoatRegistered()){
                        Object notification = new Client().run(new Request(new Message( "addNotification", String.valueOf(paymentID), getMember().getUsername(),
                                getToday().plusYears(1).toString(), typeOfPayment, String.valueOf(getNotification().getAmount()), String.valueOf(getNotification().getBoatID()))));
                        Object removeNotification = new Client().run(new Request(new Message( "removeNotification", String.valueOf(getNotification().getPaymentID()))));
                        //If the notification is not created successfully
                        if(!(boolean) notification){
                            PaymentGui.a.setContentText("""
                                    There has been an error while creating\s
                                    the notification for this payment!
                                    Contact us!""");
                        }
                        //If the notification of the older payment has not been deleted successfully
                        if(!(boolean) removeNotification){
                            PaymentGui.a.setContentText("""
                                    There has been an error while removing\s
                                    the old notification for this payment!
                                    Contact us!""");
                        }
                        //If everything worked correctly
                        if((boolean) removeNotification){
                            PaymentGui.a.setContentText("Boat Storage Payment Accepted");
                        }
                    }
                    //If the boat is registered for the first time
                    else{
                        Object boat = new Client().run(new Request(new Message( "addBoat", String.valueOf(getBoat().getID()), getBoat().getName(),
                                String.valueOf(getBoat().getLength()), String.valueOf(getBoat().getBoatStorage()), getMember().getUsername())));
                        //if the boat is added correctly to the database
                        if((boolean) boat) {
                            Object notification = new Client().run(new Request(new Message( "addNotification", String.valueOf(paymentID), getMember().getUsername(),
                                    getToday().plusYears(1).toString(), typeOfPayment, String.valueOf(getCost()), String.valueOf(getBoat().getID()))));
                            //If the notification is not created correctly for this boat
                            if(!(boolean) notification){
                                PaymentGui.a.setContentText("""
                                        There has been an error while creating\s
                                        the notification for this payment!
                                        Contact us!""");
                            }
                            else {
                                PaymentGui.a.setContentText("Boat added");
                            }
                        }
                        //if the boat does not get added to the database
                        else{
                            PaymentGui.a.setContentText("Boat not added");
                            Object deletePayment = new Client().run(new Request(new Message( "deletePayment", getMember().getUsername(), String.valueOf(getCost()))));
                        }
                    }
                }
                //If the type of payment is for the annual subscription
                if(getType()==2){
                    Object notification = new Client().run(new Request(new Message("addNotification", String.valueOf(paymentID), getMember().getUsername(),
                            String.valueOf(date.plusYears(1)), typeOfPayment, String.valueOf(getCost()), "0")));
                    //If the notification for this payment was not created successfully
                    if (!(boolean) notification) {
                        PaymentGui.a.setContentText("""
                                There has been an error while creating\s
                                the notification for this payment!
                                Contact us!""");
                    }
                    //If the member was not registered and this is the first time paying the annual subscription
                    if (!isRegistered()) {
                        Object member = new Client().run(new Request(new Message("register", getMember().getUsername(), getMember().getPassword(), getMember().getName(), getMember().getSurname(), getMember().getAddress(), getMember().getFC())));
                        //If the member is registered correctly
                        if ((boolean) member) {
                            PaymentGui.a.setContentText("Member registered successfully!");
                        }
                        //If the member is not registered correctly
                        if (!(boolean) member) {
                            PaymentGui.a.setContentText("Something went wrong, contact an employee!");
                            Object deletePayment = new Client().run(new Request(new Message("deletePayment", getMember().getUsername(), String.valueOf(getCost()))));
                            //payment made gets deleted so that there is no proof he actually paid
                            if(!(boolean) deletePayment){
                                PaymentGui.a.setContentText("Something went wrong, contact an employee!");
                            }
                        }

                    }
                    //If the member is already registered to the club database
                    else {
                        Object removeNotification = new Client().run(new Request(new Message( "removeNotification", String.valueOf(getNotification().getPaymentID()))));
                        //If old notification is not removed
                        if(!(boolean) removeNotification){
                            PaymentGui.a.setContentText("There has been an error while removing" +
                                    " \nthe old notification for this payment!"+"\nContact us!");
                        }
                        //If the notification gets removed
                        else {
                            PaymentGui.a.setContentText("Annual subscription accepted");
                        }
                    }
                }
                //If the type of payment is to add a competitor to a race
                if(getType()==3){
                    Object competitor = new Client().run(new Request(new Message( "addCompetitor", getMember().getUsername(),
                            String.valueOf(getBoat().getID()), getRace().getName())));
                    //If the competitor is added correctly
                    if((boolean) competitor){
                        PaymentGui.a.setContentText("Race fee paid");
                    }
                    //If the competitor is not added
                    else{
                        PaymentGui.a.setContentText("Race not paid");
                    }
                }
                getPopupStage().close();
            }
            else{
                PaymentGui.a.setContentText("Payment unsuccessful");
                PaymentGui.a.showAndWait();
            }
        }
        if(!(Objects.equals(PaymentGui.a.getContentText(), ""))) {
            PaymentGui.a.showAndWait();
        }
        else{
            PaymentGui.a.setContentText("Unknown Error");
            PaymentGui.a.showAndWait();
        }
    }


    /**
     * This method it's called by a GUI event, when the 'pay' button if the bank transfer pane gets clicked
     * It enables the member who clicked it to make a new payment, if the input is accepted by the filters
     * If input is accepted the payment is added to the database and a new notification could be created based on the type
     * of payment that has been made.
     *
     * @param event it's the triggered event
     **/
    //All the comments made for the Credit Card payment are the same also in the Bank Transfer
    @FXML
    void bPayBtn(ActionEvent event) {
        PaymentGui.a.setAlertType(Alert.AlertType.INFORMATION);
        if(bNameField.getText().isEmpty()){
            PaymentGui.a.setContentText("Name field is Empty");
        }
        if(bSurnameField.getText().isEmpty()){
            PaymentGui.a.setContentText("Surname field is Empty");
        }
        if(ibanField.getText().isEmpty()){
            PaymentGui.a.setContentText("Iban field is Empty");
        }
        if(bicSwiftField.getText().isEmpty()) {
            PaymentGui.a.setContentText("Bic field is Empty");
        }
        if(emailField.getText().isEmpty()){
            PaymentGui.a.setContentText("Email field is Empty");
        }
        if(!bNameField.getText().isEmpty() && !bSurnameField.getText().isEmpty() && !ibanField.getText().isEmpty()
                && !bicSwiftField.getText().isEmpty() && !emailField.getText().isEmpty()){
            LocalDate date;
            Object obj;
            int paymentID = 1;
            Object lastPaymentID = new Client().run(new Request(new Message( "lastPaymentID")));
            if(lastPaymentID != null) {
                paymentID = Integer.parseInt(String.valueOf(lastPaymentID)) + 1;
            }
            if(Objects.equals(typeOfPayment, "Annual subscription payment")){
                date = getPaymentDate();
                obj = new Client().run(new Request(new Message( "addPayment", String.valueOf(paymentID),getMember().getUsername(),
                        String.valueOf(date), typeOfPayment, "Bank Transfer", String.valueOf(getCost()),
                        String.valueOf(isPaid()), String.valueOf(0))));
            }
            else{
                date = LocalDate.parse(getToday().toString());
                if(isBoatRegistered()){
                    obj = new Client().run(new Request(new Message( "addPayment", String.valueOf(paymentID),getMember().getUsername(),
                            String.valueOf(date), typeOfPayment, "Bank Transfer", String.valueOf(getNotification().getAmount()),
                            String.valueOf(isPaid()), String.valueOf(getNotification().getBoatID()))));
                }
                else{
                    obj = new Client().run(new Request(new Message( "addPayment", String.valueOf(paymentID),getMember().getUsername(),
                            String.valueOf(date), typeOfPayment, "Bank Transfer", String.valueOf(getCost()),
                            String.valueOf(isPaid()), String.valueOf(getBoat().getID()))));
                }
            }
            if((boolean) obj) {
                PaymentGui.a.setContentText("Payment successful!");
                PaymentGui.a.showAndWait();
                if(getType()==1){
                    if(isBoatRegistered()){
                        Object notification = new Client().run(new Request(new Message( "addNotification", String.valueOf(paymentID), getMember().getUsername(),
                                getToday().plusYears(1).toString(), typeOfPayment, String.valueOf(getNotification().getAmount()), String.valueOf(getNotification().getBoatID()))));
                        Object removeNotification = new Client().run(new Request(new Message( "removeNotification", String.valueOf(getNotification().getPaymentID()))));
                        if(!(boolean) notification){
                            PaymentGui.a.setContentText("""
                                    There has been an error while creating\s
                                    the notification for this payment!
                                    Contact us!""");
                        }
                        if(!(boolean) removeNotification){
                            PaymentGui.a.setContentText("""
                                    There has been an error while removing\s
                                    the old notification for this payment!
                                    Contact us!""");
                        }
                        if((boolean) removeNotification){
                            PaymentGui.a.setContentText("Boat Storage Payment Accepted");
                        }
                    }
                    else{
                        Object boat = new Client().run(new Request(new Message( "addBoat", String.valueOf(getBoat().getID()), getBoat().getName(),
                                String.valueOf(getBoat().getLength()), String.valueOf(getBoat().getBoatStorage()), getMember().getUsername())));
                        if((boolean) boat) {
                            Object notification = new Client().run(new Request(new Message( "addNotification", String.valueOf(paymentID), getMember().getUsername(),
                                  getToday().plusYears(1).toString(), typeOfPayment, String.valueOf(getCost()), String.valueOf(getBoat().getID()))));
                            if(!(boolean) notification){
                            PaymentGui.a.setContentText("""
                                    There has been an error while creating\s
                                    the notification for this payment!
                                    Contact us!""");
                            }
                            else {
                                PaymentGui.a.setContentText("Boat added");
                            }
                        }
                        else{
                            PaymentGui.a.setContentText("Boat not added");
                            Object deletePayment = new Client().run(new Request(new Message( "deletePayment", getMember().getUsername(), String.valueOf(getCost()))));
                        }
                    }
                }
                if(getType()==2){
                    Object notification = new Client().run(new Request(new Message("addNotification", String.valueOf(paymentID), getMember().getUsername(),
                            String.valueOf(date.plusYears(1)), typeOfPayment, String.valueOf(getCost()), "0")));
                    if (!(boolean) notification) {
                        PaymentGui.a.setContentText("""
                                There has been an error while creating\s
                                the notification for this payment!
                                Contact us!""");
                    }
                    if (!isRegistered()) {
                        Object member = new Client().run(new Request(new Message("register", getMember().getUsername(), getMember().getPassword(), getMember().getName(), getMember().getSurname(), getMember().getAddress(), getMember().getFC())));
                        if ((boolean) member) {
                            PaymentGui.a.setContentText("Member registered successfully!");
                        }
                        if (!(boolean) member) {
                            PaymentGui.a.setContentText("Something went wrong, contact an employee!");
                            Object deletePayment = new Client().run(new Request(new Message("deletePayment", getMember().getUsername(), String.valueOf(getCost()))));
                            if(!(boolean) deletePayment){
                                PaymentGui.a.setContentText("Something went wrong, contact an employee!");
                            }
                        }
                    } else {
                        Object removeNotification = new Client().run(new Request(new Message( "removeNotification", String.valueOf(getNotification().getPaymentID()))));
                        if(!(boolean) removeNotification){
                            PaymentGui.a.setContentText("""
                                    There has been an error while removing\s
                                    the old notification for this payment!
                                    Contact us!""");
                        }
                        else {
                            PaymentGui.a.setContentText("Annual subscription accepted");
                        }
                    }
                }
                if(getType()==3) {
                    Object competitor = new Client().run(new Request(new Message("addCompetitor", getMember().getUsername(),
                            String.valueOf(getBoat().getID()), getRace().getName())));
                    if ((boolean) competitor) {
                        PaymentGui.a.setContentText("Race fee paid");
                    } else {
                        PaymentGui.a.setContentText("Race not paid");
                    }
                }
                getPopupStage().close();
            }
            else{
                PaymentGui.a.setContentText("Payment unsuccessful");
                PaymentGui.a.showAndWait();
            }
        }
        if(!(Objects.equals(PaymentGui.a.getContentText(), ""))) {
            PaymentGui.a.showAndWait();
        }
        else{
            PaymentGui.a.setContentText("Unknown Error");
            PaymentGui.a.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (getType() == 1) {
            setCost(getDockingCost());
            typeOfPayment = "Boat storage payment";
            titleLabel.setText("Boat storage payment");
        }
        if (getType() == 2) {
            setCost(1000.0);
            typeOfPayment = "Annual subscription payment";
            titleLabel.setText("Annual subscription payment");
        }
        if (getType() == 3) {
            setCost(getRace().getCost());
            typeOfPayment = "Race enrollment fee";
            titleLabel.setText("Race enrollment fee");
        }
    }
}

