package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.Brand;
import com.e_music.project_emusic.entities.Category;
import com.e_music.project_emusic.entities.Instrument;
import com.e_music.project_emusic.services.ServiceBrand;
import com.e_music.project_emusic.services.ServiceCategory;
import com.e_music.project_emusic.services.ServiceInstrument;
import com.e_music.project_emusic.services.ServiceInstrumentImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/instruments")
public class InstrumentController extends BaseControllerImpl< Instrument, ServiceInstrumentImpl >{

    @Autowired
    private ServiceInstrument serviceInstrument;
    @Autowired
    private ServiceCategory serviceCategory;

    @Autowired
    private ServiceBrand serviceBrand;

    @GetMapping(value = "/listAll")
    public ModelAndView listAllInstrument() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("/views/listInstruments");
            List<Instrument> instruments = serviceInstrument.findAll();
            List<Instrument> instrumentsActive = new ArrayList<>();
            for (Instrument instrument : instruments) {
                if(instrument.isActive()){
                    instrumentsActive.add(instrument);
                }
            }
            modelAndView.addObject("instruments", instrumentsActive);
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @GetMapping(value = "/list/{id}")
    public ModelAndView listInstrument(@PathVariable("id") long id ) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("/views/listInstruments.html");
            List<Instrument> instruments = serviceCategory.findById(id).getInstruments();
            List<Instrument> instrumentsActive = new ArrayList<>();
            for (Instrument instrument : instruments) {
                if(instrument.isActive()){
                    instrumentsActive.add(instrument);
                }
            }
            modelAndView.addObject("instruments", instrumentsActive);
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @GetMapping (value = "/details/{id}")
    public ModelAndView details(@PathVariable("id") long id ){
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/detailsCrud");
            Instrument instrument = this.serviceInstrument.findById(id);
            modelAndView.addObject("instrument", instrument);
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/crud/viewInstrument/{id}")
    public ModelAndView detailsCrud(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        try{
            modelAndView.setViewName("views/detailsCrud");
            Instrument instrument = serviceInstrument.findById(id);
            modelAndView.addObject("instrument", instrument);
        }catch (Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping (value = "/crud/instruments")
    public ModelAndView crud(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/crud");
            modelAndView.addObject("instruments", serviceInstrument.findAll());
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/crud/formInstrument")
    public ModelAndView saveInstrument(){
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/forms/add_instrument");
            Instrument instrument = new Instrument();
            modelAndView.addObject("instrument", instrument);
            List<Category> categories = serviceCategory.findAll();
            categories.sort(Comparator.comparing(Category::getName));
            List<Brand> brands = serviceBrand.findAll();
            brands.sort(Comparator.comparing(Brand::getName));
            modelAndView.addObject("categories", categories);
            modelAndView.addObject("brands", brands);
        }catch(Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/crud/formInstrument")
    public ModelAndView saveInstrument(
            @RequestParam("file") MultipartFile archive,
            @Valid @ModelAttribute("instrument") Instrument instrument,
            BindingResult result,
            RedirectAttributes redirectAttributes)
    {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject("instrument", instrument);
            modelAndView.addObject("categories", serviceCategory.findAll());
            modelAndView.addObject("brands", serviceBrand.findAll());
            if (result.hasErrors()){
                modelAndView.setViewName("views/forms/add_instrument");
                return modelAndView;
            }
            String route = "C://Ecommerce/images";
            int index = archive.getOriginalFilename().indexOf(".");
            String extension = "";
            extension = "."+archive.getOriginalFilename().substring(index+1);
            String photoName = Calendar.getInstance().getTimeInMillis()+extension;
            Path absoluteRoute = Paths.get(route+"//"+photoName);
            if (archive.isEmpty()){
                modelAndView.setViewName("views/forms/add_instrument");
                modelAndView.addObject("imageErrorMsg", "Image is required");
                return modelAndView;
            }else{
                if (!this.extensionValider(archive)){
                    modelAndView.setViewName("views/forms/add_instrument");
                    modelAndView.addObject("imageErrorMsg", "Extension invalid");
                    return modelAndView;
                }
                if (archive.getSize() >= 15000000) {
                    modelAndView.setViewName("views/forms/add_instrument");
                    modelAndView.addObject("imageErrorMsg", "The file exceeds 15mb");
                    return modelAndView;
                }
                Files.write(absoluteRoute,archive.getBytes());
                instrument.setPathImage(photoName);
                this.serviceInstrument.saveOne(instrument);
                redirectAttributes.addFlashAttribute("message", "Saved Correctly!");
                redirectAttributes.addFlashAttribute("class", "success");
                modelAndView.setViewName("redirect:/instruments/crud/instruments");
            }
        }catch(Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/crud/formInstrument/{id}")
    public ModelAndView editInstrument(@PathVariable("id")long id){
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/forms/edit_instrument");
            List<Category> categories = serviceCategory.findAll();
            categories.sort(Comparator.comparing(Category::getName));
            List<Brand> brands = serviceBrand.findAll();
            brands.sort(Comparator.comparing(Brand::getName));
            modelAndView.addObject("categories", categories);
            modelAndView.addObject("brands", brands);
            modelAndView.addObject("instrument",this.serviceInstrument.findById(id));
        }catch(Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/crud/formInstrument/{id}")
    public ModelAndView editInstrument(
            @RequestParam(value = "file") MultipartFile archive,
            @Valid @ModelAttribute("instrument") Instrument instrument,
            BindingResult result,
            @PathVariable("id")long id,
            RedirectAttributes redirectAttributes)
    {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.addObject("instrument", instrument);
            modelAndView.addObject("categories", serviceCategory.findAll());
            modelAndView.addObject("brands", serviceBrand.findAll());
            if (result.hasErrors()){
                modelAndView.setViewName("views/forms/edit_instrument");
                return modelAndView;
            }
            String route = "C://Ecommerce/images";
            int index = archive.getOriginalFilename().indexOf(".");
            String extension = "";
            extension = "."+archive.getOriginalFilename().substring(index+1);
            String photoName = Calendar.getInstance().getTimeInMillis()+extension;
            Path absoluteRoute = Paths.get(route+"//"+instrument.getPathImage());
            if (archive.isEmpty()){
                modelAndView.setViewName("views/forms/edit_instrument");
                modelAndView.addObject("imageErrorMsg", "Image is required");
                return modelAndView;
            }else{
                if (!this.extensionValider(archive)){
                    modelAndView.setViewName("views/forms/add_instrument");
                    modelAndView.addObject("imageErrorMsg", "Extension invalid");
                    return modelAndView;
                }
                if (archive.getSize() >= 15000000) {
                    modelAndView.setViewName("views/forms/edit_instrument");
                    modelAndView.addObject("imageErrorMsg", "The file exceeds 15mb");
                    return modelAndView;
                }
                Files.write(absoluteRoute, archive.getBytes());
                this.serviceInstrument.updateOne(instrument, id);
                redirectAttributes.addFlashAttribute("message", "Edited Correctly!");
                redirectAttributes.addFlashAttribute("class", "success");
                modelAndView.setViewName("redirect:/instruments/crud/instruments");
            }
        }catch(Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/crud/delete/{id}")
    public ModelAndView deleteFormInstrument(@PathVariable("id")long id){
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/forms/formDelete");
            Instrument instrument = serviceInstrument.findById(id);
            modelAndView.addObject("instrument", instrument);
        }catch(Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/crud/delete/{id}")
    public ModelAndView deleteInstrument(@ModelAttribute("instrument") Instrument instrument, @PathVariable("id")long id, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView();
        try {
            instrument = serviceInstrument.findById(id);
            if (!(instrument==null)){
                this.serviceInstrument.deleteById(id);
            }
            redirectAttributes.addFlashAttribute("message", "Deleted Correctly!");
            redirectAttributes.addFlashAttribute("class", "success");
            modelAndView.setViewName("redirect:/instruments/crud/instruments");
        }catch(Exception e){
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }

    public boolean extensionValider(MultipartFile file){
        try{
            ImageIO.read(file.getInputStream()).toString();
            return true;
        }catch (Exception e){
            log.info(e.getMessage(),e) ;
            return false;
        }
    }

/*
    @GetMapping (value = "/crud/instruments")
    public ModelAndView crudPagination(Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            modelAndView.setViewName("views/crud");
            int page = 0; //default page number is 0 (yes it is weird)
            int size = 10; //default page size is 10
            if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
                page = Integer.parseInt(request.getParameter("page")) - 1;
            }
            if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
                size = Integer.parseInt(request.getParameter("size"));
            }

            modelAndView.addObject("instruments", serviceInstrument.findAll(PageRequest.of(page, size)));
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }*/
}
