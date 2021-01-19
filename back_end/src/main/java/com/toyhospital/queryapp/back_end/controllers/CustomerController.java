package com.toyhospital.queryapp.back_end.controllers;

import com.toyhospital.queryapp.back_end.models.*;
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
import java.util.ArrayList;
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
        customerToUpdate.setCustomerEmail(customer.getCustomerEmail());
        customerToUpdate.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());
        customerToUpdate.setCustomerAddress(customer.getCustomerAddress());
        customerToUpdate.setIsCustomerRead(customer.getIsCustomerRead());
        customerToUpdate.setCustomerToys(customer.getCustomerToys());

        customerRepository.save(customerToUpdate);
        return new ResponseEntity<>(customerToUpdate, HttpStatus.OK);
    }

    @PostMapping(value = "/customers")
    public ResponseEntity<ArrayList<Long>> postCustomer(@RequestPart(value = "json") Holder holder,
                                                        @ModelAttribute FileTest files) throws IOException {

        ArrayList<Long> toyIds = new ArrayList<>();
        Customer databaseCustomer = customerRepository.save(holder.getCustomer());

        Toy[] customerToys = holder.getToys();
        List<MultipartFile>[] photos = files.getFiles();

        for(int i = 0; i < customerToys.length; i++) {
            customerToys[i].setCustomer(databaseCustomer);
            Toy databaseToy = toyRepository.save(customerToys[i]);
            toyIds.add(databaseToy.getId());
            for (int j = 0; j < photos[i].size(); j++) {
                String filename = StringUtils.cleanPath(photos[i].get(j).getOriginalFilename());
                System.out.println(filename);
                Photo newPhoto = new Photo(filename, photos[i].get(j).getContentType(), photos[i].get(j).getBytes(), databaseToy);
                photoRepository.save(newPhoto);
            }
        }

        return new ResponseEntity<>(toyIds, HttpStatus.CREATED);
    }

}
