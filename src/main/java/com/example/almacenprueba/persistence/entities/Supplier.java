package com.example.almacenprueba.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_supplier")
    private Long id_supplier;

    @Column(name = "address_1")
    private String address_1;

    @Column(name = "address_2")
    private String address_2;

    @Column(name = "address_3")
    private String address_3;

    @Column(name = "city")
    private String city;

    @Column(name = "comments")
    private String comments;

    @Column(name = "contact_email")
    private String contact_email;

    @Column(name = "contact_name")
    private String contact_name;

    @Column(name = "contact_phone")
    private String contact_phone;

    @Column(name = "country")
    private String country;

    @Column(name = "supplier_name")
    private String supplier_name;

    @Column(name = "postcode")
    private String postcode;


}
