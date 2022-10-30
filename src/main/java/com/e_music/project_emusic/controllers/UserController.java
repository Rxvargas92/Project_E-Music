package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.MyUserDetails;
import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.services.ServiceUser;
import com.e_music.project_emusic.services.ServiceUserImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/users")
public class UserController extends BaseControllerImpl<User, ServiceUserImpl>{

    @Autowired
    private ServiceUser serviceUser;

    @ModelAttribute("user")
    public MyUserDetails returnNewRegisterMyUserDetails(){
        return new MyUserDetails();
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") MyUserDetails myUserDetails){
        serviceUser.save(myUserDetails);
        return "redirect:/register?success";
    }

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

    @GetMapping (value = "/login")
    public ModelAndView login( Model model) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/login.html");
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @GetMapping (value = "/admin_menu")
    public ModelAndView admin_menu( Model model) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/admin_menu.html");
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @GetMapping (value = "/about")
    public ModelAndView about( Model model) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/about.html");
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }


}
