package com.example.almacenprueba.services.mapper;

import com.example.almacenprueba.expose.dto.productDTO.ProductResponse;
import com.example.almacenprueba.persistence.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductResponse source);
    ProductResponse toDto(Product target);
}
