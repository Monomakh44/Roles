package com.website.web.services.implementation;

import com.website.web.models.Home;
import com.website.web.repositories.HomeRepository;
import com.website.web.services.interfaces.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HomeService implements IHomeService {
    @Autowired
    private HomeRepository homeRepository;
    @Override
    public List<Home> findAll() {
        return homeRepository.findAll();
    }
}
