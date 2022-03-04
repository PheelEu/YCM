package com.ycm.Classes;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The{@code Notification} class defines:
 *
 * All the variables that describe a Notification.
 * All the methods to set the attributes of a Notification.
 *
 *@author Filippo Euclidi
 *@author Matteo Angeloni
 **/

public class Notification implements Serializable {
    private final static long serialVersionUID = 1L;

    private int paymentID;
    private String username;
    private LocalDate expiringDate;
    private String typeofPayment;
    private double amount;
    private int boatID;
    private boolean sent;
    private boolean paid;

    /**
     * This is the Notification class constructor
     *
     * @param m is the member to send the notification to.
     * @param expiringDate is the expiring date of the notification.
     * @param typeofPayment is the type of tax which is due to be paid
     * @param amount it's the amount of money due.
     *
     **/
    public Notification(Member m, LocalDate expiringDate, String typeofPayment, double amount){
        this.username = m.getUsername();
        this.expiringDate = expiringDate;
        this.typeofPayment = typeofPayment;
        this.amount = amount;
    }

    /**
     * This is the Notification class constructor
     *
     * @param m is the member to send the notification to.
     * @param expiringDate is the expiring date of the notification.
     * @param typeofPayment is the type of tax which is due to be paid
     * @param amount it's the amount of money due.
     *
     **/
    public Notification(Member m, LocalDate expiringDate, String typeofPayment, double amount, int boatID){
        this.username = m.getUsername();
        this.expiringDate = expiringDate;
        this.typeofPayment = typeofPayment;
        this.amount = amount;
        this.boatID = boatID;
    }

    /**
     * This is the Notification class constructor
     *
     * @param username is the member to send the notification to.
     * @param expiringDate is the expiring date of the notification.
     * @param typeofPayment is the type of tax which is due to be paid
     * @param amount it's the amount of money due.
     * @param paymentID it's the id of the payment
     * @param boatID it's the boat ID
     * @param sent it's the boolean sent
     *
     **/
    public Notification(int paymentID, String username, LocalDate expiringDate, String typeofPayment, double amount, int boatID, boolean sent){
        this.paymentID = paymentID;
        this.username = username;
        this.expiringDate = expiringDate;
        this.typeofPayment = typeofPayment;
        this.amount = amount;
        this.boatID = boatID;
        this.sent = sent;
    }

    /**
     * This method gets the ID of the payment which the notification is referred to.
     * @return the ID of the payment
     **/
    public int getPaymentID() {
        return paymentID;
    }

    /**
     * This method sets the ID of the payment which the notification is referred to.
     * @param paymentID it's the payment ID's.
     **/
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    /**
     * This method gets the username of the member which the notification is referred to.
     * @return the username of the member
     **/
    public String getUsername() {
        return username;
    }

    /**
     * This method sets the username of the member which the notification is referred to.
     * @param m it's the member
     **/
    public void setUsername(Member m) {
        this.username = m.getUsername();
    }

    /**
     * This method gets the expiration date of the notification.
     * @return the expiration date of the notification
     **/
    public LocalDate getExpiringDate() {
        return expiringDate;
    }

    /**
     * This method sets the expiration date of the notification.
     * @param expiringDate is the expiration date of the notification.
     **/
    public void setExpiringDate(LocalDate expiringDate) {
        this.expiringDate = expiringDate;
    }

    /**
     * This method gets the type of payment for the notification.
     * @return the type of payment for this notification
     **/
    public String getTypeofPayment() {
        return typeofPayment;
    }

    /**
     * This method sets the type of payment for the notification.
     * @param p is the payment type for this notification
     **/
    public void setTypeofPayment(Payment p) {
        this.typeofPayment = p.getTypeofPayment();
    }

    /**
     * This method gets the amount due for this notification.
     * @return the amount due for this notification
     **/
    public double getAmount() {
        return amount;
    }

    /**
     * This method sets the amount due for this notification.
     * @param amount it's the amount to set for this notification.
     **/
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * This method gets the Boat ID for this notification.
     * @return the Boat ID for this notification
     **/
    public int getBoatID() {
        return boatID;
    }

    /**
     * This method sets the boat ID for this notification.
     * @param boatID it's the boat ID this notification.
     **/
    public void setBoatID(int boatID) {
        this.boatID = boatID;
    }

    /**
     * This method gets the sent boolean for this notification.
     * @return the sent boolean for this notification
     **/
    public boolean isSent() {return sent;}

    /**
     * This method sets the sent boolean for this notification.
     * @param sent it's true if sent, false if not.
     **/
    public void setSent(boolean sent) {
        this.sent = sent;
    }

    /**
     * This method gets the paid boolean for this notification.
     * @return the paid boolean for this notification
     **/
    public boolean isPaid() {
        return paid;
    }

    /**
     * This method sets the paid boolean for this notification.
     * @param paid it's true if paid, false if not.
     **/
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

}
