package com.example.almacenprueba.controllers;

import com.example.almacenprueba.exceptions.NotFoundException;
import com.example.almacenprueba.expose.dto.stockDTO.SaveStockRequest;
import com.example.almacenprueba.expose.dto.stockDTO.StockResponse;
import com.example.almacenprueba.expose.dto.stockDTO.UpdateStockRequest;
import com.example.almacenprueba.services.contracts.IStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/stocks")
@RequiredArgsConstructor
public class StockController {
    private final IStockService service;

    //Controlador GetAll
    @GetMapping
    public ResponseEntity<List<StockResponse>> getAll(){
        return new ResponseEntity(service.getAll(), HttpStatus.OK);
    }

    //Controlador GetById
    @GetMapping("/{id}")
    public ResponseEntity<StockResponse> findById(@PathVariable Long id) throws NotFoundException {
        //return ResponseEntity.ok(service.findById(id));  //esto esta bien
        StockResponse stockResponse = service.findById(id); //agregado para que salga en la tabla del buscador
        if (stockResponse != null) {
            return ResponseEntity.ok(stockResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("id_product/{id}")
    public ResponseEntity<List<StockResponse>> findByProductId(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(service.getStockListByProductName(id));
    }

    @PostMapping
    public ResponseEntity<StockResponse> create(@RequestBody @Valid SaveStockRequest stock) throws NotFoundException {
        service.create(stock);
        return new ResponseEntity(service.create(stock), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockResponse> update(@PathVariable Long id , @RequestBody UpdateStockRequest stock) throws NotFoundException {
        return ResponseEntity.ok(service.update(id, stock));
    }

    //Controlador borrar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
