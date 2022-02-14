package com.ycm.Sql;

import java.sql.SQLException;
import java.time.LocalDate;

import static com.ycm.Sql.QuerySql.connection;

public class NotificationSql {

    /**
     * Method with a query to insert a notification into the database
     *
     * @param username the username of the member.
     * @param expiryDate the date of the next payment.
     * @param amount the amount of the payment.
     * @return true if there are no errors.
     */
    public static Object addNotification(String username, LocalDate expiryDate, String typeofPayment, double amount) {
        String sqlInsert = "INSERT INTO subscription notification (username, expiryDate, typeofPayment, amount) VALUES('" + username + "', '" + expiryDate + "', '" + typeofPayment + "', '" + amount + "')";
        try {
            connection().execute(sqlInsert);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method with a query to insert a notification into the database
     *
     * @param username the username of the member.
     * @param expiryDate the date of the next payment.
     * @param amount the amount of the payment.
     * @param boatID the ID of the boat for the next payment.
     * @return true if there are no errors.
     */
    public static Object addBoatNotification(String username, LocalDate expiryDate, String typeofPayment, double amount, int boatID) {
        String sqlInsert = "INSERT INTO boat storage notification (username, expiryDate, typeofPayment, amount, boatID) VALUES('" + username + "', '" + expiryDate + "', '" + typeofPayment + "', '" + boatID + "', '" + amount + "')";
        try {
            connection().execute(sqlInsert);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
