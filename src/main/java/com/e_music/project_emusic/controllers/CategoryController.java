package com.e_music.project_emusic.controllers;


import com.e_music.project_emusic.entities.Category;
import com.e_music.project_emusic.services.ServiceCategoryImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/categories")
public class CategoryController extends BaseControllerImpl<Category, ServiceCategoryImpl> {
}
