package com.ycm.Sql;

import com.ycm.Classes.Employee;

import java.sql.Date;
import java.sql.SQLException;

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

}
