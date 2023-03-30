package com.website.web.services.interfaces;

import com.website.web.models.Home;

import java.util.List;

public interface IHomeService {
    List<Home> findAll();
}
