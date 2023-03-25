package com.website.web.controllers;

import com.website.web.models.Image;
import com.website.web.models.Users;
import com.website.web.services.implementation.ImageService;
import com.website.web.services.implementation.UserService;
import lombok.SneakyThrows;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
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
    private ImageService imageService;
    @Autowired
    private UserService userService;

    @SneakyThrows
    @PostMapping("/profile")
    public String image(@RequestParam("avatar") MultipartFile file, Principal principal) {
        Users user = userService.getUserByPrincipal(principal);
        if (user.getImage() != null){
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
    public String getImage(@PathVariable("id") Long id, Model model){
        Image image = imageService.getImageById(id);
        model.addAttribute("image", image);
        return "redirect:/profile";
    }
}
