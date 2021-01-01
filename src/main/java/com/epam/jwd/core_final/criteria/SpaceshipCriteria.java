package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Spaceship;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {

    private Long flightDistance;
    private Boolean isReadyForNextMissions;

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public static class Builder extends Criteria.Builder{
        private Long flightDistance;
        private Boolean isReadyForNextMissions;

        public Builder flightDistance(Long flightDistance){
            this.flightDistance = flightDistance;
            return this;
        }

        public Builder isReadyForNextMissions(Boolean isReadyForNextMissions){
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }
        public SpaceshipCriteria build(){
            return new SpaceshipCriteria(this);
        }
    }

    private SpaceshipCriteria(Builder builder){
        super(builder);
        flightDistance = builder.flightDistance;
        isReadyForNextMissions = builder.isReadyForNextMissions;
    }
}
