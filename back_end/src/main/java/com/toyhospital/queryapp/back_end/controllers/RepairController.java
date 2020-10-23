package com.toyhospital.queryapp.back_end.controllers;

import com.toyhospital.queryapp.back_end.models.Repair;
import com.toyhospital.queryapp.back_end.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
//@CrossOrigin?? - Something to specify where requests are allowed to come from!
public class RepairController {

    @Autowired
    RepairRepository repairRepository;

    @GetMapping(value = "/repairs")
    public ResponseEntity<List<Repair>> getAllRepairs(){
        List<Repair> foundRepairs = repairRepository.findAll();
        return new ResponseEntity<>(foundRepairs, HttpStatus.OK);
    }

    @GetMapping(value = "/repairs/{id}")
    public ResponseEntity<Optional<Repair>> getRepairById(@PathVariable Long id){
        Optional<Repair> foundRepair = repairRepository.findById(id);
        return new ResponseEntity<>(foundRepair, HttpStatus.OK);
    }

    @PutMapping(value = "/repairs/{id}")
    public ResponseEntity<Repair> updateRepair(@RequestBody Repair repair, @PathVariable Long id) {
        Repair repairToUpdate = repairRepository.findById(id).get();

        repairToUpdate.setName(repair.getName());
        repairToUpdate.setPrice(repair.getPrice());
        repairToUpdate.setToys(repair.getToys());

        repairRepository.save(repairToUpdate);
        return new ResponseEntity<>(repairToUpdate, HttpStatus.OK);
    }

    @PostMapping(value = "/repairs")
    public ResponseEntity<Repair> postRepair(@RequestBody Repair repair) {
        Repair newRepair = repairRepository.save(repair);
        return new ResponseEntity<>(newRepair, HttpStatus.CREATED);
    }

}
