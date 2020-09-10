package com.toyhospital.queryapp.back_end.controllers;

import com.toyhospital.queryapp.back_end.models.Customer;
import com.toyhospital.queryapp.back_end.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

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

    @PutMapping(value = "customers/{id}")
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



}
