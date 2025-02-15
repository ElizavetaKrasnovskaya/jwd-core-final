package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.EntityDuplicateException;
import com.epam.jwd.core_final.exception.FreeCrewMemberAbsentException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {

    private final static CrewServiceImpl INSTANCE = new CrewServiceImpl();

    private CrewServiceImpl() {
    }

    public static CrewServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return (List<CrewMember>) NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> crewMembers = findAllCrewMembers();
        CrewMemberCriteria crewMemberCriteria = (CrewMemberCriteria) criteria;
        return crewMembers.stream().filter(crewMember -> (
                (crewMember.getName().equals(crewMemberCriteria.getName()) || crewMemberCriteria.getName() == null)
                        && (crewMember.getRank() == crewMemberCriteria.getRank() || crewMemberCriteria.getRank() == null)
                        && (crewMember.getReadyForNextMissions() == crewMemberCriteria.getReadyForNextMissions() || crewMemberCriteria.getReadyForNextMissions() == null)
                        && (crewMember.getRole() == crewMemberCriteria.getRole() || crewMemberCriteria.getRole() == null)
        )).collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        List<CrewMember> crewMembers = findAllCrewMembers();
        CrewMemberCriteria crewMemberCriteria = (CrewMemberCriteria) criteria;
        return crewMembers.stream().filter(crewMember -> (
                (crewMemberCriteria.getName() == null || crewMember.getName().equals(crewMemberCriteria.getName()))
                        && (crewMemberCriteria.getRank() == null || crewMember.getRank() == crewMemberCriteria.getRank())
                        && (crewMemberCriteria.getReadyForNextMissions() == null || crewMember.getReadyForNextMissions() == crewMemberCriteria.getReadyForNextMissions())
                        && (crewMemberCriteria.getRole() == null || crewMember.getRole() == crewMemberCriteria.getRole())
        )).findFirst();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember oldCrewMember, CrewMember updatedCrewMember) {

        oldCrewMember.setRank(updatedCrewMember.getRank());
        oldCrewMember.setRole(updatedCrewMember.getRole());

        return oldCrewMember;
    }

    @Override
    public void printAllCrewMembers() {
        List<CrewMember> crewMembers = findAllCrewMembers();
        AtomicInteger i = new AtomicInteger();
        crewMembers.stream()
                .map(crewMember -> (i.incrementAndGet()) + ". " + crewMember.toString())
                .forEachOrdered(System.out::println);
    }

    @Override
    public void assignRandomCrewMembersOnMission(FlightMission mission) throws RuntimeException {
        Spaceship spaceship = mission.getAssignedSpaceship();

        Map<Role, Short> crew = spaceship.getCrew();
        for (Role value : Role.values()) {
            int bound = crew.get(value);
            for (int i1 = 0; i1 < bound; i1++) {
                Optional<CrewMember> crewMemberByCriteria = findCrewMemberByCriteria(
                        new CrewMemberCriteria.Builder() {{
                            role(value);
                            isReadyForNextMissions(true);
                        }}.build());
                if(!crewMemberByCriteria.isPresent()) {
                    throw new FreeCrewMemberAbsentException("There is no available crewmember for mission");
                }
                crewMemberByCriteria.ifPresent(member -> {
                    member.setReadyForNextMissions(false);
                    mission.addCrew(member);
                });
            }
        }

    }

    @Override
    public CrewMember createCrewMember(Role role, String name, Rank rank) throws RuntimeException, EntityDuplicateException {
        CrewMember crewMember = CrewMemberFactory.getInstance().create(role, name, rank);
        Collection<CrewMember> crewMembers = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
        crewMembers.add(crewMember);
        return crewMember;
    }

    @Override
    public CrewMember createTemporaryCrewMember(Role role, Rank rank) throws RuntimeException {
        return CrewMemberFactory.getInstance().create(role, "", rank);
    }
}
