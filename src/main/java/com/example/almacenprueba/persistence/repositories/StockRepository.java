package com.example.almacenprueba.persistence.repositories;

import com.example.almacenprueba.persistence.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

    @Query(value = "SELECT * FROM stock WHERE id_product= ?",nativeQuery = true)
    List<Stock> getStockListName(Long id);
}
