package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {

    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public static class Builder extends Criteria.Builder{
        private Role role;
        private Rank rank;
        private Boolean isReadyForNextMissions;

        public Builder rank(Rank rank){
            this.rank = rank;
            return this;
        }

        public Builder role(Role role){
            this.role = role;
            return this;
        }

        public Builder isReadyForNextMissions(Boolean isReadyForNextMissions){
            this.isReadyForNextMissions = isReadyForNextMissions;
            return this;
        }

        public CrewMemberCriteria build(){
            return new CrewMemberCriteria(this);
        }
    }

    private CrewMemberCriteria(Builder builder) {
        super(builder);
        rank = builder.rank;
        role = builder.role;
        isReadyForNextMissions = builder.isReadyForNextMissions;
    }

}
