package com.ycm.Tests;

import com.ycm.Classes.Employee;
import com.ycm.Classes.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    private String username = "e";
    private String password = "e";
    private String name = "e";
    private String surname = "e";
    private String address = "e";
    private String fc = "E";
    private boolean logged = false;

    @Test
    public void standardConstructorTest() {
        Employee employee = new Employee(username,password, name, surname, address, fc, logged);
        assertAll(()-> assertEquals(employee.getUsername(), username),
                ()-> assertEquals(employee.getPassword(), password),
                () -> assertEquals(employee.getName(), name),
                () -> assertEquals(employee.getSurname(), surname),
                () -> assertEquals(employee.getAddress(), address),
                () -> assertEquals(employee.getFC(), fc),
                () -> assertEquals(employee.isLogged(), logged));
    }

    @Test
    public void standardSetterTest() {
        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setAddress(address);
        employee.setFC(fc);
        employee.setLogged(true);
        assertAll(()-> assertEquals(employee.getUsername(), username),
                ()-> assertEquals(employee.getPassword(), password),
                () -> assertEquals(employee.getName(), name),
                () -> assertEquals(employee.getSurname(), surname),
                () -> assertEquals(employee.getAddress(), address),
                () -> assertEquals(employee.getFC(), fc),
                () -> assertNotEquals(false, employee.isLogged()));
    }


}
