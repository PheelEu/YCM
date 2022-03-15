package com.ycm.Sql;

import com.ycm.Classes.Race;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


import static com.ycm.Sql.QuerySql.connection;

public class RaceSql {

    /**
     * Selects all the races
     *
     * @return all the races in descending order.
     * @throws SQLException if there is any error with the query.
     **/
    public static Object upcomingRaces() {
        String sqlSelect = "SELECT * FROM race WHERE raceDay > CURRENT_DATE() order by raceDay";
        try {
            ResultSet rst = connection().executeQuery(sqlSelect);
            ArrayList<Race> races = new ArrayList<Race>();
            while (rst.next()) {
                Race race = new Race(rst.getString("name"),  Double.parseDouble(rst.getString("cost")), LocalDate.parse(rst.getString("raceDay")));
                races.add(race);
            }
            return races;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method that adds a competitor to a specified race.
     *
     * @return true if a competitor is added correctly, false if not.
     * @throws SQLException if there is any error with the query.
     **/
    public static Object addCompetitor(String username, int boatID, String raceName) throws SQLException{
        String sqlInsert = "INSERT INTO competitors (username, boatID, raceName) VALUES('" + username + "', '" + boatID + "', '" + raceName + "')";
        try {
            connection().execute(sqlInsert);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if a competitor is already present or not in a race.
     *
     * @return true if is present, false if not and can be therefore added to the race.
     * @throws SQLException if there is any error with the query.
     */
    public static Object checkCompetitors(int boatID, String raceName) {
        String sqlSelect = "SELECT * FROM competitors WHERE boatID = '" + boatID + "' AND raceName = '" + raceName + "'";
        try {
            ResultSet rst = connection().executeQuery(sqlSelect);
            if(rst.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * Method that adds a race to the database (this method can be accessed only by an employee).
     *
     * @return true if a race is added correctly, false if not.
     * @throws SQLException if there are any errors with the query.
     **/
    public static Object addRace(String name, double cost, LocalDate raceDay) {
        String sqlInsert = "INSERT INTO race (name, cost, raceDay) VALUES('" + name + "','" + cost + "','" + raceDay + "')";
        try {
            connection().execute(sqlInsert);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method with a query to delete a race from the database and its competitors.
     *
     * @return true if there are no errors.
     */
    public static Object removeRace(String name) {
        String sqlDeleteR = "DELETE FROM race WHERE name='" + name + "'";
        String sqlDeleteC = "DELETE FROM competitors WHERE raceName='" + name + "'";
        try {
            connection().executeUpdate(sqlDeleteR);
            connection().executeUpdate(sqlDeleteC);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Method with a query to remove a competitor from all races.
     *
     * @return true if there are no errors.
     */
    public static Object removeCompetitor(String username) {
        String sqlDeleteC = "DELETE FROM competitors WHERE username='" + username + "'";
        try {
            connection().executeUpdate(sqlDeleteC);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
