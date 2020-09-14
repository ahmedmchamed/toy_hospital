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

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    ToyRepository toyRepository;

    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> foundCustomers = customerRepository.findAll();
        return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable Long id){
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }

    @PutMapping(value = "/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        Customer customerToUpdate = customerRepository.findById(id).get();

        customerToUpdate.setName(customer.getName());
        customerToUpdate.setAddress(customer.getAddress());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setPhoneNumber(customer.getPhoneNumber());
        customerToUpdate.setToys(customer.getToys());

        customerRepository.save(customerToUpdate);
        return new ResponseEntity<>(customerToUpdate, HttpStatus.OK);
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerRepository.save(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

//    @PostMapping(value = "/customers")
//    public ResponseEntity<Customer> postCustomer(@RequestParam(name = "customer", required = false) Customer customer,
//                                                 @RequestParam(name = "toy", required = false) Toy toy,
//                                                 @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
//
//        String filename = file.getOriginalFilename();
//        Photo newPhoto = new Photo(filename, file.getContentType(), file.getBytes(), null);
//
//        toyRepository.save(toy)
//        photoRepository.save(newPhoto);
//
//        Customer newCustomer = customerRepository.save(customer);
//        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
//    }

}
