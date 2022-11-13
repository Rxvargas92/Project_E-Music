package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.Instrument;
import com.e_music.project_emusic.services.*;
import com.e_music.project_emusic.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/addresses")
public class AddressController extends BaseControllerImpl<Address, ServiceAddressImpl>{

    @Autowired
    private ServiceAddress serviceAddress;

    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private ServiceInstrument serviceInstrument;

    @Autowired
    private ServiceCart serviceCart;

    /*@GetMapping(value = "/formAddress/{id}")
    public ModelAndView formAddress(@Valid @ModelAttribute("address") Address address,
                                    @PathVariable("id")long id,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes)*/

}
