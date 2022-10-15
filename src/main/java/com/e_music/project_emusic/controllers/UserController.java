package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.services.ServiceUserImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/emusic/user")
public class UserController extends BaseControllerImpl<User, ServiceUserImpl>{

}
