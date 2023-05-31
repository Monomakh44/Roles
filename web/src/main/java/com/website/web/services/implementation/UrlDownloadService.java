package com.website.web.services.implementation;

import com.website.web.models.Url;
import com.website.web.repositories.UrlDownloadRepository;
import com.website.web.services.interfaces.IUrlDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class UrlDownloadService implements IUrlDownloadService {
    @Autowired
    private UrlDownloadRepository urlRepository;
    @Override
    public boolean save(Url url) {
        Date date = new Date();
        url.setDate(date);
        urlRepository.save(url);
        return true;
    }

    @Override
    public List<Url> findAllUrl() {
        return urlRepository.findAll();
    }

}
