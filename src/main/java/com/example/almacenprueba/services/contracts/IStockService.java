package com.example.almacenprueba.services.contracts;

import com.example.almacenprueba.exceptions.NotFoundException;
import com.example.almacenprueba.expose.dto.stockDTO.SaveStockRequest;
import com.example.almacenprueba.expose.dto.stockDTO.StockResponse;
import com.example.almacenprueba.expose.dto.stockDTO.UpdateStockRequest;

import java.util.List;

public interface IStockService {
    List<StockResponse> getAll();

    StockResponse findById(Long id) throws NotFoundException;

    StockResponse create(SaveStockRequest saveStockRequest) throws NotFoundException;

    StockResponse update(Long id, UpdateStockRequest updateStockRequest) throws NotFoundException;

    void delete(Long id);

    List<StockResponse> getStockListByProductName(Long id);
}
