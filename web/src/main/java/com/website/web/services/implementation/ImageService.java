package com.website.web.services.implementation;

import com.website.web.models.Image;
import com.website.web.repositories.ImageRepository;
import com.website.web.services.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {
    @Autowired
    private ImageRepository imageRepository;
    public void add(Image image){
        Image savedImage = imageRepository.save(image);
        image.setId(savedImage.getId());
    }
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public void remove(Image image){
        imageRepository.delete(image);
    }
}
