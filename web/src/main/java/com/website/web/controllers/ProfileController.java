package com.website.web.controllers;

import com.website.web.models.Image;
import com.website.web.models.Users;
import com.website.web.services.interfaces.IImageService;
import com.website.web.services.interfaces.IUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.nio.file.Files;
import java.security.Principal;


@Controller
public class ProfileController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IImageService imageService;

    @SneakyThrows
    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        Users user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);

        Image image = new Image();
        if (user.getImage() != null){
            image = imageService.getImageById(user.getImage().getId());
        } else {
            String imagePath = "web/src/main/resources/static/images/avatar.png";
            image.setBytes(Files.readAllBytes(new File(imagePath).toPath()));
        }

        byte[] encodeBase64 = Base64.encode(image.getBytes());
        String base64Encoded = new String(encodeBase64, "UTF-8");
        model.addAttribute("avatar", base64Encoded);
        return "Profile";
    }
}
