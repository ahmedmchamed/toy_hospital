package com.toyhospital.queryapp.back_end.components;

import com.toyhospital.queryapp.back_end.models.*;
import com.toyhospital.queryapp.back_end.repositories.*;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public DataLoader() {}

    public void run(ApplicationArguments args) {

        User sara = new User("saraemanuelsson", "sara256emanuelsson@gmail.com", "1234");
        userRepository.save(sara);

        Role admin = new Role(ERole.ROLE_ADMIN);
        roleRepository.save(admin);

        Role user = new Role(ERole.ROLE_USER);
        roleRepository.save(user);

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
