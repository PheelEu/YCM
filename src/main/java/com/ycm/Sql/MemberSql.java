package com.ycm.Sql;


import java.sql.SQLException;

/**
 * The class {@code MemberSql} defines a collection of queries for members.
 *
 * @author Filippo Euclidi
 * @author Matteo Angeloni
 */
public class MemberSql {

    /**
     * Method with a query to insert a member into the database
     *
     * @param username the username of the new member.
     * @param password the password of new member.
     * @param name the name of new member.
     * @param surname the surname of new member.
     * @param address the address of new member.
     * @param fc the Fiscal Code of new member.
     * @return true if there are no errors.
     */
    public static Object register(String username, String password, String name, String surname, String address, String fc) {
        String sqlInsert = "INSERT INTO member (username, password, name, surname, address, fc) VALUES('"+ username + "', '" + password + "', '" + name + "', '" + surname + "', '" + address + "', '" + fc + "')";
        try {
            QuerySql.connection().execute(sqlInsert);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Method with a query to delete a member from database.
     *
     * @param email the email of user that will be delete.
     * @param password the password of user that will be delete.
     * @return true if there are no errors.
     */
    public static Object delete(String email, String password) {
        String sqlDelete = "DELETE FROM member WHERE email='" + email + "' AND password='" + password + "'";
        try {
            QuerySql.connection().executeQuery(sqlDelete);
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    /**
     * Create an order in the database.
     * This is as a purchase in the shop.
     *
     * @param email the email of the user buying.
     * @param nameWine the name of the wine that user wants.
     * @param producerWine the producer's name of the wine that user wants.
     * @param yearWine the production year of the wine that user wants.
     * @param address the user's address.
     * @param numBottles the number of bottles that user buying.
     */
    public static void AddBoat(String email, String nameWine, String producerWine, String yearWine, String address, int numBottles) {
        String sqlInsert = "INSERT INTO buyorder (Email, NameWine, ProducerWine, YearWine, Address, NumBottles, Done) VALUES('" + email + "','" + nameWine + "','" + producerWine + "','"  + yearWine + "','" + address + "','" + numBottles + "','0')";
        try {
            QuerySql.connection().execute(sqlInsert);
        }
        catch (SQLException e) {
            return;
        }
    }

    /**
     * Create a new notification made by user in the database.
     *
     * @param email the email of the user that notify.
     * @param nameWine the name of the wine interested in the notification.
     * @param producerWine the producer's name of the wine.
     * @param yearWine the production year of the wine.
     * @param numBottles the number of bottles wanted by user.
     * @return true if there are no errors.
     */
    public static Object sendNotify(String email, String nameWine, String producerWine, String yearWine, int numBottles) {
        String sqlInsert = "INSERT INTO sendnotify (Email, NameWine, ProducerWine, YearWine, NumBottles) VALUES('" + email + "','" + nameWine + "','" + producerWine + "','"  + yearWine + "','" + numBottles + "')";
        try {
            QuerySql.connection().execute(sqlInsert);
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }
}
