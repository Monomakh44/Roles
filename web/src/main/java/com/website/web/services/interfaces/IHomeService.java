package com.website.web.services.interfaces;

import com.website.web.models.Home;

import java.security.Principal;
import java.util.List;

public interface IHomeService {
    List<Home> findAll();
    List<Home> findAllById(Principal principal);
    void deleteById(Long id);
}
