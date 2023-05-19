package com.example.almacenprueba.services.impl;

import com.example.almacenprueba.exceptions.NotFoundException;
import com.example.almacenprueba.expose.dto.productDTO.ProductResponse;
import com.example.almacenprueba.expose.dto.stockDTO.SaveStockRequest;
import com.example.almacenprueba.expose.dto.stockDTO.StockResponse;
import com.example.almacenprueba.expose.dto.stockDTO.UpdateStockRequest;
import com.example.almacenprueba.persistence.entities.Product;
import com.example.almacenprueba.persistence.entities.Stock;
import com.example.almacenprueba.persistence.repositories.ProductRepository;
import com.example.almacenprueba.persistence.repositories.StockRepository;
import com.example.almacenprueba.services.contracts.IStockService;
import com.example.almacenprueba.services.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService implements IStockService {
    private final StockRepository repository;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    @Override
    public List<StockResponse> getAll() {
        return repository.findAll()
                .stream().map(stock -> mapToResponse(stock))
                .collect(Collectors.toList());
    }

    @Override
    public StockResponse findById(Long id) throws NotFoundException {
        Stock stock= repository.findById(id).orElseThrow(()-> new NotFoundException("Stock not found."));
        return mapToResponse(stock);
    }

    //Realizar las creaciones con "id_product": {número} en el JSON
    @Override
    public StockResponse create(SaveStockRequest saveStockRequest) throws NotFoundException {
        Stock stock=mapToEntity(saveStockRequest);
        repository.save(stock);
        return mapToResponse(stock);
    }

    @Override
    public StockResponse update(Long id, UpdateStockRequest updateStockRequest) throws NotFoundException {
        Stock stock=repository.findById(id)
                .orElseThrow(()-> new NotFoundException("Stock not found"));
        stock.setProduct(findProductById(updateStockRequest.getId_product()));
        repository.save(stock);
        return mapToResponse(stock);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<StockResponse> getStockListByProductName(Long id) {
        return repository.getStockListName(id).stream().map(stock -> mapToResponse(stock))
                .collect(Collectors.toList());
    }

    private StockResponse mapToResponse(Stock stock){
        StockResponse stockResponse= new StockResponse();
        stockResponse.setId_stock(stock.getId_stock());
        stockResponse.setProduct(mapToProductResponse(stock.getProduct()));
        return stockResponse;
    }

    private ProductResponse mapToProductResponse(Product product) {
        return productMapper.toDto(product);
    }

    private Stock mapToEntity(SaveStockRequest saveStockRequest) throws NotFoundException {
        Stock stock= new Stock();
        stock.setProduct(findProductById(saveStockRequest.getId_product()));
        return stock;
    }

    private Product findProductById(Long idProduct) throws NotFoundException {
        return productRepository.findById(idProduct)
                .orElseThrow(()->new NotFoundException("Product not found"));
    }
}
