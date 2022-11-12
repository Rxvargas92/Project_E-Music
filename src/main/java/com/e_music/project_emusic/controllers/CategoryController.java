package com.e_music.project_emusic.controllers;


import com.e_music.project_emusic.entities.Category;
import com.e_music.project_emusic.services.ServiceCategory;
import com.e_music.project_emusic.services.ServiceCategoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping(path = "/categories")
public class CategoryController extends BaseControllerImpl<Category, ServiceCategoryImpl> {

    @Autowired
    private ServiceCategory serviceCategory;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/crud/formCategory")
    public ModelAndView formCategory(){
        ModelAndView modelAndView = new ModelAndView();
        try{
            modelAndView.setViewName("views/forms/formCategory");
            modelAndView.addObject("category", new Category());
        }catch (Exception e){
            log.info(e.getMessage(), e);
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/crud/formCategory")
    public ModelAndView saveCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        try {
            category = serviceCategory.saveOne(category);
            redirectAttributes.addFlashAttribute("message", "Saved Correctly!");
            redirectAttributes.addFlashAttribute("class", "success");
            modelAndView.setViewName("redirect:/instruments/crud/instruments");
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }
}
