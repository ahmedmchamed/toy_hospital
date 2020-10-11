package com.toyhospital.queryapp.back_end.controllers;

import com.toyhospital.queryapp.back_end.models.Role;
import com.toyhospital.queryapp.back_end.models.User;
import com.toyhospital.queryapp.back_end.repositories.RoleRepository;
import com.toyhospital.queryapp.back_end.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

//Roles just needs saving once so can be removed, gonna leave it commented out for now
//    @GetMapping(value = "/roles")
//    public ResponseEntity<List<Role>> getAllRoles() {
//        List<Role> foundRoles = roleRepository.findAll();
//        return new ResponseEntity<>(foundRoles, HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/roles")
//    public ResponseEntity<Role> postRole(@RequestBody Role role) {
//        Role newRole = roleRepository.save(role);
//        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping(value = "/roles")
//    public void deleteRoles() {
//        roleRepository.deleteAll();
//    }
    
// only added for testing, will need to be removed once we've set up better functionality to manage users
//    @GetMapping(value = "/users")
//    public ResponseEntity<List<User>> getUsers() {
//        List<User> allUsers = userRepository.findAll();
//        return new ResponseEntity<>(allUsers, HttpStatus.OK);
//    }
//
//
//    @DeleteMapping(value = "/users")
//    public void deleteUsers() {
//        userRepository.deleteAll();
//    }
//
//    @PutMapping(value = "/user/{:id}")
//    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
//        User userToUpdate = userRepository.findById(id).get();
//        userToUpdate.setUsername(user.getUsername());
//        userToUpdate.setEmail(user.getEmail());
//        userToUpdate.setPassword(user.getPassword());
//        userToUpdate.setRoles(user.getRoles());
//        userRepository.save(userToUpdate);
//        return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
//    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
