package com.ycm.Tests;

import com.ycm.Classes.Member;
import com.ycm.Classes.Notification;
import com.ycm.Classes.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.ycm.Classes.Club.getToday;
import static org.junit.jupiter.api.Assertions.*;

public class NotificationTest{

    private int paymentID=1;
    private Member m = new Member("c","c", "c", "c", "c", "c", false);
    private String username = "c";
    private LocalDate expiringDate = getToday();
    private Payment p = new Payment(1, m, "type", "method", 100.0);
    private double amount = 100.0;
    private int boatID = 2;
    private boolean sent = false;

    @Test
    public void standardConstructorTest() {
        Notification notification = new Notification(m, expiringDate, p.getTypeofPayment(), amount);
        assertAll(()-> assertEquals(notification.getUsername(), m.getUsername()),
                ()-> assertEquals(notification.getExpiringDate(), expiringDate),
                () -> assertEquals(notification.getTypeofPayment(), p.getTypeofPayment()),
                () -> assertEquals(notification.getAmount(), amount));
    }

    @Test
    public void standardSetterTest() {
        Notification notification = new Notification();
        notification.setUsername(m);
        notification.setExpiringDate(expiringDate);
        notification.setTypeofPayment(p);
        notification.setAmount(amount);
        notification.setBoatID(boatID);
        notification.setSent(true);
        assertAll(()-> assertEquals(notification.getUsername(), username),
                ()-> assertEquals(notification.getExpiringDate(), expiringDate),
                () -> assertEquals(notification.getTypeofPayment(), p.getTypeofPayment()),
                () -> assertEquals(notification.getAmount(), amount),
                () -> assertEquals(notification.getBoatID(), boatID),
                () -> assertNotEquals(false, notification.isSent()));
    }
}
