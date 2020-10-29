package com.toyhospital.queryapp.back_end.controllers;

import com.toyhospital.queryapp.back_end.models.Customer;
import com.toyhospital.queryapp.back_end.models.Holder;
import com.toyhospital.queryapp.back_end.models.Photo;
import com.toyhospital.queryapp.back_end.models.Toy;
import com.toyhospital.queryapp.back_end.repositories.CustomerRepository;
import com.toyhospital.queryapp.back_end.repositories.PhotoRepository;
import com.toyhospital.queryapp.back_end.repositories.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    ToyRepository toyRepository;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> foundCustomers = customerRepository.findAll();
        return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable Long id){
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }

    @PutMapping(value = "/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        Customer customerToUpdate = customerRepository.findById(id).get();

        customerToUpdate.setCustomerName(customer.getCustomerName());
        customerToUpdate.setCustomerAddress(customer.getCustomerAddress());
        customerToUpdate.setCustomerEmail(customer.getCustomerEmail());
        customerToUpdate.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());
        customerToUpdate.setCustomerToys(customer.getCustomerToys());

        customerRepository.save(customerToUpdate);
        return new ResponseEntity<>(customerToUpdate, HttpStatus.OK);
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<HashMap<String, Long>> postCustomer(@RequestBody Holder holder) {

        Customer databaseCustomer = customerRepository.save(holder.getCustomer());

        Toy toy = holder.getToys();
        toy.setCustomer(databaseCustomer);
        Toy databaseToy = toyRepository.save(toy);

        HashMap<String, Long> customerAndToyIds = new HashMap<>();

        customerAndToyIds.put("customerId", databaseCustomer.getId());
        customerAndToyIds.put("toyId", databaseToy.getId());
        //returns the customer instead
        //store the id in state in the front end
        //need to know if customer is already in the database
        //don't want the customer to exist more than once in the app
        //check if they exist in the database
        return new ResponseEntity<>(customerAndToyIds, HttpStatus.CREATED);
    }

}
