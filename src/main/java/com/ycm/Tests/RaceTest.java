package com.ycm.Tests;

import com.ycm.Classes.Boat;
import com.ycm.Classes.Payment;
import com.ycm.Classes.Race;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.ycm.Classes.Club.getToday;
import static org.junit.jupiter.api.Assertions.*;

public class RaceTest {

    private String name = "race";
    private double cost = 100.0;
    private LocalDate raceDay = getToday();

    @Test
    public void standardConstructorTest() {
        Race race = new Race(name, cost, raceDay);
        assertAll(()-> assertEquals(race.getName(), name),
                ()-> assertEquals(race.getCost(), cost),
                () -> assertEquals(race.getRaceDay(), raceDay));
    }

    @Test
    public void standardSetterTest() {
        Race race = new Race();
        ArrayList<Boat> competitors = new ArrayList<Boat>();
        Boat b0 = new Boat("barca0", 10);
        Boat b1 = new Boat("barca1",11);
        Boat b2 = new Boat("barca2", 12);
        competitors.add(b0);
        competitors.add(b1);
        competitors.add(b2);
        race.setName(name);
        race.setRaceDay(raceDay);
        race.setCost(cost);
        race.setCompetitors(competitors);
        assertAll(()-> assertEquals(race.getName(), name),
                ()-> assertEquals(race.getRaceDay(), raceDay),
                () -> assertEquals(race.getCost(), cost),
                () -> assertEquals(race.getCompetitors(), competitors));
    }
}
