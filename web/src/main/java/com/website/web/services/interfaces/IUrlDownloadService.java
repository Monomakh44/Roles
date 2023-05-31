package com.website.web.services.interfaces;

import com.website.web.models.Url;

import java.util.List;

public interface IUrlDownloadService {
    boolean save(Url url);
    List<Url> findAllUrl();
}
