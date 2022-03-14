package com.ycm.Sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ycm.Classes.Employee;
import com.ycm.Classes.Member;


public class QuerySql {

    private static final String DBURL = "jdbc:mysql://localhost:3306/YCMClub?";
    private static final String ARGS = "createDatabaseIfNotExist=true&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";

    private static Connection conn;

    /**
     * Gets the connection.
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
        if(username.contains(".ycm")){
            String sqlSelect = "SELECT * FROM employee WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet rst = connection().executeQuery(sqlSelect);
            if(rst.next()) {
                String sqlUpdate = "UPDATE employee SET Logged = 1 WHERE username= '" + username + "' AND password= '" + password + "'";
                connection().executeUpdate(sqlUpdate);
                return new Employee(rst.getString("username"),  rst.getString("password"), rst.getString("name"), rst.getString("surname"), rst.getString("address"), rst.getString("FC"), true);
            }
        }

        if(!username.contains(".ycm")) {
            String sqlSelect = "SELECT * FROM member WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet rst = connection().executeQuery(sqlSelect);
            if (rst.next()) {
                String sqlUpdate = "UPDATE member SET logged = 1 WHERE username= '" + username + "' AND password= '" + password + "'";
                connection().executeUpdate(sqlUpdate);
                return new Member(rst.getString("username"), rst.getString("password"), rst.getString("name"), rst.getString("surname"), rst.getString("address"), rst.getString("FC"), true);
            }
        }
        return null;
    }

    /**
     * Changes the boolean logged to false (0).
     *
     * @param username the username of the person.
     * @param password the password of the person.
     *
     * @return true if there are no errors.
     **/
    public static Object logout(String username, String password) {
        String sqlUpdate = "UPDATE member SET logged = 0 WHERE username='" + username + "' AND password='" + password + "'";
        try {
            connection().execute(sqlUpdate);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Deletes an account.
     * Can be a member or an employee
     *
     * @param username the username of the person.
     * @param password the password of the person.
     *
     * @return true if account has been deleted, false if it has not.
     * @throws SQLException if there is any error with the queries.
     */
    public static Object deleteAccount(String username, String password) throws SQLException {
        String sqlDelete = "DELETE FROM member WHERE username='" + username + "' AND password='" + password + "'";
        try {
            connection().executeUpdate(sqlDelete);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
