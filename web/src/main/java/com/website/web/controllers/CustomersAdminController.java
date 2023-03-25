package com.website.web.controllers;

import com.website.web.enums.Role;
import com.website.web.models.Users;
import com.website.web.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomersAdminController {
    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    public String Users(Model model) {
        Iterable<Users> user = userService.findAll();
        model.addAttribute("user", user);
        return "Users";
    }

    @PostMapping("/users/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
