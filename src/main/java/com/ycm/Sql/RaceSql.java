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
     * @throws SQLException if there is any error with the queries.
     */
    public static Object upcomingRaces() throws SQLException {
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
}
