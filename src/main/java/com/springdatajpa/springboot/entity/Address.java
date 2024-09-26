package com.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    // This will be owner side for OneToOne Bidirectional Mapping between Order and Address
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id", referencedColumnName = "id")  // will assign a custom name to foreign key
    private Order order;

}