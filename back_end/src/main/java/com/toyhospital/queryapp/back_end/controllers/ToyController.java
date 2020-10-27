package com.toyhospital.queryapp.back_end.controllers;

import com.toyhospital.queryapp.back_end.models.Toy;
import com.toyhospital.queryapp.back_end.repositories.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class ToyController {

    @Autowired
    ToyRepository toyRepository;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/toys")
    public ResponseEntity<List<Toy>> getAllToys() {
        List<Toy> foundToys = toyRepository.findAll();
        return new ResponseEntity<>(foundToys, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/toys/{id}")
    public ResponseEntity<Optional<Toy>> getToy(@PathVariable Long id) {
        Optional<Toy> foundToy = toyRepository.findById(id);
        return new ResponseEntity<>(foundToy, HttpStatus.OK);
    }

    @PutMapping(value = "/toys/{id}")
    public ResponseEntity<Toy> updateToy(@RequestBody Toy toy, @PathVariable Long id) {
        Toy toyToUpdate = toyRepository.findById(id).get();

        toyToUpdate.setToyAge(toy.getToyAge());
        toyToUpdate.setStaff(toy.getStaff());
        toyToUpdate.setCustomer(toy.getCustomer());
        toyToUpdate.setToyName(toy.getToyName());
        toyToUpdate.setPhotos(toy.getPhotos());
        toyToUpdate.setRepair(toy.getRepair());
        toyToUpdate.setRepairFromCustomer(toy.getRepairFromCustomer());
        toyToUpdate.setToySize(toy.getToySize());
        toyToUpdate.setToyType(toy.getToyType());

        toyRepository.save(toyToUpdate);
        return new ResponseEntity<>(toyToUpdate, HttpStatus.OK);
    }

    @PostMapping(value = "/toys")
    public ResponseEntity<Toy> postToy(@RequestBody Toy toy) {
        Toy newToy = toyRepository.save(toy);
        return new ResponseEntity<>(newToy, HttpStatus.CREATED);
    }

}
