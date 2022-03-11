package com.ycm.Tests;

import com.ycm.Classes.Club;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClubTest {

    private String name = "club";
    private String address = "somewhere";

    @Test
    public void standardConstructorTest() {
        Club club = new Club(name, address);
        assertAll(() -> assertEquals(club.getName(), name),
                () -> assertEquals(club.getAddress(), address));
    }

    @Test
    public void standardSetterTest() {
        Club club = new Club();
        club.setName(name);
        club.setAddress(address);
        assertAll(() -> assertEquals(club.getName(), name),
                () -> assertEquals(club.getAddress(), address));
    }

}
