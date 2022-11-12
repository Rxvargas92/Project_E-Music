package com.e_music.project_emusic.config;

import com.e_music.project_emusic.entities.Rol;
import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.enums.RolName;
import com.e_music.project_emusic.services.ServiceRol;
import com.e_music.project_emusic.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.auditing.config.AuditingBeanDefinitionRegistrarSupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CreateAdmin implements CommandLineRunner {

    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ServiceRol serviceRol;

    @Override
    public void run(String... args) throws Exception {
        /*User user = new User();
        String passwordEncoded = passwordEncoder.encode("evelin");
        user.setPassword(passwordEncoded);
        user.setEmail("evelin@gmail.com");
        user.setDni(12312312);
        user.setName("Evelin");
        user.setLastName("Ortiz");
        //Rol rolAdmin = serviceRol.getByRolName(RolName.ROLE_ADMIN).get();
        Rol rolUser = serviceRol.getByRolName(RolName.ROLE_USER).get();
        Set<Rol> rols = new HashSet<>();
        //rols.add(rolAdmin);
        rols.add(rolUser);
        user.setRoles(rols);
        serviceUser.saveOne(user);*/
    }
}
