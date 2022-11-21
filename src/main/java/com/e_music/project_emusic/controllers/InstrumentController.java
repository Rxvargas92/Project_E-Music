package com.e_music.project_emusic.controllers;

import com.e_music.project_emusic.entities.Brand;
import com.e_music.project_emusic.entities.Category;
import com.e_music.project_emusic.entities.Instrument;
import com.e_music.project_emusic.entities.User;
import com.e_music.project_emusic.security.service.ServiceMyUserDetailsImpl;
import com.e_music.project_emusic.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private ServiceMyUserDetailsImpl serviceMyUserDetails;


    @GetMapping(value = "/listAllInstruments")
    public ModelAndView listAllInstrument(@RequestParam Map<String, Object> params) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

            PageRequest pageRequest = PageRequest.of(page,10);

            Page<Instrument> pageInstrument = serviceInstrument.findAllByActivePageable(pageRequest);

            int totalPage = pageInstrument.getTotalPages();
            if(totalPage > 0){
                List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
                modelAndView.addObject("pages", pages);
            }
            modelAndView.setViewName("views/listInstruments");
            modelAndView.addObject("instruments", pageInstrument.getContent());
            modelAndView.addObject("current", page + 1);
            modelAndView.addObject("next", page + 2);
            modelAndView.addObject("prev", page);
            modelAndView.addObject("last", totalPage);
        } catch (Exception e) {
            log.info(e.getMessage(),e) ;
            modelAndView.setViewName("error.html");
        }
        return modelAndView;
    }


    @GetMapping(value = "/list/{id}")
    public ModelAndView listInstrument(@PathVariable("id") Long id,
                                       @RequestParam Map<String, Object> params) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

            PageRequest pageRequest = PageRequest.of(page,10);

            Page<Instrument> pageInstrument = serviceInstrument.findAllByActiveAndCategory(id, pageRequest);

            int totalPage = pageInstrument.getTotalPages();
            if(totalPage > 0){
                List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
                modelAndView.addObject("pages", pages);
            }
            modelAndView.setViewName("views/listInstrumentsCategory");
            modelAndView.addObject("category", serviceCategory.findById(id));
            modelAndView.addObject("instruments", pageInstrument.getContent());
            modelAndView.addObject("current", page + 1);
            modelAndView.addObject("next", page + 2);
            modelAndView.addObject("prev", page);
            modelAndView.addObject("last", totalPage);

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
    public ModelAndView crud(Authentication auth,
                             @RequestParam Map<String, Object> params) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("views/crud");
            int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

            PageRequest pageRequest = PageRequest.of(page,10);

            Page<Instrument> pageInstrument = serviceInstrument.findAll(pageRequest);

            int totalPage = pageInstrument.getTotalPages();
            if(totalPage > 0){
                List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
                modelAndView.addObject("pages", pages);
            }

            modelAndView.addObject("instruments", pageInstrument.getContent());
            modelAndView.addObject("current", page + 1);
            modelAndView.addObject("next", page + 2);
            modelAndView.addObject("prev", page);
            modelAndView.addObject("last", totalPage);
            User user = serviceUser.getUserAutenticated(auth);
            modelAndView.addObject("user", user);
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
            if (!archive.isEmpty()){
                String route = "C://Ecommerce/images";
                int index = archive.getOriginalFilename().indexOf(".");
                String extension = "";
                extension = "."+archive.getOriginalFilename().substring(index+1);
                String photoName = Calendar.getInstance().getTimeInMillis()+extension;
                Path absoluteRoute = Paths.get(route+"//"+instrument.getPathImage());
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
                instrument.setPathImage(photoName);
            }
            this.serviceInstrument.updateOne(instrument, id);
            redirectAttributes.addFlashAttribute("message", "Edited Correctly!");
            redirectAttributes.addFlashAttribute("class", "success");
            modelAndView.setViewName("redirect:/instruments/crud/instruments");
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
    
}
