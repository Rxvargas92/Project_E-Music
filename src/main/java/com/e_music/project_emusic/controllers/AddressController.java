package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.services.ServiceAddressImpl;
import com.e_music.project_emusic.entities.Address;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/emusic/addresses")
public class AddressController extends BaseControllerImpl<Address, ServiceAddressImpl>{
}
