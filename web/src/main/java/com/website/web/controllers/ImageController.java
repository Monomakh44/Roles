package com.website.web.controllers;

import com.website.web.models.Image;
import com.website.web.models.Users;
import com.website.web.services.interfaces.IImageService;
import com.website.web.services.interfaces.IUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
public class ImageController {
    @Autowired
    private IImageService imageService;
    @Autowired
    private IUserService userService;

    @SneakyThrows
    @PostMapping("/profile")
    public String image(@RequestParam("avatar") MultipartFile file, Principal principal, Model model) {
        Users user = userService.getUserByPrincipal(principal);
        if (user.getImage() != null) {
            imageService.remove(user.getImage());
        }

        Image image = new Image();
        image.setBytes(file.getBytes());
        user.setImage(image);
        imageService.add(image);
        userService.save(user);

        return "redirect:/profile";
    }

    @PostMapping("/profile/{id}")
    public String getImage(@PathVariable("id") Long id, Model model) {
        Image image = imageService.getImageById(id);
        model.addAttribute("image", image);
        return "redirect:/profile";
    }
}
