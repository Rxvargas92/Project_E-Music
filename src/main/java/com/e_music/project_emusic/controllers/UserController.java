package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.MyUserDetails;
import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.services.ServiceUser;
import com.e_music.project_emusic.services.ServiceUserImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private ServiceUser serviceUser;

    @ModelAttribute("user")
    public MyUserDetails returnNewRegisterMyUserDetails(){
        return new MyUserDetails();
    }


    @GetMapping (value = "/register")
    public ModelAndView register( Model model) {
        ModelAndView modelAndView = new ModelAndView();
        try {

            modelAndView.setViewName("views/register.html");
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @GetMapping (value = "/login")
    public ModelAndView login( Model model) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/login.html");
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @GetMapping (value = "/admin_menu")
    public ModelAndView admin_menu( Model model) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/admin_menu.html");
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @GetMapping (value = "/about")
    public ModelAndView about( Model model) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/about.html");
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(serviceUser.findAll());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(serviceUser.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PostMapping
    public ResponseEntity<?> registerUserAccount(@ModelAttribute("user") MyUserDetails myUserDetails){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(serviceUser.save(myUserDetails));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody User user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(serviceUser.updateOne(user, id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(serviceUser.deleteById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
