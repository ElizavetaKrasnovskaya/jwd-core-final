package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.strategy.impl.CrewFile;
import com.epam.jwd.core_final.strategy.impl.SpaceshipFile;
import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {
    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private final Collection<Spaceship> missions = new ArrayList<>();

    private final static NassaContext INSTANCE = new NassaContext();

    private ApplicationProperties applicationProperties;

    private final String separator = FileSystems.getDefault().getSeparator();

    private String crewPath;
    private String spaceshipPath;

    private NassaContext(){}

    public static NassaContext getInstance(){
        return INSTANCE;
    }

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if(tClass.toString().equals(CrewMember.class.toString())){
            return (Collection<T>) crewMembers;
        }
        if(tClass.toString().equals(Spaceship.class.toString())){
            return (Collection<T>) spaceships;
        }
        if(tClass.toString().equals(FlightMission.class.toString())){
            return (Collection<T>) missions;
        }
        throw new UnknownEntityException("Unknown base entity (only CrewMember, FlightMission or Spaceship collections)");
    }

    /**
     * You have to read input files, populate collections
     * @throws InvalidStateException
     */

    public ApplicationProperties getApplicationProperties() {
        return applicationProperties;
    }

    public String getCrewPath(){
        return crewPath;
    }

    public String getSpaceshipPath(){
        return spaceshipPath;
    }

    @Override
    public void init() throws InvalidStateException {
        applicationProperties = PropertyReaderUtil.loadProperties();
        crewPath = "src" + separator + "main" + separator + "resources" + separator
                + applicationProperties.getInputRootDir() + separator + applicationProperties.getCrewFileName();
        spaceshipPath = "src" + separator + "main" + separator + "resources" + separator
                + applicationProperties.getInputRootDir() + separator + applicationProperties.getSpaceshipsFileName();
        try{
            new CrewFile(crewPath).read();
            new SpaceshipFile(spaceshipPath).read();
        }catch (IOException ex){
            ex.printStackTrace();
            throw new InvalidStateException();
        }
    }
}
