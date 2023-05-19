package com.example.almacenprueba.controllers;

import com.example.almacenprueba.exceptions.NotFoundException;
import com.example.almacenprueba.expose.dto.supplierDTO.SaveSupplierRequest;
import com.example.almacenprueba.expose.dto.supplierDTO.SupplierResponse;
import com.example.almacenprueba.expose.dto.supplierDTO.UpdateSupplierRequest;
import com.example.almacenprueba.services.contracts.ISupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final ISupplierService service;


    //Controlador GetAll
    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAll(){
        return new ResponseEntity(service.getAll(), HttpStatus.OK);
    }

    //Controlador GetById
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> findById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SupplierResponse> create(@RequestBody @Valid SaveSupplierRequest supplier) {
        service.create(supplier);
        return new ResponseEntity(service.create(supplier), HttpStatus.CREATED);
    }

    //Controlador actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponse> update(@PathVariable Long id , @RequestBody UpdateSupplierRequest supplier) throws NotFoundException {
        return ResponseEntity.ok(service.update(id, supplier));
    }

    //Controlador borrar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
