package com.toyhospital.queryapp.back_end.components;

import com.toyhospital.queryapp.back_end.models.Customer;
import com.toyhospital.queryapp.back_end.models.Repair;
import com.toyhospital.queryapp.back_end.models.Toy;
import com.toyhospital.queryapp.back_end.repositories.CustomerRepository;
import com.toyhospital.queryapp.back_end.repositories.RepairRepository;
import com.toyhospital.queryapp.back_end.repositories.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class DataLoader implements ApplicationRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RepairRepository repairRepository;

    @Autowired
    ToyRepository toyRepository;

    public DataLoader() {}

    public void run(ApplicationArguments args) {

        Customer customer = new Customer("John", "aaa@aaa.com", "01234567", "100 Somewhere Lane");
        customerRepository.save(customer);

        Toy toy = new Toy("fluffy", "mechanical", 20, 150.0, "This is broken", customer);
        toyRepository.save(toy);

        Repair repair = new Repair("Skin repair", 30.0);
        repairRepository.save(repair);

        customer.addToy(toy);
        customerRepository.save(customer);

        toy.addRepair(repair);
        toyRepository.save(toy);

        repair.addToy(toy);
        repairRepository.save(repair);

    }

}
