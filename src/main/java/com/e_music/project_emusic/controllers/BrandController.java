package com.e_music.project_emusic.controllers;


import com.e_music.project_emusic.entities.Brand;
import com.e_music.project_emusic.services.ServiceBrandImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/brands")
public class BrandController extends BaseControllerImpl<Brand, ServiceBrandImpl> {
}
