package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MissionService {

    List<FlightMission> findAllMissions();

    List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria);

    Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria);

    FlightMission updateMissionDetails(FlightMission oldFlightMission, FlightMission updatedFlightMission);

    FlightMission createMission(String name, LocalDateTime startDate, LocalDateTime endDate, Long distance);

    Double calculateMissionProgress(FlightMission flightMission);

    void missionStatusUpdate(FlightMission mission);

    void finishMission(FlightMission mission);

    void printAllMissions();

    FlightMission createTemporaryMission(LocalDateTime startDate, LocalDateTime endDate, Long distance);

    List<FlightMission> getMissionsWithCrewMember(CrewMember crewMember);
}
