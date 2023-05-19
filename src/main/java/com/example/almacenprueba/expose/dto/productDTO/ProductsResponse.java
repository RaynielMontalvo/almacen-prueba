package com.example.almacenprueba.expose.dto.productDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductsResponse implements Serializable {
    private List<ProductResponse> productos;
}
