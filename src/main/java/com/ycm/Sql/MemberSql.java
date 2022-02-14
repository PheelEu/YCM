package com.ycm.Sql;

import com.ycm.Classes.Boat;
import com.ycm.Classes.Employee;
import com.ycm.Classes.Race;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import static com.ycm.Classes.Club.getToday;
import static com.ycm.Sql.QuerySql.connection;

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
     * @param name     the name of new member.
     * @param surname  the surname of new member.
     * @param address  the address of new member.
     * @param fc       the Fiscal Code of new member.
     * @return true if there are no errors.
     */
    public static Object register(String username, String password, String name, String surname, String address, String fc) {
        String sqlInsert = "INSERT INTO member (username, password, name, surname, address, fc) VALUES('" + username + "', '" + password + "', '" + name + "', '" + surname + "', '" + address + "', '" + fc + "')";
        try {
            connection().execute(sqlInsert);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Method with a query to delete a member from database.
     *
     * @param email    the email of user that will be deleted.
     * @param password the password of user that will be deleted.
     * @return true if there are no errors.
     */
    public static Object delete(String email, String password) {
        String sqlDelete = "DELETE FROM member WHERE email='" + email + "' AND password='" + password + "'";
        try {
            connection().executeQuery(sqlDelete);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     *
     **/
    public static Object AddBoat(String name, double length, double boatStorage) {
        String sqlInsert = "INSERT INTO boat (name, length, boatStorage) VALUES('" + name + "','" + length + "','" + boatStorage + "')";
        try {
            connection().execute(sqlInsert);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Method with a query to delete a member from database.
     *
     * @param name the name of boat that will be deleted.
     * @param ID   it's the ID of the boat that will be deleted.
     * @return true if there are no errors.
     */
    public static Object removeBoat(String name, String ID) {
        String sqlDelete = "DELETE FROM boat WHERE Name='" + name + "' AND ID='" + ID + "'";
        try {
            connection().executeUpdate(sqlDelete);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Changes a boolean that tells whether a person is logged (1) or not(0).
     * Can be a member or an employee
     *
     * @param username the username of the member.
     * @param type the type of payment.
     * @return the last payment date.
     * @throws SQLException if there is any error with the queries.
     */
    public static Object expiryDate(String username, String type) throws SQLException {
        String sqlSelect = "SELECT * FROM payment WHERE username='" + username + "' AND type='" + type + "' order by paymentDate DESC LIMIT 0,1";
        try {
            ResultSet rst = connection().executeQuery(sqlSelect);
            if (rst.next()) {
                LocalDate date;
                try {
                    date = LocalDate.parse(rst.getString("paymentDate"));
                    return date;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


    /**
     * Selects all the races
     *
     * @return all the races in descending order.
     * @throws SQLException if there is any error with the queries.
     */
    public static Object memberBoats(String username) throws SQLException {
        String sqlSelect = "SELECT * FROM boat WHERE owner ='" + username + "'";
        try {
            ResultSet rst = connection().executeQuery(sqlSelect);
            ArrayList<Boat> boats = new ArrayList<Boat>();
            while (rst.next()) {
                Boat boat = new Boat(rst.getString("name"),Integer.parseInt(rst.getString("ID")), Double.parseDouble(rst.getString("length")));
                boats.add(boat);
            }
            return boats;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
