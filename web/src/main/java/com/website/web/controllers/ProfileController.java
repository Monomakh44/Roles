package com.website.web.controllers;

import com.website.web.models.Users;
import com.website.web.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;


@Controller
public class ProfileController {
    @Autowired
    private IUserService userService;

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        Users user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "Profile";
    }
}
