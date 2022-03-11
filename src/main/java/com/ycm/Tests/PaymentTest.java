package com.ycm.Tests;

import com.ycm.Classes.Member;
import com.ycm.Classes.Notification;
import com.ycm.Classes.Payment;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

import static com.ycm.Classes.Club.getToday;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private int ID=1;
    private Member m = new Member("c","c", "c", "c", "c", "c", false);
    private LocalDate paymentDate = getToday();
    private String type = "Annual subscription payment";
    private String method = "Credit card";
    private double amount = 1000.0;
    private boolean paid = false;
    private int info = 2;

    @Test
    public void standardConstructorTest() {
        Payment payment = new Payment(ID, m, type, method, amount);
        assertEquals(payment.getID(), ID);
        assertAll(()-> assertEquals(payment.getUsername(), m.getUsername()),
                ()-> assertEquals(payment.getID(), ID),
                () -> assertEquals(payment.getTypeofPayment(), type),
                () -> assertEquals(payment.getMethod(), method),
                () -> assertEquals(payment.getAmount(), amount),
                () -> assertEquals(payment.getPaymentDate(), paymentDate),
                () -> assertEquals(payment.isPaid(), paid));
    }

    @Test
    public void standardSetterTest() {
        Payment payment = new Payment();
        payment.setUsername(m);
        payment.setPaymentDate();
        payment.setTypeofPayment(type);
        payment.setAmount(amount);
        payment.setMethod(method);
        payment.setInfo(info);
        payment.setPaid(true);

        assertAll(()-> assertEquals(payment.getUsername(), m.getUsername()),
                ()-> assertEquals(payment.getPaymentDate(), paymentDate),
                () -> assertEquals(payment.getTypeofPayment(), type),
                () -> assertEquals(payment.getAmount(), amount),
                () -> assertEquals(payment.getMethod(), method),
                () -> assertEquals(payment.getInfo(), info),
                () -> assertNotEquals(false, payment.isPaid()));
    }

}
