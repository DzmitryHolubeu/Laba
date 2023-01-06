package com.example.testfordima.service;

import com.example.testfordima.model.Stock;
import com.example.testfordima.repository.StockRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepo stockRepo;

    public StockServiceImpl(StockRepo stockRepo) {
        this.stockRepo = stockRepo;
    }

    @Override
    public List<Stock> findAll() {
        return stockRepo.findAll();
    }

    @Override
    public Stock findStockById(int id) {
        return stockRepo.findById(id);
    }


    @Override
    public void save(Stock stock) {
        stockRepo.save(stock);
    }

    @Transactional
    @Override
    public void delete(int id) {
        stockRepo.deleteById(id);
    }

//
//    public List<Stock> findAll() {
//        return stockRepo.findAll();
//    }
//
//
//    public void deleteById(long id) {
//        stockRepo.deleteById(id);
//    }
//
//
//    public Stock findById(long id) {
//        return stockRepo.findById(id);
//    }


}
