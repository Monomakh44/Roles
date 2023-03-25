package com.website.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PayController {
    @GetMapping("/pay")
    public String pay(){
        return "Pay";
    }
}
