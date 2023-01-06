package com.example.testfordima.service;

import com.example.testfordima.model.Product;
import com.example.testfordima.model.Stock;

import java.util.List;

public interface ProductService {

    boolean addNewProduct(long id, Product product);

    Product product = new Product();

    List<Product> findProductsByStockId(int id);
}
