package com.website.web.services.interfaces;

import org.springframework.ui.Model;

import java.security.Principal;

public interface IGetImageService {
    void image(Model model, Principal principal);
}
