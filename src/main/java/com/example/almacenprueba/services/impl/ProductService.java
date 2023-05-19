package com.example.almacenprueba.services.impl;

import com.example.almacenprueba.exceptions.NotFoundException;
import com.example.almacenprueba.expose.dto.productDTO.ProductResponse;
import com.example.almacenprueba.expose.dto.productDTO.SaveProductRequest;
import com.example.almacenprueba.expose.dto.productDTO.UpdateProductRequest;
import com.example.almacenprueba.persistence.entities.Product;
import com.example.almacenprueba.persistence.repositories.ProductRepository;
import com.example.almacenprueba.services.contracts.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository repository;
    @Override
    public List<ProductResponse> getAll() {

        return repository.findAll()
                .stream().map(product -> mapToResponse(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse findById(Long id) throws NotFoundException {
        Product product=repository.findById(id).orElseThrow(()-> new NotFoundException("Product not found"));
        return mapToResponse(product);
    }

    @Override
    public ProductResponse create(SaveProductRequest saveProductRequest) {

        Product product=mapToEntity(saveProductRequest);
        repository.save(product);
        return mapToResponse(product);
    }

    @Override
    public ProductResponse update(Long id, UpdateProductRequest updateProductRequest) throws NotFoundException {
        Product product=repository.findById(id)
                .orElseThrow(()-> new NotFoundException("Product not found"));
        product.setName_product(updateProductRequest.getName_product());
        product.setShort_description(updateProductRequest.getShort_description());
        product.setLong_description(updateProductRequest.getLong_description());
        product.setUnit_price(updateProductRequest.getUnit_price());
        product.setComments(updateProductRequest.getComments());
        repository.save(product);
        return mapToResponse(product);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ProductResponse mapToResponse(Product product){
        ProductResponse productResponse= new ProductResponse();
        productResponse.setId_product(product.getId_product());
        productResponse.setName_product(product.getName_product());
        productResponse.setShort_description(product.getShort_description());
        productResponse.setLong_description(product.getLong_description());
        productResponse.setUnit_price(product.getUnit_price());
        productResponse.setComments(product.getComments());

        return productResponse;
    }

    private Product mapToEntity(SaveProductRequest saveProductRequest){
        Product product= new Product();
        product.setName_product(saveProductRequest.getName_product());
        product.setComments(saveProductRequest.getComments());
        product.setShort_description(saveProductRequest.getShort_description());
        product.setLong_description(saveProductRequest.getLong_description());
        product.setUnit_price(saveProductRequest.getUnit_price());
        product.setComments(saveProductRequest.getComments());

        return product;
    }

}
