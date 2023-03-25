package com.website.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactsController {
    @GetMapping("/contact")
    public String Contacts(){
        return "/Contacts";
    }
}
