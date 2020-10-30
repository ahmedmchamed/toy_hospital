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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class PhotoController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    ToyRepository toyRepository;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/photos")
    public ResponseEntity<List<Photo>> getPhotos() {
        List<Photo> foundPhotos = photoRepository.findAll();
        return new ResponseEntity<>(foundPhotos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/photos/{id}")
    public ResponseEntity<Optional<Photo>> getPhoto(@PathVariable Long id) {
        Optional<Photo> foundPhoto = photoRepository.findById(id);
        return new ResponseEntity<>(foundPhoto, HttpStatus.OK);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam(value = "files") MultipartFile[][] files,
                                              @RequestParam(value = "toyIds") Long[] toyIds) throws IOException {

        try {
            for(int i = 0; i < toyIds.length; i++) {
                Optional<Toy> foundToy = toyRepository.findById(toyIds[i]);
                if (foundToy.isPresent()) {
                    for(int j = 0; j < files.length; j++) {
                        for(int k = 0; k < files[j].length; k++) {
                            String filename = StringUtils.cleanPath(files[j][k].getOriginalFilename());
                            Photo newPhoto = new Photo(filename, files[j][k].getContentType(), files[j][k].getBytes(), foundToy.get());
                            photoRepository.save(newPhoto);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not upload files");
        }

        return new ResponseEntity<>("foundToy", HttpStatus.CREATED);
    }
}
