package com.ycm.Classes;

import java.io.Serial;
import java.io.Serializable;

/**
 * The{@code Member} class defines:
 *
 * All the variables that describe a Member.
 * All the methods to set the attributes of a Member.
 *
 *@author Filippo Euclidi
 *@author Matteo Angeloni
 **/

public final class Member extends Person implements Serializable {
    @Serial
    private  static final long serialVersionUID = 1L;

    /**
     *Class Constructor (empty)
     **/
    public Member(){}

    /**
     * This is the class constructor to initialize a Member
     *
     * @param username it's the username of the member
     * @param password it's the password of the member
     * @param name it's the name of the member
     * @param surname it's the surname of the member
     * @param address it's the address of the member
     * @param FC it's the Fiscal Code of the member
     * @param logged it's true if the member is logged in, false if logged out
     *
     **/
    public Member(String username, String password, String name, String surname, String address, String FC, boolean logged){
        super(username,password,name,surname,address,FC,logged);
    }
}
