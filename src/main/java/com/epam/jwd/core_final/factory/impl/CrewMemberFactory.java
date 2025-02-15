package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    private CrewMemberFactory() {
    }

    private final static CrewMemberFactory INSTANCE = new CrewMemberFactory();

    public static CrewMemberFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public CrewMember create(Object... args) {
        return new CrewMember((String) args[1], (Role) args[0], (Rank) args[2]);
    }
}
