package com.website.web.controllers;

import com.website.web.models.Image;
import com.website.web.services.implementation.ImageService;
import com.website.web.services.implementation.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;

    @SneakyThrows
    @PostMapping("/profile")
    public String image(@RequestParam("avatar") MultipartFile file, Model model) {
        Image image = new Image();
        image.setBytes(file.getBytes());
        imageService.add(image);
        model.addAttribute("avatar", image);
        return "redirect:/profile";
    }
    @PostMapping("/profile/{id}")
    public String getImage(@PathVariable("id") Long id, Model model){
        Image image = imageService.getImageById(id);
        model.addAttribute("image", image);
        return "redirect:/profile";
    }
}
