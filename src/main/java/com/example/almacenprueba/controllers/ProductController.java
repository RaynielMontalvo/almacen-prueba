package com.example.almacenprueba.controllers;

import com.example.almacenprueba.exceptions.NotFoundException;
import com.example.almacenprueba.expose.dto.productDTO.ProductResponse;
import com.example.almacenprueba.expose.dto.productDTO.SaveProductRequest;
import com.example.almacenprueba.expose.dto.productDTO.UpdateProductRequest;
import com.example.almacenprueba.services.contracts.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService service;

    //Controlador GetAll
    //@Cacheable("getAll")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll(){
        return new ResponseEntity(service.getAll(), HttpStatus.OK);
    }

    //Controlador GetById
    //@Cacheable("findById")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid SaveProductRequest product) {
        service.create(product);
        return new ResponseEntity(service.create(product), HttpStatus.CREATED);
    }

    //Controlador actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id , @RequestBody UpdateProductRequest product) throws NotFoundException {
        return ResponseEntity.ok(service.update(id, product));
    }

    //Controlador borrar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
