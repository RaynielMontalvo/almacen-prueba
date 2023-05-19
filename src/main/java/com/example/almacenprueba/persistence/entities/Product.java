package com.example.almacenprueba.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id_product;

    @Column(name = "name_product")
    private String name_product;

    @Column(name = "comments")
    private String comments;

    @Column(name = "long_description")
    private String long_description;

    @Column(name = "short_description")
    private String short_description;

    @Column(name = "unit_price")
    private Float unit_price;

    @Column(name = "supplier_partno")
    private String supplier_partno;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_supplier")
    private Supplier supplier;


}
