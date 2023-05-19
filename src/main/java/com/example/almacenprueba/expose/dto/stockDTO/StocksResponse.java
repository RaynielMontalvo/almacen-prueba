package com.example.almacenprueba.expose.dto.stockDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StocksResponse {
    private List<StocksResponse> stocks;
}
