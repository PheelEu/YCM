package com.ycm.Sql;

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
     * @return true if there are no errors.
     */
    public static Object addPayment(String username, Date paymentDate, String type, String method, double amount, boolean paid) {
        int paidInt = paid ? 1 : 0;
        String sqlInsert = "INSERT INTO payment (username, paymentDate, type, method, amount, paid) VALUES('" + username + "', '" + paymentDate + "', '" + type + "', '" + method + "', '" + amount + "', '" + paidInt + "')";
        try {
            connection().execute(sqlInsert);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
