package com.website.web.controllers;

import com.website.web.enums.Role;
import com.website.web.models.Users;
import com.website.web.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomersController {
    @Autowired
    private IUserService userService;

    @GetMapping("/customer")
    public String Customers(Model model) {
        Iterable<Users> user = userService.findAll();
        model.addAttribute("user", user);
        return "Customers";
    }
}