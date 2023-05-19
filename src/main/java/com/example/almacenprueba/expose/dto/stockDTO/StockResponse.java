package com.example.almacenprueba.expose.dto.stockDTO;

import com.example.almacenprueba.expose.dto.productDTO.ProductResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockResponse {
    private Long id_stock;
    private ProductResponse product;
}
