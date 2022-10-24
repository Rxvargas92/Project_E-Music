package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.Cart;
import com.e_music.project_emusic.services.ServiceCartImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/carts")
public class CartController extends BaseControllerImpl<Cart, ServiceCartImpl> {

    @GetMapping (value = "/cart")
    public ModelAndView cart( Model model) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/cart.html");
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

}
