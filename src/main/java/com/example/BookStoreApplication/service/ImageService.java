package com.example.BookStoreApplication.service;


import com.example.BookStoreApplication.model.Image;
import com.example.BookStoreApplication.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;
    public Image addImage(String token, MultipartFile logo) throws IOException,NullPointerException {

        if(logo.getOriginalFilename()==null)
            throw new NullPointerException("Enter a image for logo");
        Image imageModel = new Image();
        List<Image> imageModels = imageRepository.findAll() .stream().filter(image->{
            return image.getImageName()
                    .equalsIgnoreCase(logo.getOriginalFilename())
                    && image.getImageType().equalsIgnoreCase(logo.getContentType());
        }).toList();

        if(!imageModels.isEmpty())
            throw new RuntimeException("Image already exists");

        imageModel.setImageName(logo.getOriginalFilename());
        imageModel.setImageType(logo.getContentType());
        imageModel.setImageData(logo.getBytes());
        imageRepository.save(imageModel);

        return imageModel;
    }

    public Image getImageByID(long id){
        return imageRepository.findById(id).orElseThrow(()->new RuntimeException("Image doesn't exists :-)"));
    }
}