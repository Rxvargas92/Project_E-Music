package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.services.ServiceUserImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/emusic/users")
public class UserController extends BaseControllerImpl<User, ServiceUserImpl>{

    @GetMapping (value = "/register")
    public ModelAndView register( Model model) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/register.html");
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

}
