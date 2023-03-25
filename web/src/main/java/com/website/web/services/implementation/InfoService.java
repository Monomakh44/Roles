package com.website.web.services.implementation;

import com.website.web.models.Info;
import com.website.web.repositories.InfoRepository;
import com.website.web.services.interfaces.IInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService implements IInfoService {
    @Autowired
    private InfoRepository infoRepository;

    @Override
    public List<Info> findAll() {

        return infoRepository.findAll();
    }
}
