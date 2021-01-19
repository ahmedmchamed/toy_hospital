package com.toyhospital.queryapp.back_end.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "repairs")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "repair_description")
    private String repairDescription;

    @JsonIgnoreProperties({"repair"})
    @ManyToMany
    @JoinTable(
            name = "toys_repairs",
            joinColumns = {@JoinColumn(name = "repair_id")},
            inverseJoinColumns = {@JoinColumn(name = "toy_id")}
    )
    private List<Toy> toys;

    public Repair(String name, double price, String repairDescription) {
        this.name = name;
        this.price = price;
        this.repairDescription = repairDescription;
        this.toys = new ArrayList<Toy>();
    }

    public Repair() {};

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Toy> getToys() {
        return toys;
    }

    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }

    public void addToy(Toy toy) {
        this.toys.add(toy);
    }

    public String getRepairDescription() {
        return this.repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }
}
