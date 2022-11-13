package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.Category;
import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.security.service.MyUserDetails;
import com.e_music.project_emusic.services.ServiceCategory;
import com.e_music.project_emusic.services.ServiceUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("")
@CrossOrigin("localhost:9000")
public class IndexController {

    @Autowired
    private ServiceCategory serviceCategory;

    @GetMapping(value = {"", "/index"})
    public ModelAndView indexView(){
        ModelAndView modelAndView = new ModelAndView();
        try{
            modelAndView.setViewName("index");
            List<Category> categories = serviceCategory.findAll();
            categories.sort(Comparator.comparing(Category::getName));
            modelAndView.addObject("categories", categories);
        }catch (Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @GetMapping(value = "/forbidden")
    public ModelAndView forbidden(){
        ModelAndView modelAndView = new ModelAndView();
        try{
            modelAndView.setViewName("/forbidden");

        }catch (Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }


}
