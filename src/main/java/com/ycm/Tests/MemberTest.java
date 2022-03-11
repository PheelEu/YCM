package com.ycm.Tests;

import com.ycm.Classes.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {
    private String username = "c";
    private String password = "c";
    private String name = "c";
    private String surname = "c";
    private String address = "c";
    private String fc = "C";
    private boolean logged = false;

    @Test
    public void standardConstructorTest() {
        Member member = new Member(username,password, name, surname, address, fc, logged);
        assertAll(()-> assertEquals(member.getUsername(), username),
                ()-> assertEquals(member.getPassword(), password),
                () -> assertEquals(member.getName(), name),
                () -> assertEquals(member.getSurname(), surname),
                () -> assertEquals(member.getAddress(), address),
                () -> assertEquals(member.getFC(), fc),
                () -> assertEquals(member.isLogged(), logged));
    }

    @Test
    public void standardSetterTest() {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);
        member.setName(name);
        member.setSurname(surname);
        member.setAddress(address);
        member.setFC(fc);
        member.setLogged(true);
        assertAll(()-> assertEquals(member.getUsername(), username),
                ()-> assertEquals(member.getPassword(), password),
                () -> assertEquals(member.getName(), name),
                () -> assertEquals(member.getSurname(), surname),
                () -> assertEquals(member.getAddress(), address),
                () -> assertEquals(member.getFC(), fc),
                () -> assertNotEquals(false, member.isLogged()));
    }


}
