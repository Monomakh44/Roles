package com.website.web.services.interfaces;

import com.website.web.models.Image;

public interface IImageService {
    void add(Image image);
    Image getImageById(Long id);
    void remove(Image image);
}
