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
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.ycm.Classes.Club.getToday;
import static com.ycm.Gui.ClubGui.*;
import static com.ycm.Gui.MemberGui.*;
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
            Date date = Date.valueOf(getToday().toString());
            Object obj = new Client().run(new Request(new Message( "payment", m.getUsername(),
                    String.valueOf(date), typeOfPayment, "Credit Card", String.valueOf(getDockingCost()),
                    String.valueOf(isPaid()))));
            if((boolean) obj) {
                PaymentGui.a.setContentText("Payment successful!");
                PaymentGui.a.showAndWait();
                if(getType()==1){
                    Object boat = new Client().run(new Request(new Message( "addBoat", b.getName(),
                            String.valueOf(b.getLength()), String.valueOf(b.getBoatStorage()))));
                    if((boolean) boat) {
                        PaymentGui.a.setContentText("Boat added");
                    }
                    else{
                        PaymentGui.a.setContentText("Boat not added");
                    }
                }
                if(getType()==2){
                    PaymentGui.a.setContentText("Annual subscription accepted");
                }
                if(getType()==3){
                    PaymentGui.a.setContentText("Race fee paid");
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
            Date date = Date.valueOf(getToday().toString());
            Object obj = new Client().run(new Request(new Message( "payment", m.getUsername(),
                    String.valueOf(date), typeOfPayment, "Bank Transfer", String.valueOf(getDockingCost()),
                    String.valueOf(isPaid()))));
            if((boolean) obj) {
                PaymentGui.a.setContentText("Payment successful!");
                PaymentGui.a.showAndWait();
                if(getType()==1){
                    Object boat = new Client().run(new Request(new Message( "addBoat", b.getName(),
                            String.valueOf(b.getLength()), String.valueOf(b.getBoatStorage()))));
                    if((boolean) boat) {
                        PaymentGui.a.setContentText("Boat added");
                    }
                    else{
                        PaymentGui.a.setContentText("Boat not added");
                    }
                }
                if(getType()==2){
                    PaymentGui.a.setContentText("Annual subscription accepted");
                }
                if(getType()==3){
                    PaymentGui.a.setContentText("Race fee paid");
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
            typeOfPayment = "Boat storage payment";
            titleLabel.setText("Boat storage payment");
        }
        if (getType() == 2) {
            typeOfPayment = "Annual subscription payment";
            titleLabel.setText("Annual subscription payment");
        }
        if (getType() == 3) {
            typeOfPayment = "Race enrollment fee";
            titleLabel.setText("Race enrollment fee");
        }
    }
}

