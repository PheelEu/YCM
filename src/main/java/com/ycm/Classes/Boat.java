package com.ycm.Classes;

import java.io.Serializable;

/**
 * The{@code Boat} class defines:
 *
 * All the variables that describe a boat.
 * All the methods to set the attributes of a boat.
 *
 *@author Filippo Euclidi
 *@author Matteo Angeloni
 **/

public class Boat implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String ID;
    private double length;
    private double boatStorage;
    private int lastID = 0;

    /**
     *Class Constructor
     *
     * @param name is the name of the boat
     * @param length is the length of the boat.
     * @param boatStorage is the tax for the storage of the boat
     **/
    public Boat(String name, double length, double boatStorage){
        this.name = name;
        this.ID = String.valueOf(getID());;
        this.length = length;
        this.boatStorage = boatStorage;//TODO class payment method to implement
    }

    /**
     * Gets the name of the boat.
     * @return name of the boat.
     **/
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the boat.
     * @param name is the name to set for the boat.
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the ID of the boat.
     * @return the id of the boat.
     **/
    public int getID() {
        lastID++;
        return lastID;
    }

    /**
     * Sets the ID of the boat.
     * @param ID is the id to set for the boat.
     **/
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Gets the length of the boat.
     * @return length of the boat.
     **/
    public double getLength() {
        return length;
    }

    /**
     * Sets the length of the boat.
     * @param length is the length to set for the boat.
     **/
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Gets the boat storage tax for the specific boat.
     * @return storage tax of the boat.
     **/
    public double getBoatStorage() {
        return boatStorage;
    }
}
