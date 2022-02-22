package com.ycm.Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static com.ycm.Sql.QuerySql.connection;

public class PaymentSql {

    /**
     * Method with a query to insert a member into the database
     *
     * @param username the username of the member making payment.
     * @param paymentDate the date of the payment.
     * @param type the type of payment.
     * @param method the method used to pay.
     * @param amount the amount of the payment.
     * @param paid if the payment has been made.
     * @param info is the info about the payment (usually boat id)
     * @return true if there are no errors.
     */
    public static Object addPayment(int ID, String username, Date paymentDate, String type, String method, double amount, boolean paid, int info) {
        int paidInt = paid ? 1 : 0;
        String sqlInsert = "INSERT INTO payment (ID, username, paymentDate, type, method, amount, paid, info) VALUES('" + ID + "', '" + username + "', '" + paymentDate + "', '" + type + "', '" + method + "', '" + amount + "', '" + paidInt + "', '" + info + "')";
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
     * @param username it's the username associated to the payment which will be deleted.
     * @param amount it's the username associated to the payment which will be deleted.
     * @return true if there are no errors.
     */
    public static Object deletePayment(String username, Double amount) {
        String sqlDelete = "DELETE FROM payment WHERE username='" + username + "' AND amount='" + amount + "' ORDER BY 'ID' DESC LIMIT 1";
        try {
            connection().executeUpdate(sqlDelete);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method with a query get the last payment ID from database.
     *
     * @return true if there are no errors.
     */
    public static Object lastPaymentID() {
        String sqlSelect = "SELECT MAX( `ID` ) FROM `payment`";
        try{
            ResultSet rst = connection().executeQuery(sqlSelect);
            if(rst.next()){
                String number = rst.getString(1);
                return number;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
