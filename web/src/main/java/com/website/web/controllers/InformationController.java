package com.website.web.controllers;

import com.website.web.models.Info;
import com.website.web.services.IInfoService;
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
        Iterable<Info> infos = infoService.findAll();
        model.addAttribute("infos", infos);
        return "Information-main";
    }
}
