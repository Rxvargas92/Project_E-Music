package com.e_music.project_emusic.config;

import com.e_music.project_emusic.services.ServiceAddress;
import com.e_music.project_emusic.services.ServiceCart;
import com.e_music.project_emusic.services.ServiceRol;
import com.e_music.project_emusic.services.ServiceUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateAdmin implements CommandLineRunner {

    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private ServiceAddress serviceAddress;

    @Autowired
    private ServiceCart serviceCart;

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

        /*User user = serviceUser.findById(2L);
        log.info(user.getEmail());
        Address address = serviceAddress.findById(4L);
        log.info(address.getStreet());
        log.info(String.valueOf(address.getNumber()));
        user.setAddress(address);
        user.setDni(40123123);
        user.setName("Alejo");
        user.setLastName("Araya");
        log.info(String.valueOf(user.getAddress().getId()));
        serviceUser.updateOne(user, 2L);*/

        //user.setAddress(address);
        //serviceUser.updateOne(user, 2L);

        /*List<Address> addresses = serviceAddress.findAll();
        for (Address address: addresses) {
            log.info(address.getStreet());
            log.info(String.valueOf(address.getNumber()));
            log.info(address.getLocation());
            log.info(address.getProvince());
            log.info(String.valueOf(address.getPostalCode()));
            log.info("---------------------------------------");
        }*/

    }
}
