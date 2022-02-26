package com.ycm.Sql;

import com.ycm.Classes.Boat;
import com.ycm.Classes.Employee;
import com.ycm.Classes.Member;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.ycm.Gui.ClubGui.stringToBool;
import static com.ycm.Sql.QuerySql.connection;

public class EmployeeSql {

    public EmployeeSql(){};

    /*
    public static removeMember(){}

    public static removeBoat(){}

    public static addRace(){}

    public static removeRace(){}


     */
    /**
     * Create a new notification made by user in the database.
     *
     * @return true if there are no errors.
     **/
    public static Object sendNotification(String Username, Date expiringDate, String typeofPayment, double amount){
        String sqlInsert = "INSERT INTO sendNotification(Username, expiringDate, typeofPayment, amount) VALUES('" + Username + "','" + expiringDate + "','" + typeofPayment + "','"  + amount + "')";
        try {
            QuerySql.connection().execute(sqlInsert);
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    /*

    public static viewPayments(){}
     */

    /**
     * Method with a query to delete all the boats of a member from the database.
     *
     * @param username the name of the member which owns the boats.
     * @return true if there are no errors.
     */
    public static Object removeAllBoat(String username) {
        String sqlDelete = "DELETE FROM boat WHERE owner='" + username + "'";
        try {
            connection().executeUpdate(sqlDelete);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Selects all the members
     *
     * @return all the members registered.
     * @throws SQLException if there is any error with the queries.
     */
    public static Object members(){
        String sqlSelect = "SELECT * FROM member";
        try {
            ResultSet rst = connection().executeQuery(sqlSelect);
            ArrayList<Member> members = new ArrayList<Member>();
            while (rst.next()) {
                boolean logged = stringToBool(rst.getString("logged"));
                Member member = new Member(rst.getString("username"),"", rst.getString("name"),
                        rst.getString("surname"), rst.getString("address"), rst.getString("fc"), logged);

                members.add(member);
            }
            return members;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method with a query to delete a member from the database.
     *
     * @param username the name of the member to be deleted.
     * @return true if there are no errors.
     */
    public static Object removeMember(String username) {
        String sqlDelete = "DELETE FROM member WHERE username='" + username + "'";
        try {
            connection().executeUpdate(sqlDelete);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
