package com.toyhospital.queryapp.back_end.controllers;

import com.toyhospital.queryapp.back_end.models.Customer;
import com.toyhospital.queryapp.back_end.models.FileTest;
import com.toyhospital.queryapp.back_end.models.Photo;
import com.toyhospital.queryapp.back_end.models.Toy;
import com.toyhospital.queryapp.back_end.repositories.CustomerRepository;
import com.toyhospital.queryapp.back_end.repositories.PhotoRepository;
import com.toyhospital.queryapp.back_end.repositories.ToyRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/photos")
    public ResponseEntity<List<Photo>> getPhotos() {
        List<Photo> foundPhotos = photoRepository.findAll();
        return new ResponseEntity<>(foundPhotos, HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/photos/{id}")
    public ResponseEntity<Photo> getPhoto(@PathVariable Long id) {
        Optional<Photo> foundPhoto = photoRepository.findById(id);
        return new ResponseEntity<>(foundPhoto.get(), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    @RequestMapping(value = "/upload", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
//    public ResponseEntity<String> uploadPhoto(@RequestParam(value = "files") List<MultipartFile>[] files) {
//        for (int i = 0; i < files.length; i++) {
//            for (int j = 0; j < files[i].size(); j++) {
//                System.out.println(files[i].get(j).getOriginalFilename());
//            }
//        }
//
//        return new ResponseEntity<>("foundToy", HttpStatus.CREATED);
//    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("files") String[][] photos) throws IOException {

        for (int i = 0; i < photos.length; i++) {
            for (int j = 0; j < photos[i].length; j++) {
                Photo newPhoto = new Photo(String.format("photo=%s", i), Base64.decodeBase64(photos[i][j]), null);
                photoRepository.save(newPhoto);
            }
        }
//        List<MultipartFile[]> allFiles = files.getFiles();
//        System.out.println(allFiles);
//        System.out.println(allFiles.getFiles().length);
//        System.out.println(allFiles.getFiles()[0][0].getClass());
//        System.out.println(allFiles.getAllFiles()[0].get(0).getClass());
//        System.out.println(allFiles.getFiles()[0].get(0).getOriginalFilename());
//        System.out.println(allFiles.size());
//        System.out.println(allFiles.get(0).get(0).getOriginalFilename());
//        System.out.println(toyIds);
//        try {
//            for(int i = 0; i < toyIds.size(); i++) {
//                Optional<Toy> foundToy = toyRepository.findById(toyIds.get(i));
//                if (foundToy.isPresent()) {
////                    for(int j = 0; j < allFiles.length; j++) {
//                        for(int k = 0; k < allFiles[i].size(); k++) {
//                            String filename = StringUtils.cleanPath(allFiles[i].get(k).getOriginalFilename());
//                            System.out.println(filename);
//                            Photo newPhoto = new Photo(filename, allFiles[i].get(k).getContentType(), allFiles[i].get(k).getBytes(), foundToy.get());
//                            photoRepository.save(newPhoto);
//                        }
////                    }
//                }
//            }
//        }
//        catch (IOException e) {
//            throw new RuntimeException("Could not upload files");
//        }

        return new ResponseEntity<>("foundToy", HttpStatus.CREATED);
    }
}
