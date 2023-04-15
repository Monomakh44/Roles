package com.website.web.services.implementation;

import com.website.web.models.Image;
import com.website.web.models.Users;
import com.website.web.repositories.ImageRepository;
import com.website.web.services.interfaces.IImageService;
import com.website.web.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.security.Principal;

@Service
public class ImageService implements IImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public void add(Image image){
        Image savedImage = imageRepository.save(image);
        image.setId(savedImage.getId());
    }
    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
    @Override
    public void remove(Image image){
        imageRepository.delete(image);
    }
}
