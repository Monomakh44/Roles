package com.website.web.controllers;

import com.website.web.services.interfaces.IGetImageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class RecordController {
    @Autowired
    private IGetImageService getImageService;
    @SneakyThrows
    @GetMapping("/record")
    public String Record(Model model, Principal principal) {
        getImageService.image(model, principal);
        return "Record";
    }
}
