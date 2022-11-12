package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.Address;
import com.e_music.project_emusic.entities.Instrument;
import com.e_music.project_emusic.entities.Rol;
import com.e_music.project_emusic.enums.RolName;
import com.e_music.project_emusic.security.service.MyUserDetails;
import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.services.ServiceAddress;
import com.e_music.project_emusic.services.ServiceRol;
import com.e_music.project_emusic.services.ServiceUser;
import com.e_music.project_emusic.services.ServiceUserImpl;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/users")
public class UserController extends BaseControllerImpl<User, ServiceUserImpl>{

    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private ServiceRol serviceRol;

    @Autowired
    private ServiceAddress serviceAddress;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping (value = "/register")
    public ModelAndView registerForm() {
        ModelAndView modelAndView = new ModelAndView();
        try{
            modelAndView.setViewName("views/forms/register");
            modelAndView.addObject("user", new User());
            modelAndView.addObject("address", new Address());
        }catch (Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @PostMapping(value = "/register")
    public ModelAndView register(@Valid @ModelAttribute("user") User user,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        try{
            modelAndView.addObject("user", user);
            if (result.hasErrors()){
                modelAndView.setViewName("/views/forms/register");
                return modelAndView;
            }
            if(serviceUser.existsByEmail(user.getEmail())){
                modelAndView.setViewName("/views/forms/register");
                redirectAttributes.addFlashAttribute("message", "that email is already registered ");
                return modelAndView;
            }
            Rol rolUser = serviceRol.getByRolName(RolName.ROLE_USER).get();
            Set<Rol> rols = new HashSet<>();
            rols.add(rolUser);
            user.setRoles(rols);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            serviceUser.saveOne(user);
            redirectAttributes.addFlashAttribute("message", "Register Successfully!");
            redirectAttributes.addFlashAttribute("class", "success");
            modelAndView.setViewName("redirect:/user/login");
        }catch (Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @GetMapping (value = "/login")
    public ModelAndView login( Model model) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/forms/login");
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
