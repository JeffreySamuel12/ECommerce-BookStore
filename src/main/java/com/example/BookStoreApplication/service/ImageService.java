package com.example.BookStoreApplication.service;

import com.example.BookStoreApplication.dto.DataHolder;
import com.example.BookStoreApplication.model.Image;
import com.example.BookStoreApplication.repository.ImageRepository;
import com.example.BookStoreApplication.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    TokenUtility tokenUtility;


    private void checkDuplication(MultipartFile logo)
    {
        if(logo.getOriginalFilename()=="")
            throw new NullPointerException("Please upload a valid file");
        List<Image> imageModels = imageRepository.findAll() .stream().filter(image->{
            return image.getImageName()
                    .equalsIgnoreCase(logo.getOriginalFilename())
                    && image.getImageType().equalsIgnoreCase(logo.getContentType());
        }).toList();

        if(!imageModels.isEmpty())
            throw new RuntimeException("Image already exists");
    }

    private void checkAdmin(String token)
    {
        DataHolder dataHolder = tokenUtility.decode(token);
        if(!dataHolder.getRole().equalsIgnoreCase("admin"))
            throw new RuntimeException("You are not authorized :-)");
    }
    public Image addImage(String token, MultipartFile logo) throws IOException,NullPointerException {
        checkAdmin(token);
        checkDuplication(logo);
        Image imageModel = new Image();
        imageModel.setImageName(logo.getOriginalFilename());
        imageModel.setImageType(logo.getContentType());
        imageModel.setImageData(logo.getBytes());
        imageRepository.save(imageModel);

        return imageModel;
    }

    public Image getImageByID(String token,long id){
        checkAdmin(token);
        return imageRepository.findById(id).orElseThrow(()->new RuntimeException("Image doesn't exists :-)"));
    }

    public String updateImage(String token, MultipartFile logo, long imageId) throws IOException {
        checkDuplication(logo);
        Image update_image = getImageByID(token,imageId);
        update_image.setImageData(logo.getBytes());
        update_image.setImageName(logo.getOriginalFilename());
        update_image.setImageType(logo.getContentType());
        imageRepository.save(update_image);
        return " Updated the image with id "+imageId;
    }
}