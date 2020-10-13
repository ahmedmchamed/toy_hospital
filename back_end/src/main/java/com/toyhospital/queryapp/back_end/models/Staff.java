package com.toyhospital.queryapp.back_end.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "superpowers")
    private String superpower;

    @JsonIgnoreProperties({"staff"})
    @OneToMany(mappedBy = "staff", fetch = FetchType.EAGER)
    private List<Toy> toys;

    public Staff(String firstName, String lastName, String superpower) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.superpower = superpower;
        this.toys = new ArrayList<Toy>();
    }

    public Staff() {}

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }
}
