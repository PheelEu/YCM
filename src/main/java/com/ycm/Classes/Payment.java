package com.ycm.Classes;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The{@code Payment} class defines:
 *
 * All the variables that describe a Payment.
 * All the methods to set the attributes of a Payment.
 *
 *@author Filippo Euclidi
 *@author Matteo Angeloni
 **/

public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    private int ID;
    private String username;
    private LocalDate paymentDate;
    private String type;
    private String method;
    private double amount;
    private boolean paid;
    private int info;

    /**
     *Class Constructor (empty)
     **/
    public Payment(){};

    /**
     * This is the payment class constructor for any generic payment.
     *
     * @param ID it's the unique ID created for this payment.
     * @param m it's the member making the payment.
     * @param type it's the type of payment to be made.
     * @param method it's the method chosen to make the payment (Credit Card or Bank Transfer)
     * @param amount it's the amount of money due.
     *
     **/
    public Payment(int ID, Member m, String type,String method, double amount){
        this.ID = ID;
        this.username = m.getUsername();
        this.paymentDate = Club.getToday();
        this.type = type;
        this.method = method;
        this.amount = amount;
        this.paid = false;
    }

    /**
     * This is the payment class constructor for a payment .
     *
     * @param ID it's the unique ID created for this payment.
     * @param username it's username of the member making the payment.
     * @param paymentDate it's the type of payment to be made.
     * @param method it's the method chosen to make the payment (Credit Card or Bank Transfer)
     * @param amount it's the amount of money due.
     * @param paid it's the boolean set to true if tha payment hase been registered, false if it hasn't.
     *
     **/
    public Payment(int ID, String username, LocalDate paymentDate,String method, double amount, boolean paid){
        this.ID = ID;
        this.username = username;
        this.paymentDate = paymentDate;
        this.method = method;
        this.amount = amount;
        this.paid = paid;
    }

    /**
     * This is the payment class constructor for a payment made for a specific boat storage fee.
     *
     * @param ID it's the unique ID created for this payment.
     * @param username it's username of the member making the payment.
     * @param paymentDate it's the type of payment to be made.
     * @param method it's the method chosen to make the payment (Credit Card or Bank Transfer)
     * @param amount it's the amount of money due.
     * @param paid it's the boolean set to true if tha payment hase been registered, false if it hasn't.
     * @param info it's the information regarding the ID of the boat set to a payment.
     *
     **/
    public Payment(int ID, String username, LocalDate paymentDate,String method, double amount, boolean paid, int info){
        this.ID = ID;
        this.username = username;
        this.paymentDate = paymentDate;
        this.method = method;
        this.amount = amount;
        this.paid = paid;
        this.info = info;
    }

    /**
     * This is the payment class constructor for a payment made for a specific boat storage fee.
     *
     * @param ID it's the unique ID created for this payment.
     * @param username it's username of the member making the payment.
     * @param paymentDate it's the type of payment to be made.
     * @param type it's the type of payment to be made.
     * @param method it's the method chosen to make the payment (Credit Card or Bank Transfer)
     * @param amount it's the amount of money due.
     * @param paid it's the boolean set to true if tha payment hase been registered, false if it hasn't.
     * @param info it's the information regarding the ID of the boat set to a payment.
     *
     **/
    public Payment(int ID, String username, LocalDate paymentDate, String type,String method, double amount, boolean paid,int info){
        this.ID = ID;
        this.username = username;
        this.paymentDate = paymentDate;
        this.type = type;
        this.method = method;
        this.amount = amount;
        this.paid = paid;
        this.info = info;
    }

    /**
     * Gets the id of the payment.
     * @return id of the last payment + 1.
     **/
    public int getID(){return ID;}

    /**
     * Gets the username of the member linked to this payment.
     * @return username of the member linked to this payment.
     **/
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the member to the payment.
     * @param m is the member to set to the payment.
     **/
    public void setUsername(Member m) {
        this.username = m.getUsername();
    }

    /**
     * Gets the issuing date of this payment.
     * @return issuedDate of this payment.
     **/
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    /**
     * Sets the issuing date of this payment to today's date.
     **/
    public void setPaymentDate() {
        this.paymentDate = Club.getToday();
    }

    /**
     * This method gets the type of payment.
     * @return the type of payment.
     **/
    public String getTypeofPayment() {
        return type;
    }

    /**
     * This method sets the type of payment for the notification.
     * @param type it's the payment type.
     **/
    public void setTypeofPayment(String type) {
        this.type = type;
    }

    /**
     * This method gets the method of payment.
     * @return the method of payment, can be by card or bank transfer.
     **/
    public String getMethod() {
        return method;
    }

    /**
     * This method sets the type of payment for the notification.
     * @param method it's the payment method, can be by card or bank transfer.
     **/
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Gets the amount of this payment.
     * @return the amount of this payment.
     **/
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the payment.
     * @param amount it's the amount of the payment.
     **/
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets if the payment has been paid.
     * @return amount of this payment.
     **/
    public boolean isPaid() {
        return paid;
    }

    /**
     * Sets if the payment has been paid.
     * @param paid it's true if the payment has been made, false if it has not.
     **/
    public void setPaid(boolean paid) {
        this.paid = paid;
    }


    /**
     * Gets the info of the boat linked to this payment.
     * @return info of the boat linked to this payment.
     **/
    public int getInfo() {
        return info;
    }

    /**
     * Sets the info of the boat to the payment.
     * @param info it's the boat info to be set to the payment.
     **/
    public void setInfo(int info) {this.info = info;}

}
