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
     * @param email the email of user that will be deleted.
     * @param password the password of user that will be deleted.
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
     *
     *
     **/
    public static void AddBoat(String name, String ID, double length, double boatStorage) {
        String sqlInsert = "INSERT INTO boat (Name, ID, Length, BoatStorage) VALUES('" + name + "','" + ID + "','" + length + "','"  + boatStorage + "')";
        try {
            QuerySql.connection().execute(sqlInsert);
        }
        catch (SQLException e) {
            return;
        }
    }

    /**
     * Method with a query to delete a member from database.
     *
     * @param name the name of boat that will be deleted.
     * @param ID it's the ID of the boat that will be deleted.
     * @return true if there are no errors.
     */
    public static Object removeBoat(String name, String ID) {
        String sqlDelete = "DELETE FROM boat WHERE Name='" + name + "' AND ID='" + ID + "'";
        try {
            QuerySql.connection().executeQuery(sqlDelete);
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

}
