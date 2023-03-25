package com.website.web.services.interfaces;

import com.website.web.models.Users;

import java.security.Principal;
import java.util.List;

public interface IUserService{
    List<Users> findAll();
    boolean save(Users users);
    void deleteById(Long id);
    Users findByEmail(String email);
    Users getUserByPrincipal(Principal principal);
}
