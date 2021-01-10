package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.strategy.FileStrategy;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class InlineFileStrategy implements FileStrategy {

    private final static InlineFileStrategy INSTANCE = new InlineFileStrategy();

    private InlineFileStrategy(){}

    public static InlineFileStrategy getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void read(String path) throws IOException {

        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("#")) {
                    stringWork(line);
                }
            }
        } catch (IOException e) {

        }
    }

    private void stringWork(String line) {
        CrewService crewService = CrewServiceImpl.getInstance();
        String[] list = line.split("[;,]");
        int count = 0;
        for (int i = 0; i < list.length; i += 3) {
            Long roleID = Long.valueOf(list[i]);
            Long rankID = Long.valueOf(list[i + 2]);
            try {
                Role role = Role.resolveRoleById(roleID);
                Rank rank = Rank.resolveRankById(rankID);
                crewService.createCrewMember(role, list[i + 1], rank);
            } catch (UnknownEntityException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
