package com.toyhospital.queryapp.back_end.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="toys")
public class Toy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "toy_name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "age")
    private int age;

    @Column(name = "size")
    private double size;

    @Column(name = "repair_from_customer")
    private String repairFromCustomer;

    @JsonIgnoreProperties({"toy"})
    @OneToMany(mappedBy = "toy", fetch = FetchType.EAGER)
    private List<Photo> photos;

    @JsonIgnoreProperties({"toys"})
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @JsonIgnoreProperties({"toys"})
    @ManyToMany
    @JoinTable(
            name = "toys_repairs",
            joinColumns = {@JoinColumn(name = "toy_id", updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "repair_id", updatable = false)}
    )
    private List<Repair> repair;

    public Toy(String name, String type, int age, double size, String repairFromCustomer, Customer customer) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.size = size;
        this.repairFromCustomer = repairFromCustomer;
        this.photos = new ArrayList<Photo>();
        this.customer = customer;
        this.repair = new ArrayList<Repair>();
    }

    public Toy() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getRepairFromCustomer() {
        return repairFromCustomer;
    }

    public void setRepairFromCustomer(String repairFromCustomer) {
        this.repairFromCustomer = repairFromCustomer;
    }

    public List<Repair> getRepair() {
        return repair;
    }

    public void setRepair(List<Repair> repair) {
        this.repair = repair;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addRepair(Repair repair) {
        this.repair.add(repair);
    }
}
