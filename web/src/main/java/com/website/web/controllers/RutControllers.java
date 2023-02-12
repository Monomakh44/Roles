package com.website.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RutControllers {

    @GetMapping("/")
    public String Home(Model model) {
        model.addAttribute("Title", "Главная страница");
        return "Home";
    }
}