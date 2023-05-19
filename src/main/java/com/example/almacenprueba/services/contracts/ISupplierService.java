package com.example.almacenprueba.services.contracts;

import com.example.almacenprueba.exceptions.NotFoundException;
import com.example.almacenprueba.expose.dto.supplierDTO.SaveSupplierRequest;
import com.example.almacenprueba.expose.dto.supplierDTO.SupplierResponse;
import com.example.almacenprueba.expose.dto.supplierDTO.UpdateSupplierRequest;

import java.util.List;

public interface ISupplierService {
    List<SupplierResponse> getAll();
    SupplierResponse findById(Long id) throws NotFoundException;
    SupplierResponse create(SaveSupplierRequest supplierRequest);

    SupplierResponse update(Long id , UpdateSupplierRequest updateSupplierRequest) throws NotFoundException;

    void delete(Long id);
}
