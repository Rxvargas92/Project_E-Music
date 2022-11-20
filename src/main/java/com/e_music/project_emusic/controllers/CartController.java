package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.Address;
import com.e_music.project_emusic.entities.Cart;
import com.e_music.project_emusic.entities.DTOs.CartDTO;
import com.e_music.project_emusic.entities.Instrument;
import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.services.*;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/carts")
public class CartController extends BaseControllerImpl<Cart, ServiceCartImpl> {

    @Autowired
    private ServiceCart serviceCart;

    @Autowired
    private ServiceInstrument serviceInstrument;


    /*@PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/confirmPurchase")
    public ModelAndView confirmPurchase(){
        ModelAndView modelAndView = new ModelAndView();
        try{
            modelAndView.setViewName("views/forms/confirmPurchase");
            modelAndView.addObject("cartDTO", cartDTO);
        }catch (Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/confirmPurchase")
    public ModelAndView confirmPurchasePost(@ModelAttribute("cartDTO") CartDTO cartDTO,
                                            RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        try{
            cartDTO.getInstrument().setStock(cartDTO.getInstrument().getStock()-1);
            serviceInstrument.updateOne(cartDTO.getInstrument(), cartDTO.getInstrument().getId());
            ModelMapper modelMapper = new ModelMapper();
            Cart cart = new Cart();
            modelMapper.map(cartDTO, cart);
            serviceCart.saveOne(cart);
            modelAndView.setViewName("redirect:/carts/confirmedPurchase");
        }catch (Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }*/

//    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/confirmedPurchase")
    public ModelAndView confirmedPurchase(){
        ModelAndView modelAndView = new ModelAndView();
        try{
            modelAndView.setViewName("views/confirmedPurchase");
        }catch (Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }
}
