package com.website.web.controllers;

import com.website.web.models.Users;
import com.website.web.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {
    @Autowired
    private IUserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "Registration";
    }

    @PostMapping("/registration")
    public String postRegistration(Users users, Model model) {
        if(!userService.save(users))
        {
            model.addAttribute("errorMessage",  "Пользователь с email: " + users.getEmail() + "уже существует");
            return "Registration";
        }
        userService.save(users);
        return "redirect:/entrance";
    }
}
