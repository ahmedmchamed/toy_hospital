package com.toyhospital.queryapp.back_end.controllers;

import com.toyhospital.queryapp.back_end.models.Customer;
import com.toyhospital.queryapp.back_end.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/customers/{:id}")
    public ResponseEntity getCustomerById(@PathVariable Long id){
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }



}
