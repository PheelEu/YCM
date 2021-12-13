package com.ycm.Classes;

import java.io.Serializable;
import java.util.ArrayList;

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

    ArrayList<Boat> competitors = new ArrayList<Boat>();

    public Race(String name, double cost, ArrayList<Boat> competitors){
        this.name = name;
        this.cost = cost;
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
}
