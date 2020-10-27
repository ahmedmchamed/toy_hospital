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
    public ResponseEntity<Customer> postCustomer(@RequestBody Holder holder) throws IOException {

        Customer newCustomer = customerRepository.save(holder.getCustomer());

        Toy[] toys = holder.getToys();
        for (int i = 0; i < toys.length; i++) {
            Toy newToy = new Toy(toys[i].getToyName(), toys[i].getToyType(), toys[i].getToyAge(), toys[i].getToySize(), toys[i].getRepairFromCustomer(), newCustomer, null);
            newToy.setCustomer(newCustomer);
            toyRepository.save(newToy);
        }
//        Toy newToy = holder.getToy();
//        newCustomer.addToy(newToy);


//        MultipartFile[] photos = holder.getPhotos();
//        for (int i = 0; i < photos.length; i++) {
//            String filename = StringUtils.cleanPath(photos[i].getOriginalFilename());
//            Photo newPhoto = new Photo(filename, photos[i].getContentType(), photos[i].getBytes(), databaseToy);
//            photoRepository.save(newPhoto);
//        }
//
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

//    @PostMapping(value = "/customers")
//    public ResponseEntity<Customer> postCustomer(@RequestParam(name = "customer", required = false) Customer customer,
//                                                 @RequestParam(name = "toy", required = false) Toy toy,
//                                                 @RequestParam(name = "files", required = false) MultipartFile[] files) throws IOException {
//
//        Customer newCustomer = customerRepository.save(customer);
//
//        for (int i = 0; i < toy.length; i++) {
//            toy[i].setCustomer(newCustomer);
//            toyRepository.save(toy[i]);
//        }
//
//        toy.setCustomer(newCustomer);
//        toyRepository.save(toy);
//
//        for (int i = 0; i < files.length; i++) {
//            String filename = files[i].getOriginalFilename();
//            Photo newPhoto = new Photo(filename, files[i].getContentType(), files[i].getBytes(), toy);
//            photoRepository.save(newPhoto);
//        }
//
//        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
//    }

}
