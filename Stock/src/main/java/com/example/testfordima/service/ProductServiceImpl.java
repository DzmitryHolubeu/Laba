package com.example.testfordima.service;

import com.example.testfordima.model.Product;
import com.example.testfordima.model.Stock;
import com.example.testfordima.model.User;
import com.example.testfordima.repository.ProdRepo;
import com.example.testfordima.repository.UsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final StockServiceImpl stockService;
    private final ProdRepo prodRepo;
    private final UserDetailsService userDetailsService;

    @Transactional
    @Override
    public boolean addNewProduct(long id, Product product) {
        Stock stock = stockService.findStockById((int) id);

        if (stock.getCount() + product.getSize() > stock.getMaxCount()||userDetailsService.findUserByUsername(product.getUsername())==null) {
            return false;
        } else {
            product.setStock(stock);
            product.setUser(userDetailsService.findUserByUsername(product.getUsername()));
            prodRepo.save(product);
            stock.setCount(stock.getCount() + product.getSize());
            return true;
        }
    }

    @Transactional
    @Override
    public List<Product> findProductsByStockId(int id) {
        Stock stock = stockService.findStockById(id);

        return stock.getProducts();
    }
}
