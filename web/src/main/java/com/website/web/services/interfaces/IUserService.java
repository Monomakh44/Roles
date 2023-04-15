package com.website.web.services.interfaces;

import com.website.web.models.Users;
import org.apache.catalina.User;

import java.security.Principal;
import java.util.List;

public interface IUserService{
    List<Users> findAllNoAdmin();
    void banUser(Long id);
    boolean save(Users users);
    void deleteById(Long id);
    Users findByEmail(String email);
    Users getUserByPrincipal(Principal principal);
}
