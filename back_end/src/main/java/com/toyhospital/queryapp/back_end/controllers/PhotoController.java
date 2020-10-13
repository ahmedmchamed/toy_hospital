package com.toyhospital.queryapp.back_end.controllers;

import com.toyhospital.queryapp.back_end.models.Customer;
import com.toyhospital.queryapp.back_end.models.Photo;
import com.toyhospital.queryapp.back_end.models.Toy;
import com.toyhospital.queryapp.back_end.repositories.CustomerRepository;
import com.toyhospital.queryapp.back_end.repositories.PhotoRepository;
import com.toyhospital.queryapp.back_end.repositories.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PhotoController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    ToyRepository toyRepository;

    @GetMapping(value = "/photos")
    public ResponseEntity<List<Photo>> getPhotos() {
        List<Photo> foundPhotos = photoRepository.findAll();
        return new ResponseEntity<>(foundPhotos, HttpStatus.OK);
    }

    @GetMapping(value = "/photos/{id}")
    public ResponseEntity<Optional<Photo>> getPhoto(@PathVariable Long id) {
        Optional<Photo> foundPhoto = photoRepository.findById(id);
        return new ResponseEntity<>(foundPhoto, HttpStatus.OK);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam(value = "files") MultipartFile[] files,
                                              @RequestParam(value = "toy") Toy toy) throws IOException {
        try {
            for (int i = 0; i < files.length; i++) {
                String filename = StringUtils.cleanPath(files[i].getOriginalFilename());
                Photo newPhoto = new Photo(filename, files[i].getContentType(), files[i].getBytes(), toy);
                photoRepository.save(newPhoto);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not upload files");
        }

        return new ResponseEntity<>("Files uploaded", HttpStatus.CREATED);
    }
}
