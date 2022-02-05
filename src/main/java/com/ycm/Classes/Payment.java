package com.ycm.Classes;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.Date;

public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    private int ID;
    private String username;
    private LocalDate paymentDate;
    private String type;
    private String method;
    private double amount;
    private boolean paid;

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
}
