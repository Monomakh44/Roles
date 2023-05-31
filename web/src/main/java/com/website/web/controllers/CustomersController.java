package com.website.web.controllers;

import com.website.web.models.Home;
import com.website.web.models.Image;
import com.website.web.models.Users;
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

import java.io.File;
import java.nio.file.Files;
import java.security.Principal;

@Controller
public class CustomersController {
    @Autowired
    private IHomeService homeService;
    @Autowired
    private IGetImageService getImageService;
    @SneakyThrows
    @GetMapping("/customer")
    public String Customers(Model model, Principal principal) {
        Iterable<Home> home = homeService.findAll();
        getImageService.image(model, principal);
        model.addAttribute("home", home);
        return "Customers";
    }
}