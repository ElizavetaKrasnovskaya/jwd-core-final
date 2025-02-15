package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SpaceshipTest {

    @Test
    public void setCrewTest() throws InvalidStateException {
        Spaceship spaceship = SpaceshipServiceImpl.getInstance().createSpaceship("Test", 10000L, null);
        Map<Role, Short> crew = new HashMap<>();
        crew.put(Role.MISSION_SPECIALIST, (short) 2);
        spaceship.setCrew(crew);
        Assert.assertEquals(spaceship.getCrew(), crew);
    }

    @Test
    public void setFlightDistanceTest() throws InvalidStateException {
        Spaceship spaceship = SpaceshipServiceImpl.getInstance().createSpaceship("Test", null, null);
        spaceship.setFlightDistance(1000L);
        long expected = spaceship.getFlightDistance();
        Assert.assertEquals(expected, 1000L);
    }

    @Test
    public void setReadyForNextMissionsTest() throws InvalidStateException {
        Spaceship spaceship = SpaceshipServiceImpl.getInstance().createSpaceship("Test", null, null);
        spaceship.setReadyForNextMissions(true);
        Assert.assertEquals(spaceship.getReadyForNextMissions(), true);
    }

    @Test
    public void toStringTest() throws InvalidStateException {
        Spaceship spaceship = SpaceshipServiceImpl.getInstance().createSpaceship("Test", 1000L, null);
        String expected = "Spaceship name = Test, crew = null, flightDistance = 1000, isReadyForNextMissions = true";
        Assert.assertEquals(expected, spaceship.toString());
    }

}
