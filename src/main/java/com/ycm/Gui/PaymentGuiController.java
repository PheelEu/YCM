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
        if(!cNameField.getText().isEmpty() && !cSurnameField.getText().isEmpty() && isValidCVVNumber(cvvField.getText()) &&
                !(dateField.getValue() == null) && isValidCVVNumber(cvvField.getText()) &&
                isValidCardNumber(cardNumber.getText()) && !getToday().isAfter(LocalDate.parse(dateField.getValue().toString()))){
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
                            PaymentGui.a.setContentText("There has been an error while removing" +
                                    " \nthe old notification for this payment!"+"\nContact us!");
                        }
                        else {
                            PaymentGui.a.setContentText("Annual subscription accepted");
                        }
                    }
                }
                if(getType()==3){
                    Object competitor = new Client().run(new Request(new Message( "addCompetitor", getMember().getUsername(),
                            String.valueOf(getBoat().getID()), getRace().getName())));

                    if((boolean) competitor){
                        PaymentGui.a.setContentText("Race fee paid");
                    }
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

