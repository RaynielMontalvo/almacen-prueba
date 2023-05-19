package com.example.almacenprueba.expose.dto.productDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProductRequest implements Serializable {
    private String name_product;
    private String comments;
    private String long_description;
    private String short_description;
    private Float unit_price;
}
