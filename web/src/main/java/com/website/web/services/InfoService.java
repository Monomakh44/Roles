package com.website.web.services;

import com.website.web.models.Info;
import com.website.web.repo.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService implements IInfoService {

    @Autowired
    private InfoRepository repository;

    @Override
    public List<Info> findAll() {

        var info = (List<Info>) repository.findAll();

        return info;
    }
}
