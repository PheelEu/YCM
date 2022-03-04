package com.ycm.Sql;

import com.ycm.Classes.Member;
import com.ycm.Classes.Notification;
import com.ycm.Classes.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.ycm.Gui.ClubGui.stringToBool;
import static com.ycm.Sql.QuerySql.connection;

public class NotificationSql {




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
    public static Object addNotification(int ID, String username, LocalDate expiringDate, String typeofPayment, double amount, int boatID) {
        String sqlInsert = "INSERT INTO notification (ID, username, expiringDate, typeofPayment, amount, boatID) VALUES('" + ID + "', '" + username + "', '" + expiringDate + "', '" + typeofPayment + "', '" + amount + "', '" + boatID + "')";
        try {
            connection().execute(sqlInsert);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Selects all the notifications
     *
     * @return all the notifications in the database.
     * @throws SQLException if there is any error with the queries.
     */
    public static Object viewNotifications(){
        String sqlSelect = "SELECT * FROM notification";
        try {
            ResultSet rst = connection().executeQuery(sqlSelect);
            ArrayList<Notification> notifications = new ArrayList<Notification>();
            while (rst.next()) {
                Notification n = new Notification(Integer.parseInt(rst.getString("ID")), rst.getString("username"),
                        LocalDate.parse(rst.getString("expiringDate")), rst.getString("typeofPayment"),
                        Double.parseDouble(rst.getString("amount")), Integer.parseInt(rst.getString("boatID")),
                        stringToBool(rst.getString("sent")));
                notifications.add(n);
            }
            return notifications;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Selects all the notifications that have been sent to a member
     *
     * @return all the payments in the database.
     * @throws SQLException if there is any error with the queries.
     */
    public static Object viewMemberNotifications(String username){
        String sqlSelect = "SELECT * FROM notification WHERE username='" + username + "' AND sent = 1";
        try {
            ResultSet rst = connection().executeQuery(sqlSelect);
            ArrayList<Notification> notifications = new ArrayList<Notification>();
            while (rst.next()) {
                Notification n = new Notification(Integer.parseInt(rst.getString("ID")), rst.getString("username"),
                        LocalDate.parse(rst.getString("expiringDate")), rst.getString("typeofPayment"),
                        Double.parseDouble(rst.getString("amount")), Integer.parseInt(rst.getString("boatID")),
                        stringToBool(rst.getString("sent")));
                notifications.add(n);
            }
            return notifications;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method with a query to delete a payment from the database.
     *
     * @param boatID it's the id of the boat associated to the notifications which will be deleted.
     * @return true if there are no errors.
     */
    public static Object removeBoatNotification(int boatID) {
        String sqlDelete = "DELETE FROM notification WHERE boatID='" + boatID + "'";
        try {
            connection().executeUpdate(sqlDelete);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Method with a query to delete all the notifications of a member from the database.
     *
     * @param username it's the username associated to the notifications which will be deleted.
     * @return true if there are no errors.
     */
    public static Object removeAllBoatNotification(String username) {
        String sqlDelete = "DELETE FROM notification WHERE username='" + username + "' AND boatID > 0";
        try {
            connection().executeUpdate(sqlDelete);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method with a query to delete a notification from the database.
     *
     * @param ID it's the ID associated to the notification which will be deleted.
     * @return true if there are no errors.
     */
    public static Object removeNotification(int ID) {
        String sqlDelete = "DELETE FROM notification WHERE ID='" + ID + "'";
        try {
            connection().executeUpdate(sqlDelete);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Method with a query to delete all the notifications from the database.
     *
     * @param username it's the username associated to the notifications which will be deleted.
     * @return true if there are no errors.
     */
    public static Object removeAllNotification(String username) {
        String sqlDelete = "DELETE FROM notification WHERE username='" + username + "'";
        try {
            connection().executeUpdate(sqlDelete);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * When a notification is sent, changes a boolean to true.
     *
     * @return true if there are no errors.
     * @throws SQLException if there is any error with the queries.
     **/

    public static Object notificationSent(int ID) {
        String sqlSelect = "SELECT * FROM notification WHERE ID='" + ID + "'";
        try {
            ResultSet rst = connection().executeQuery(sqlSelect);
            if (rst.next()) {
                String sqlUpdate = "UPDATE notification SET sent = 1 WHERE ID= '" + ID + "'";
                connection().executeUpdate(sqlUpdate);
                return true;
            }
            else{
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}
