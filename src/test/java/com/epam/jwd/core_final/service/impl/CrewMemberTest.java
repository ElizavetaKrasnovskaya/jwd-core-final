package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.junit.Assert;
import org.junit.Test;

public class CrewMemberTest {

    @Test
    public void setRankTest() throws InvalidStateException {
        CrewMember crewMember = CrewServiceImpl.getInstance().createCrewMember(Role.COMMANDER, "Test", Rank.CAPTAIN);
        crewMember.setRank(Rank.CAPTAIN);
        Assert.assertEquals(crewMember.getRank(), Rank.CAPTAIN);
    }

    @Test
    public void setRoleTest() throws InvalidStateException {
        CrewMember crewMember = CrewServiceImpl.getInstance().createCrewMember(Role.COMMANDER, "Test", Rank.CAPTAIN);
        crewMember.setRole(Role.COMMANDER);
        Assert.assertEquals(crewMember.getRole(), Role.COMMANDER);
    }

    @Test
    public void setReadyForNextMissionsTest() throws InvalidStateException {
        CrewMember crewMember = CrewServiceImpl.getInstance().createCrewMember(Role.COMMANDER, "Test", Rank.CAPTAIN);
        crewMember.setReadyForNextMissions(true);
        Assert.assertTrue(crewMember.getReadyForNextMissions());
    }

    @Test
    public void toStringTest() throws InvalidStateException {
        CrewMember crewMember = CrewServiceImpl.getInstance().createCrewMember(Role.COMMANDER, "Test", Rank.CAPTAIN);
        String expected = "CrewMember name = Test, id = 0, role = COMMANDER, rank = CAPTAIN, isReadyForNextMissions = true";
        Assert.assertEquals(expected, crewMember.toString());
    }


}
