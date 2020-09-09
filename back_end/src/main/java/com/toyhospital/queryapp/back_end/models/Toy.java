package com.toyhospital.queryapp.back_end.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="toys")
public class Toy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "age")
    private int age;

    @Column(name = "size")
    private double size;

    @Column(name = "repair_from_customer")
    private String repairFromCustomer;

    @JsonIgnoreProperties({"toys"})
    @ManyToMany
    @JoinTable(
            name = "toys_repairs",
            joinColumns = {@JoinColumn(name = "toy_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "repair_id", nullable = false, updatable = false)}
    )
    private ArrayList<Repair> repair;

    @Column(name = "photos")
    private ArrayList<String> photos;

    public Toy(String name, String type, int age, double size, String repairFromCustomer) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.size = size;
        this.repairFromCustomer = repairFromCustomer;
        this.repair = new ArrayList<Repair>();
        this.photos = new ArrayList<String>();
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

    public ArrayList<Repair> getRepair() {
        return repair;
    }

    public void setRepair(ArrayList<Repair> repair) {
        this.repair = repair;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public void addRepair(Repair repair) {
        this.repair.add(repair);
    }
}
