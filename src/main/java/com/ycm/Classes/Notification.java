package com.ycm.Classes;

import java.io.Serializable;
import java.util.Date;

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


    private String username;
    private Date expiringDate;
    private String typeofPayment;
    private double amount;

    /**
     * This is the Notification class constructor
     *
     * @param m is the member to send the notification to.
     * @param expiringDate is the expiring date of the notification.
     * @param typeofPayment is the type of tax which is due to be paid
     * @param amount it's the amount of money due.
     *
     **/
    public Notification(Member m, Date expiringDate, String typeofPayment, double amount){
        this.username = m.getUsername();
        this.expiringDate = expiringDate;
        this.typeofPayment = typeofPayment;
        this.amount = amount;
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
    public Date getExpiringDate() {
        return expiringDate;
    }

    /**
     * This method sets the expiration date of the notification.
     * @param expiringDate is the expiration date of the notification.
     **/
    public void setExpiringDate(Date expiringDate) {
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

}
