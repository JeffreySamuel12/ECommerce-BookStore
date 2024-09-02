package com.example.BookStoreApplication.controller;

import com.example.BookStoreApplication.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping()
    public ResponseEntity<?> saveImage(@RequestHeader String token, @RequestPart MultipartFile logo) throws IOException {
        return new ResponseEntity<>(imageService.addImage(token,logo), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateImage(@RequestHeader String token,@RequestPart MultipartFile logo,@PathVariable long id) throws IOException {
        return new ResponseEntity<>(imageService.updateImage(token,logo,id),HttpStatus.ACCEPTED);
    }
}