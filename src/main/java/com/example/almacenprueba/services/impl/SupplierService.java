package com.example.almacenprueba.services.impl;

import com.example.almacenprueba.exceptions.NotFoundException;
import com.example.almacenprueba.expose.dto.supplierDTO.SaveSupplierRequest;
import com.example.almacenprueba.expose.dto.supplierDTO.SupplierResponse;
import com.example.almacenprueba.expose.dto.supplierDTO.UpdateSupplierRequest;
import com.example.almacenprueba.persistence.entities.Supplier;
import com.example.almacenprueba.persistence.repositories.SupplierRepository;
import com.example.almacenprueba.services.contracts.ISupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class SupplierService implements ISupplierService {
    private final SupplierRepository repository;


    @Override
    public List<SupplierResponse> getAll() {
        return repository.findAll()
                .stream().map(supplier -> mapToResponse(supplier))
                .collect(Collectors.toList());
    }

    @Override
    public SupplierResponse findById(Long id) throws NotFoundException {
        Supplier supplier= repository.findById(id).orElseThrow(()-> new NotFoundException("Supplier not found"));
        return mapToResponse(supplier);
    }

    @Override
    public SupplierResponse create(SaveSupplierRequest supplierRequest) {
        Supplier supplier=mapToEntity(supplierRequest);
        repository.save(supplier);
        return mapToResponse(supplier);
    }

    @Override
    public SupplierResponse update(Long id, UpdateSupplierRequest updateSupplierRequest) throws NotFoundException {
        Supplier supplier=repository.findById(id).orElseThrow(()-> new NotFoundException("Supplier not found"));
        supplier.setSupplier_name(updateSupplierRequest.getSupplier_name());
        supplier.setAddress_1(updateSupplierRequest.getAddress_1());
        supplier.setAddress_2(updateSupplierRequest.getAddress_2());
        supplier.setAddress_3(updateSupplierRequest.getAddress_3());
        supplier.setCity(updateSupplierRequest.getCity());
        supplier.setComments(updateSupplierRequest.getComments());
        supplier.setContact_email(updateSupplierRequest.getContact_email());
        supplier.setContact_name(updateSupplierRequest.getContact_name());
        supplier.setContact_phone(updateSupplierRequest.getContact_phone());
        supplier.setCountry(updateSupplierRequest.getCountry());
        supplier.setPostcode(updateSupplierRequest.getPostcode());

        repository.save(supplier);
        return mapToResponse(supplier);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private SupplierResponse mapToResponse(Supplier supplier){
        SupplierResponse supplierResponse= new SupplierResponse();
        supplierResponse.setSupplier_name(supplier.getSupplier_name());
        supplierResponse.setId_supplier(supplier.getId_supplier());
        supplierResponse.setAddress_1(supplier.getAddress_1());
        supplierResponse.setAddress_2(supplier.getAddress_2());
        supplierResponse.setAddress_3(supplier.getAddress_3());
        supplierResponse.setCity(supplier.getCity());
        supplierResponse.setComments(supplier.getComments());
        supplierResponse.setContact_email(supplier.getContact_email());
        supplierResponse.setContact_name(supplier.getContact_name());
        supplierResponse.setContact_phone(supplier.getContact_phone());
        supplierResponse.setCountry(supplier.getCountry());
        supplierResponse.setPostcode(supplier.getPostcode());

        return supplierResponse;
    }

    private Supplier mapToEntity(SaveSupplierRequest saveSupplierRequest){
        Supplier supplier= new Supplier();

        supplier.setSupplier_name(saveSupplierRequest.getSupplier_name());
        supplier.setAddress_1(saveSupplierRequest.getAddress_1());
        supplier.setAddress_2(saveSupplierRequest.getAddress_2());
        supplier.setAddress_3(saveSupplierRequest.getAddress_3());
        supplier.setCity(saveSupplierRequest.getCity());
        supplier.setComments(saveSupplierRequest.getComments());
        supplier.setContact_email(saveSupplierRequest.getContact_email());
        supplier.setContact_name(saveSupplierRequest.getContact_name());
        supplier.setContact_phone(saveSupplierRequest.getContact_phone());
        supplier.setCountry(saveSupplierRequest.getCountry());
        supplier.setPostcode(saveSupplierRequest.getPostcode());

        return supplier;
    }
}
