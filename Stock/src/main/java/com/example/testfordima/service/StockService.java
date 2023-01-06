package com.example.testfordima.service;

import com.example.testfordima.model.Stock;

import java.util.List;

public interface StockService {
    List<Stock> findAll();

    Stock findStockById(int id);

    void save(Stock stock);

    void delete(int id);
}
