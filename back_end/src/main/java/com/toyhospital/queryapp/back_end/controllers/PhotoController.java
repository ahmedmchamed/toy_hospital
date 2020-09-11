package com.toyhospital.queryapp.back_end.controllers;

import antlr.StringUtils;
import com.toyhospital.queryapp.back_end.models.Photo;
import com.toyhospital.queryapp.back_end.models.Toy;
import com.toyhospital.queryapp.back_end.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PhotoController {

    @Autowired
    PhotoRepository photoRepository;

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
    public ResponseEntity<Photo> uploadPhoto(@RequestParam("file") MultipartFile file,
                                             @RequestParam("toy") Toy toy) throws IOException {
        String filename = file.getOriginalFilename();
        Photo newPhoto = new Photo(filename, file.getContentType(), file.getBytes(), toy);

        return new ResponseEntity<>(photoRepository.save(newPhoto), HttpStatus.CREATED);
    }

}
