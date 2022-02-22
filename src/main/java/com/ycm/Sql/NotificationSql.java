package com.ycm.Sql;

import java.sql.SQLException;
import java.time.LocalDate;

import static com.ycm.Sql.QuerySql.connection;

public class NotificationSql {

    /**
     * Method with a query to insert a notification into the database
     *
     * @param username the username of the member.
     * @param expiringDate the date of the next payment.
     * @param amount the amount of the payment.
     * @return true if there are no errors.
     **/
    public static Object addNotification(int ID, String username, LocalDate expiringDate, String typeofPayment, double amount) {
        String sqlInsert = "INSERT INTO subscriptionNotification (ID, username, expiringDate, typeofPayment, amount) VALUES('" + ID + "','" + username + "', '" + expiringDate + "', '" + typeofPayment + "', '" + amount + "')";
        try {
            connection().execute(sqlInsert);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Method with a query to delete a payment from the database.
     *
     * @param username it's the username associated to the notifications which will be deleted.
     * @return true if there are no errors.
     */
    public static Object removeNotification(String username) {
        String sqlDelete = "DELETE FROM subscriptionNotification WHERE username='" + username + "'";
        try {
            connection().executeUpdate(sqlDelete);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Method with a query to insert a notification into the database
     *
     * @param ID the ID of the payment which generated the notification.
     * @param username the username of the member.
     * @param expiringDate the date of the next payment.
     * @param amount the amount of the payment.
     * @param boatID the ID of the boat for the next payment.
     * @return true if there are no errors.
     **/
    public static Object addBoatNotification(int ID, String username, LocalDate expiringDate, String typeofPayment, double amount, int boatID) {
        String sqlInsert = "INSERT INTO boatStorageNotification (ID, username, expiringDate, typeofPayment, amount, boatID) VALUES('" + ID + "', '" + username + "', '" + expiringDate + "', '" + typeofPayment + "', '" + amount + "', '" + boatID + "')";
        try {
            connection().execute(sqlInsert);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Method with a query to delete a payment from the database.
     *
     * @param username it's the username associated to the notifications which will be deleted.
     * @param boatID it's the id of the boat associated to the notifications which will be deleted.
     * @return true if there are no errors.
     */
    public static Object removeBoatNotification(String username, int boatID) {
        String sqlDelete = "DELETE FROM boatStorageNotification WHERE username='" + username + "' AND boatID='" + boatID + "'";
        try {
            connection().executeUpdate(sqlDelete);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
