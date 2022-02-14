package com.ycm.Classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * The{@code Race} class defines:
 *
 * All the variables that describe a race.
 * All the methods to set the attributes of a race.
 *
 *@author Filippo Euclidi
 *@author Matteo Angeloni
 *
 **/

public class Race implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private double cost;
    private LocalDate raceDay;

    ArrayList<Boat> competitors = new ArrayList<Boat>();

    public Race(String name, double cost, LocalDate raceDay){
        this.name = name;
        this.cost = cost;
        this.raceDay = raceDay;

    }
    public Race(String name, double cost, LocalDate raceDay,ArrayList<Boat> competitors){
        this.name = name;
        this.cost = cost;
        this.raceDay = raceDay;
        this.competitors = competitors;
    }

    /**
     * Gets the name of the race.
     * @return name of the race.
     **/
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the race.
     * @param name it's the name to set for the race.
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the cost for participating in the race.
     * @return cost of the race.
     **/
    public double getCost() {
        return cost;
    }

    /**
     * Sets the cost of the race.
     * @param cost it's the cost to set for taking part in the race.
     **/
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Gets the list of boats participating in the race.
     * @return competitors the list of boats taking part in the race.
     **/
    public ArrayList<Boat> getCompetitors() {
        return competitors;
    }

    /**
     * Sets the list of boats participating in the race.
     * @param competitors is the list of boats taking part in the race.
     **/
    public void setCompetitors(ArrayList<Boat> competitors) {
        this.competitors = competitors;
    }

    /**
     * Gets date of the race.
     * @return the race day date.
     **/
    public LocalDate getRaceDay() {return raceDay;}

    /**
     * Sets the date of the race.
     * @param raceDay is the date on which the race is taking place.
     **/
    public void setRaceDay(LocalDate raceDay) {this.raceDay = raceDay;}
}
