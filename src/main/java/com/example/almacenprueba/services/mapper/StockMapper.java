package com.example.almacenprueba.services.mapper;

import com.example.almacenprueba.expose.dto.stockDTO.StockResponse;
import com.example.almacenprueba.persistence.entities.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {
    Stock toEntity(StockResponse source);
    StockResponse toDto(Stock target);
}
