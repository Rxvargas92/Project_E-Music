package com.e_music.project_emusic.controllers.controllersViews;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllersHtmls {

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    //@GetMapping(value = "/login")
    //public String login(){
    //    return "views/login";
    //}

}
