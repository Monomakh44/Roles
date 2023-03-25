package com.website.web.controllers;

import com.website.web.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntranceController {
    @Autowired
    IUserService userService;

    @GetMapping("/entrance")
    public String Entrance() {
        return "Entrance";}

}
