package com.e_music.project_emusic.config;

import com.e_music.project_emusic.entities.Rol;
import com.e_music.project_emusic.enums.RolName;
import com.e_music.project_emusic.services.ServiceRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class CreateRoles implements CommandLineRunner {

    @Autowired
    private ServiceRol serviceRol;

    @Override
    public void run(String... args) throws Exception{
        /*Rol roleAdmin = new Rol(RolName.ROLE_ADMIN);
        Rol roleUser = new Rol(RolName.ROLE_USER);
        serviceRol.saveOne(roleAdmin);
        serviceRol.saveOne(roleUser);*/
    }

}
