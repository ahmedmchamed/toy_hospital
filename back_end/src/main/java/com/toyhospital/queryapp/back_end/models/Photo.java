package com.toyhospital.queryapp.back_end.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

//    @Column(name = "type")
//    private String type;

    @Lob
    private byte[] data;

    @JsonIgnoreProperties({"photos"})
    @ManyToOne
    @JoinColumn(name = "toy_id")
    private Toy toy;

    public Photo(String name, byte[] data, Toy toy) {
        this.name = name;
//        this.type = type;
        this.data = data;
        this.toy = toy;
    }

    public Photo() {}

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

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Toy getToy() {
        return toy;
    }

    public void setToy(Toy toy) {
        this.toy = toy;
    }
}
