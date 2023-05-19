package com.example.almacenprueba.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock")
    private Long id_stock;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;
}
