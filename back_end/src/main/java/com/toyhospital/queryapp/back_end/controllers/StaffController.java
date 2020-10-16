package com.toyhospital.queryapp.back_end.controllers;

import com.toyhospital.queryapp.back_end.models.Staff;
import com.toyhospital.queryapp.back_end.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class StaffController {

    @Autowired
    StaffRepository staffRepository;

    @GetMapping(value = "/staff")
    public ResponseEntity<List<Staff>> getAllStaff() {
        List<Staff> allStaff = staffRepository.findAll();
        return new ResponseEntity<List<Staff>>(allStaff, HttpStatus.OK);
    }

    @GetMapping(value = "/staff/{id}")
    public ResponseEntity<Optional<Staff>> getStaffById(@PathVariable Long id) {
        Optional<Staff> staffMember = staffRepository.findById(id);
        return new ResponseEntity<Optional<Staff>>(staffMember, HttpStatus.OK);
    }

    @PostMapping(value = "/staff")
    public ResponseEntity<Staff> postStaff(@RequestBody Staff staff) {
        Staff newStaffMember = staffRepository.save(staff);
        return new ResponseEntity<Staff>(newStaffMember, HttpStatus.CREATED);
    }

}
