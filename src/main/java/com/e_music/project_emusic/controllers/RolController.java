package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.Rol;
import com.e_music.project_emusic.services.ServiceRolImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/emusic/rols")
public class RolController extends BaseControllerImpl<Rol, ServiceRolImpl> {

}
