package com.toyhospital.queryapp.back_end.models;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class Holder {

    private Customer customer;
    private Toy[] toys;
//    private MultipartFile[] photos;

    public Holder(Customer customer, Toy[] toys) {
        this.customer = customer;
        this.toys = toys;
//        this.photos = photos;
    }

    public Holder() {}

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Toy[] getToys() {
        return toys;
    }

    public void setToys(Toy[] toy) {
        this.toys = toy;
    }

//    public MultipartFile[] getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(MultipartFile[] photos) {
//        this.photos = photos;
//    }
}
