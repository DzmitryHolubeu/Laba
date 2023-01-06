package com.example.testfordima.repository;

import com.example.testfordima.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepo extends JpaRepository<Stock,Integer> {
    List<Stock> findAll();

    void deleteById(long id);

    Stock findById(long id);

 //   Stock save(Stock stock);


    @Override
    <S extends Stock> S save(S entity);
}
