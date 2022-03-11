package com.ycm.Tests;

import com.ycm.Classes.Member;
import com.ycm.Classes.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private String username = "c";
    private String password = "c";
    private String name = "c";
    private String surname = "c";
    private String address = "c";
    private String fc = "C";
    private boolean logged = false;

    @Test
    public void standardConstructorTest() {
        Person person = new Person(username,password, name, surname, address, fc, logged);
        assertAll(()-> assertEquals(person.getUsername(), username),
                ()-> assertEquals(person.getPassword(), password),
                () -> assertEquals(person.getName(), name),
                () -> assertEquals(person.getSurname(), surname),
                () -> assertEquals(person.getAddress(), address),
                () -> assertEquals(person.getFC(), fc),
                () -> assertEquals(person.isLogged(), logged));
    }

    @Test
    public void standardSetterTest() {
        Person person = new Person();
        person.setUsername(username);
        person.setPassword(password);
        person.setName(name);
        person.setSurname(surname);
        person.setAddress(address);
        person.setFC(fc);
        person.setLogged(true);
        assertAll(()-> assertEquals(person.getUsername(), username),
                ()-> assertEquals(person.getPassword(), password),
                () -> assertEquals(person.getName(), name),
                () -> assertEquals(person.getSurname(), surname),
                () -> assertEquals(person.getAddress(), address),
                () -> assertEquals(person.getFC(), fc),
                () -> assertNotEquals(false, person.isLogged()));
    }

}
