package com.website.web.services.implementation;

import com.website.web.models.Home;
import com.website.web.models.Users;
import com.website.web.repositories.HomeRepository;
import com.website.web.services.interfaces.IHomeService;
import com.website.web.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
@Service
public class HomeService implements IHomeService {
    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private IUserService userService;
    @Override
    public List<Home> findAll() {
        return homeRepository.findAll();
    }
    @Override
    public List<Home> findAllById(Principal principal){
        Users user = userService.getUserByPrincipal(principal);
        List<Home> homes = homeRepository.findAll();
        List<Home> newHomes = new ArrayList<>();
        for (Home home : homes) {
            if (home.getUserId().equals(user.getId())) {
                newHomes.add(home);
            }
        }
        return newHomes;
    }
    @Override
    public void deleteById(Long id) {
        homeRepository.deleteById(id);
    }
}
