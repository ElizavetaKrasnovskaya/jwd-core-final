package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.strategy.FileStrategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MultilineFileStrategy implements FileStrategy {

    private final static MultilineFileStrategy INSTANCE = new MultilineFileStrategy();

    private MultilineFileStrategy(){}

    public static MultilineFileStrategy getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void read(String path) throws IOException {
        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (!line.contains("#"))
                    stringWork(line, count++);
            }
        } catch (IOException e) {
        }
    }

    private void stringWork(String line, int countCrew) {
        SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
        String[] list = line.split("[{},:;]");
        long distance = Long.parseLong(list[1]);
        Map<Role, Short> map = new HashMap<>();
        try {
            for (int i = 3, j = 4; i < 10; i += 2, j += 2) {
                Long roleID = Long.valueOf(list[i]);
                short count = Short.parseShort(list[j]);
                Role role = Role.resolveRoleById(roleID);
                map.put(role, count);
            }
            spaceshipService.createSpaceship(list[0], distance, map);
        } catch (UnknownEntityException e) {
            System.out.println(e.getMessage());
        }
    }
}
