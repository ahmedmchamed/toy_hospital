package com.toyhospital.queryapp.back_end.controllers;

import com.toyhospital.queryapp.back_end.models.Photo;
import com.toyhospital.queryapp.back_end.models.Toy;
import com.toyhospital.queryapp.back_end.repositories.PhotoRepository;
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
    public ResponseEntity<String> uploadPhoto(@RequestParam(value = "files", required = false) MultipartFile files, @RequestBody Toy toy)
            throws IOException {

//        try {
//            List<Photo> newPhotos = new ArrayList<Photo>();
//
//            for (int i = 0; i < files.length; i++) {
//                String filename = files[i].getOriginalFilename();
//                Photo newPhoto = new Photo(filename, files[i].getContentType(), files[i].getBytes(), null);
//                newPhotos.add(newPhoto);
//            }
//    try {
        String filename = StringUtils.cleanPath(files.getOriginalFilename());
        Photo newPhoto = new Photo(filename, files.getContentType(), files.getBytes(), toy);

        return new ResponseEntity<>("Hooray", HttpStatus.CREATED);
//    }
//    catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Nope, error.");
//    }

//        }
//        catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Teehee");
//        }

//        return new ResponseEntity<>(newPhoto, HttpStatus.CREATED);
    }

}
