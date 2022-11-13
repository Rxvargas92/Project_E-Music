package com.e_music.project_emusic.controllers;


import com.e_music.project_emusic.entities.Brand;
import com.e_music.project_emusic.services.ServiceBrand;
import com.e_music.project_emusic.services.ServiceBrandImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping(path = "/brands")
public class BrandController extends BaseControllerImpl<Brand, ServiceBrandImpl> {

    @Autowired
    private ServiceBrand serviceBrand;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/crud/formBrand")
    public ModelAndView formCategory(){
        ModelAndView modelAndView = new ModelAndView();
        try{
            modelAndView.setViewName("views/forms/formBrand");
            modelAndView.addObject("brand", new Brand());
        }catch (Exception e){
            log.info(e.getMessage(), e);
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/crud/formBrand")
    public ModelAndView saveCategory(@ModelAttribute("brand") Brand brand, RedirectAttributes redirectAttributes) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        try {
            if(serviceBrand.existsByName(brand.getName())){
                redirectAttributes.addFlashAttribute("message", "that brand is already saved");
                redirectAttributes.addFlashAttribute("class", "warning");
                modelAndView.setViewName("redirect:/brands/crud/formBrand");
                return modelAndView;
            }else {
                brand = serviceBrand.saveOne(brand);
                redirectAttributes.addFlashAttribute("message", "Saved Correctly!");
                redirectAttributes.addFlashAttribute("class", "success");
                modelAndView.setViewName("redirect:/instruments/crud/instruments");
            }
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

}
