package com.website.web.controllers;

import com.website.web.models.Home;
import com.website.web.models.Image;
import com.website.web.models.Users;
import com.website.web.services.implementation.GetImageService;
import com.website.web.services.interfaces.IGetImageService;
import com.website.web.services.interfaces.IHomeService;
import com.website.web.services.interfaces.IImageService;
import com.website.web.services.interfaces.IUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.nio.file.Files;
import java.security.Principal;

@Controller
public class BookingController {
    @Autowired
    private IHomeService homeService;
    @Autowired
    private IGetImageService getImageService;
    @SneakyThrows
    @GetMapping("/booking")
    public String booking(Model model, Principal principal){
        Iterable<Home> home = homeService.findAllById(principal);
        model.addAttribute("homeId", home);
        getImageService.image(model, principal);
        return "Booking";
    }
    @PostMapping("/booking/{id}")
    public String delete(@PathVariable("id") Long id) {
        homeService.deleteById(id);
        return "redirect:/booking";
    }
}
