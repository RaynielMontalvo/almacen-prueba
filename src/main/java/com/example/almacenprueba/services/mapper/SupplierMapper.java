package com.example.almacenprueba.services.mapper;

import com.example.almacenprueba.expose.dto.supplierDTO.SupplierResponse;
import com.example.almacenprueba.persistence.entities.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    Supplier toEntity(SupplierResponse source);

    SupplierResponse toDto(Supplier target);
}
