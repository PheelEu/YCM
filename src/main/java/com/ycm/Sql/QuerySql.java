package com.ycm.Sql;

import com.ycm.Classes.Employee;
import com.ycm.Classes.Member;

import java.sql.*;

public class QuerySql {

    private static final String DBURL = "jdbc:mysql://localhost:3306/YCMClub?";
    private static final String ARGS = "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    private static Connection conn;

    /**
     * Get the connection.
     *
     * @return the connection.
     */
    public static Connection getConnection() { return conn; }

    /**
     * Assign a connection and create a statement.
     *
     * @return the statement.
     */
    public static Statement connection() {
        Statement stmt;
        try {
            conn = DriverManager.getConnection(DBURL + ARGS, LOGIN, PASSWORD);
            stmt = conn.createStatement();
            return stmt;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Changes a boolean that tells whether a person is logged (1) or not(0).
     * Can be a member or an employee
     *
     * @param username the username of the person.
     * @param password the password of the person.
     *
     * @return the person, that can either be a member or an employee.
     * @throws SQLException if there is any error with the queries.
     */
    public static Object login(String username, String password) throws SQLException {
        if(username.contains(".myc.employees")) {
            String sqlSelect = "SELECT * FROM employee WHERE Username='" + username + "' AND Password='" + password + "'";
            ResultSet rst = connection().executeQuery(sqlSelect);
            if(rst.next()) {
                String sqlUpdate = "UPDATE employee SET Logged = 1 WHERE Username= '" + username + "' AND Password= '" + password + "'";
                connection().executeUpdate(sqlUpdate);
                return new Employee(rst.getString("Username"),  rst.getString("Password"), rst.getString("Name"), rst.getString("Surname"), rst.getString("Address"), rst.getString("FC"), true);
            }
        }
        if(!username.contains(".myc.employees")) {
            String sqlSelect = "SELECT * FROM member WHERE Username='" + username + "' AND password='" + password + "'";
            ResultSet rst = connection().executeQuery(sqlSelect);
            if(rst.next()) {
                String sqlUpdate = "UPDATE member SET Logged = 1 WHERE Username= '" + username + "' AND Password= '" + password + "'";
                connection().executeUpdate(sqlUpdate);
                return new Member(rst.getString("Username"),  rst.getString("Password"), rst.getString("Name"), rst.getString("Surname"), rst.getString("Address"), rst.getString("FC"), true);
            }
        }
        return null;
    }

    /**
     * Changes a boolean that tells whether a person is logged (0).
     *
     * @param username the email of the person.
     * @param password the password of the person.
     *
     * @return true if there are no errors.
     **/
    public static Object logout(String username, String password) {
        String sqlUpdate = "UPDATE member SET Logged = 0 WHERE Username='" + username + "' AND Password='" + password + "'";
        try {
            connection().execute(sqlUpdate);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Create a new notification made by the system to the member.
     *
     * @param nameWine the name of the wine interested in the notification.
     * @param producerWine the producer's name of the wine.
     * @param yearWine the production year of the wine.
     * @param numBottles the number of bottles (standard number).
     */
    /*
    public static void sendShopNotify(String nameWine, String producerWine, String yearWine, int numBottles) {
        String sqlInsert = "INSERT INTO sendnotify (NameWine, ProducerWine, YearWine, NumBottles) VALUES('" + nameWine + "','" + producerWine + "','"  + yearWine + "','" + numBottles + "')";
        try {
            sqlQuery.connection().execute(sqlInsert);
        }
        catch (SQLException e) {
            return;
        }
    }
     */

    /**
     * Decrease the number of wines bottles in the database.
     * It also calls buBottles because decrease bottles after an order created.
     *
     * @param email the email of user that buying.
     * @param nameWine the name of the wine wanted.
     * @param producerWine the producer's name of the wine wanted.
     * @param yearWine the production year of the wine wanted.
     * @param address the user's address.
     * @param numBottles the number of bottles of wine wanted.
     *
     * @return true if there are no errors.
     * @throws SQLException if there is any error with the queries.
     */
    /*
    public static Object decrease(String email, String nameWine, String producerWine, String yearWine, String address, int numBottles) throws SQLException {
        Wine w = (Wine) WineSQL.searchWine(nameWine, producerWine, yearWine);
        if(numBottles <= w.getNumBottles()) {
            UserSQL.buyBottles(email, nameWine, producerWine, yearWine, address, numBottles);
            WineSQL.decreaseBottles(nameWine, producerWine, yearWine, numBottles);
            if(w.getNumBottles() - numBottles == 0) {
                sendShopNotify(nameWine, producerWine, yearWine, 250);
            }
            return true;
        }
        return false;
    }
     */

    /**
     * Create a new notification made by the system to the user.
     *
     * @param nNameWine the name of the wine arrived.
     * @param nProducerWine the producer's name of the wine arrived.
     * @param nYearWine the production year of the wine arrived.
     * @param nNumBottles the number of bottles required by user.
     * @param nEmail the email of the user that sent a notification.
     *
     * @return true if there are no errors.
     */
    /*
    public static Object notifyUser(String nNameWine, String nProducerWine, String nYearWine, int nNumBottles, String nEmail) {
        String sqlInsert = "INSERT INTO sendnotify (NameWine, ProducerWine, YearWine, NumBottles, EmailRec) VALUES('" + nNameWine + "','" + nProducerWine + "','"  + nYearWine + "','" + nNumBottles + "','" + nEmail + "')";
        try {
            sqlQuery.connection().execute(sqlInsert);
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

     */
}
