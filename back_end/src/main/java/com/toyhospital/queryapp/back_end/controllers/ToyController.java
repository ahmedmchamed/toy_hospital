package com.toyhospital.queryapp.back_end.controllers;

import com.toyhospital.queryapp.back_end.models.Toy;
import com.toyhospital.queryapp.back_end.repositories.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ToyController {

    @Autowired
    ToyRepository toyRepository;

    @GetMapping(value = "/toys")
    public ResponseEntity<List<Toy>> getAllToys() {
        List<Toy> foundToys = toyRepository.findAll();
        return new ResponseEntity<>(foundToys, HttpStatus.OK);
    }

    @GetMapping(value = "/toys/{id}")
    public ResponseEntity<Optional<Toy>> getToy(@PathVariable Long id) {
        Optional<Toy> foundToy = toyRepository.findById(id);
        return new ResponseEntity<>(foundToy, HttpStatus.OK);
    }

    @PutMapping(value = "/toys/{id}")
    public ResponseEntity<Toy> updateToy(@RequestBody Toy toy, @PathVariable Long id) {
        Toy toyToUpdate = toyRepository.findById(id).get();

        toyToUpdate.setAge(toy.getAge());
        toyToUpdate.setCustomer(toy.getCustomer());
        toyToUpdate.setName(toy.getName());
//        toyToUpdate.setPhotos(toy.getPhotos());
        toyToUpdate.setRepair(toy.getRepair());
        toyToUpdate.setRepairFromCustomer(toy.getRepairFromCustomer());
        toyToUpdate.setSize(toy.getSize());
        toyToUpdate.setType(toy.getType());

        toyRepository.save(toyToUpdate);
        return new ResponseEntity<>(toyToUpdate, HttpStatus.OK);
    }

    @PostMapping(value = "/toys")
    public ResponseEntity<Toy> postToy(@RequestBody Toy toy) {
        Toy newToy = toyRepository.save(toy);
        return new ResponseEntity<>(newToy, HttpStatus.CREATED);
    }

}
