package com.ycm.Classes;

import java.io.Serializable;
import java.util.*;

/**
 * The class {@code Club} defines the Club and all of its functions.
 *
 * @author Euclidi Filippo
 * @author Angeloni Matteo
 **/

public class Club implements Serializable {
    private static Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
    private static Date today = calendar.getTime();
    private String name;
    private String address;

    private static final long serialVersionUID = 1L;

    /**
     * This is the Club class constructor.
     *
     * @param name it's the name of the club.
     * @param address it's the address of the club.
     **/
    public Club(String name, String address){
        this.name = name;
        this.address = address;
    }

    /**
     * This method gets today's date.
     * @return today's date.
     **/
    public static Date getToday() {
        return today;
    }
    /**
     * Gets the name of the club.
     * @return the name of the club.
     **/
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the club.
     * @param name it's the name to set for the club.
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the address of the club.
     * @return the address of the club.
     **/
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the club.
     * @param address it's the address to set for the club.
     **/
    public void setAddress(String address) {
        this.address = address;
    }
}
