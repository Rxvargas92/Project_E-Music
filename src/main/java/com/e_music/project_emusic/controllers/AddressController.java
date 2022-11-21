package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.Address;
import com.e_music.project_emusic.entities.Cart;
import com.e_music.project_emusic.entities.Instrument;
import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
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

//    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/formAddress/{id}")
    public ModelAndView formAddress(@PathVariable("id")long id,
                                    RedirectAttributes redirectAttributes,
                                    Authentication auth){
        ModelAndView modelAndView = new ModelAndView();
        try{
            modelAndView.setViewName("views/forms/formAddress");
            Instrument instrument = serviceInstrument.findById(id);
            User user = serviceUser.getUserAutenticated(auth);
            if (user.getAddress()==null){
                modelAndView.addObject("address", new Address());
            }else{
                modelAndView.addObject("address", serviceAddress.findById(user.getAddress().getId()));
            }
            modelAndView.addObject("instrument", instrument);

        }catch (Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;

    }
//    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/formAddress/{id}")
    public ModelAndView formAddressPost(@PathVariable("id")long id,
                                        @Valid @ModelAttribute("address") Address address,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes,
                                        Authentication auth){
        ModelAndView modelAndView = new ModelAndView();
        try{
            Instrument instrument = serviceInstrument.findById(id);
            modelAndView.addObject("instrument", instrument);
            modelAndView.addObject("address", address);
            if (result.hasErrors()){
                modelAndView.setViewName("views/forms/formAddress");
                return modelAndView;
            }
            Cart cart = new Cart();
            User user = serviceUser.findById(serviceUser.getUserAutenticated(auth).getId());
            if(user.getAddress()==null){
                serviceAddress.saveOne(address);
                List<Address> addresses = serviceAddress.findAll();
                address = serviceAddress.findById(addresses.get(addresses.size()-1).getId());
                user.setAddress(address);
                serviceUser.updateOne(user, user.getId());
            }else{
                serviceAddress.updateOne(address, user.getAddress().getId());
            }
            instrument.setStock(instrument.getStock()-1);
            serviceInstrument.updateOne(instrument, instrument.getId());
            cart.setUser(user);
            cart.setInstrument(instrument);
            cart.setPayment_method("Transfer");
            cart.setTotal_price(instrument.getPrice());
            serviceCart.saveOne(cart);
            modelAndView.setViewName("redirect:/carts/confirmedPurchase");
        }catch (Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;

    }





}
