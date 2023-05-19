package com.example.almacenprueba.services.contracts;

import com.example.almacenprueba.exceptions.NotFoundException;
import com.example.almacenprueba.expose.dto.productDTO.ProductResponse;
import com.example.almacenprueba.expose.dto.productDTO.SaveProductRequest;
import com.example.almacenprueba.expose.dto.productDTO.UpdateProductRequest;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface IProductService {

    List<ProductResponse> getAll();
    ProductResponse findById(Long id) throws NotFoundException;

    ProductResponse create(SaveProductRequest productRequest);

    ProductResponse update(Long id , UpdateProductRequest updateProductRequest) throws NotFoundException;

    void delete(Long id);
}
