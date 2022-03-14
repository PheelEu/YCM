package com.ycm.Classes;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * The{@code Person} class defines:
 *
 * All the variables that describe a Person.
 * All the methods to set the attributes of a Person.
 *
 *@author Filippo Euclidi
 *@author Matteo Angeloni
 *
 **/

public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String FC;
    private boolean logged;

    /**
     *Class Constructor (empty)
     **/
    public Person(){}

    /**
     *Class Constructor for any person (could be both a Member or an Employee)
     *
     * @param username is the username of the person.
     * @param password is the password of the person.
     * @param name is the name of the person.
     * @param surname is the surname of the person.
     * @param address is the address of the person.
     * @param FC is the Fiscal Code of the person
     * @param logged tells whether the person is logged in or not.
     **/
    public Person(String username, String password, String name, String surname, String address, String FC, boolean logged){
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.FC = FC;
        this.logged = logged;
    }

    /**
     * Gets the username of the Person.
     * @return username of the Person.
     **/
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the Person.
     * @param username is the username to set for the Person.
     **/
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the Person.
     * @return password of the Person.
     **/
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the Person.
     * @param password the password  to set for the Person.
     **/
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the name of the Person.
     * @return name of the Person.
     **/
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Person.
     * @param name the name to set for the Person.
     **/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the surname of the Person.
     * @return surname of the Person.
     **/
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the Person.
     * @param surname the surname to set for the Person.
     **/
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the address of the Person.
     * @return address of the Person.
     **/
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the Person.
     * @param address the address to set for the Person.
     **/
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the Fiscal code of the Person.
     * @return Fiscal code of the Person.
     **/
    public String getFC() {
        return FC;
    }

    /**
     * Sets the Fiscal Code of the Person.
     * @param FC the fiscal code to set for the Person.
     **/
    public void setFC(String FC) {
        this.FC = FC;
    }

    /**
     * This method tells whether a Person has done the login or not.
     * @return True if is logged, False if is not logged
     **/
    public boolean isLogged() {
        return logged;
    }

    /**
     * This method sets the boolean logged to true when a Person logs in, False if the person logs out.
     * @param logged set to true if a person is logged in, false if is logged out.
     */
    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
