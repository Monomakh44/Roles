package com.website.web.controllers;

import com.website.web.models.Info;
import com.website.web.services.interfaces.IInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InformationController {
    @Autowired
    private IInfoService infoService;

    @GetMapping("/info")
    public String info(Model model) {
        Iterable<Info> info = infoService.findAll();
        model.addAttribute("info", info);
        return "Information-main";
    }
}
