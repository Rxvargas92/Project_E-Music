package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.Instrument;
import com.e_music.project_emusic.services.ServiceCategoryImpl;
import com.e_music.project_emusic.services.ServiceInstrumentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/emusic/instruments")
public class InstrumentController extends BaseControllerImpl< Instrument, ServiceInstrumentImpl >{

}
