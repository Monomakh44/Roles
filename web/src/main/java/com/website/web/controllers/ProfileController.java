package com.website.web.controllers;

import com.website.web.models.Users;
import com.website.web.services.interfaces.IGetImageService;
import com.website.web.services.interfaces.IUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class ProfileController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IGetImageService getImageService;

    @SneakyThrows
    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        Users user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        getImageService.image(model, principal);
        return "Profile";
    }
}
