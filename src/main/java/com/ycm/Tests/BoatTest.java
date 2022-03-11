package com.ycm.Tests;

import com.ycm.Classes.Boat;
import com.ycm.Classes.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoatTest {

    private String name = "boat";
    private int ID = 1;
    private double length = 100.0;
    private double boatStorage = 40000.0;
    private String owner = "Ciccio";

    @Test
    public void standardConstructorTest() {
        Boat boat = new Boat(name, length);
        assertAll(()-> assertEquals(boat.getName(), name),
                ()-> assertEquals(boat.getLength(), length));
    }

    @Test
    public void standardSetterTest() {
        Boat boat = new Boat();
        boat.setID(ID);
        boat.setName(name);
        boat.setLength(length);
        boat.setBoatStorage();
        boat.setOwner(owner);
        assertAll(()-> assertEquals(boat.getID(), ID),
                ()-> assertEquals(boat.getName(), name),
                () -> assertEquals(boat.getLength(), length),
                () -> assertEquals(boat.getBoatStorage(), boatStorage ),
                () -> assertEquals(boat.getOwner(), owner));
    }
}
