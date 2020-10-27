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
    private String toyName;

    @Column(name = "type")
    private String toyType;

    @Column(name = "age")
    private int toyAge;

    @Column(name = "size")
    private double toySize;

    @Column(name = "repair_from_customer")
    private String repairFromCustomer;

    @JsonIgnoreProperties({"toy"})
    @OneToMany(mappedBy = "toy", fetch = FetchType.EAGER)
    private List<Photo> photos;

    @JsonIgnoreProperties({"customerToys"})
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonIgnoreProperties({"toys"})
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @JsonIgnoreProperties({"toys"})
    @ManyToMany
    @JoinTable(
            name = "toys_repairs",
            joinColumns = {@JoinColumn(name = "toy_id", updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "repair_id", updatable = false)}
    )
    private List<Repair> repair;

    public Toy(String toyName, String toyType, int toyAge, double toySize, String repairFromCustomer, Customer customer, Staff staff) {
        this.toyName = toyName;
        this.toyType = toyType;
        this.toyAge = toyAge;
        this.toySize = toySize;
        this.repairFromCustomer = repairFromCustomer;
        this.photos = new ArrayList<Photo>();
        this.customer = customer;
        this.staff = staff;
        this.repair = new ArrayList<Repair>();
    }

    public Toy() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public String getToyType() {
        return toyType;
    }

    public void setToyType(String toyType) {
        this.toyType = toyType;
    }

    public int getToyAge() {
        return toyAge;
    }

    public void setToyAge(int toyAge) {
        this.toyAge = toyAge;
    }

    public double getToySize() {
        return toySize;
    }

    public void setToySize(double toySize) {
        this.toySize = toySize;
    }
}
