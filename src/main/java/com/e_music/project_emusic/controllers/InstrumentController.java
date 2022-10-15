package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.Instrument;
import com.e_music.project_emusic.services.ServiceCategoryImpl;
import com.e_music.project_emusic.services.ServiceInstrumentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class InstrumentController {

    @Autowired
    private ServiceInstrumentImpl serviceInstrument;

    @Autowired
    private ServiceCategoryImpl serviceCategory;

    @GetMapping ( value = "/start" )
    public String start( Model model ) {
        try {
            List< Instrument > games = this.serviceInstrument.findAllByActive( );
            model.addAttribute( "games", games );
            return "views/start";
        } catch ( Exception e ) {
            model.addAttribute( "error", e.getMessage( ) );
            return "error";
        }
    }
}
