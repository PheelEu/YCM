package com.ycm.Classes;

import java.io.Serializable;

/**
 * The{@code Employee} class defines:
 *
 * All the variables that describe an Employee.
 * All the methods to set the attributes of an Employee.
 *
 *@author Filippo Euclidi
 *@author Matteo Angeloni
 **/

public class Employee extends Person implements Serializable {
    private final static long serialVersionUID = 1L;

    /**
     * This is the class constructor to initialize an Employee
     *
     * @param username it's the username of the employee
     * @param password it's the password of the employee
     * @param name it's the name of the employee
     * @param surname it's the surname of the employee
     * @param address it's the address of the employee
     * @param FC it's the Fiscal Code of the employee
     * @param logged it's true if the employee is logged in, false if logged out
     *
     **/
    public Employee(String username, String password, String name, String surname, String address, String FC, boolean logged){
        super(username,password,name,surname,address, FC, logged);
    }
}
