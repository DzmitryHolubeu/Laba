package com.example.testfordima.controllers;

import com.example.testfordima.model.Product;
import com.example.testfordima.model.Stock;
import com.example.testfordima.model.User;
import com.example.testfordima.service.ProductService;
import com.example.testfordima.service.StockService;
import com.example.testfordima.service.StockServiceImpl;
import com.example.testfordima.service.UserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
@SessionAttributes("currentUser")
@RequestMapping("/hello")
public class TestController {
    private final UserDetailsService userDetailsService;
    private StockService stockService;
    private final ProductService productService;


    @GetMapping
    public String test(Model model) {
        model.addAttribute("stocks", stockService.findAll());
        User currentUser = (User) model.getAttribute("currentUser");
        log.info("currentUser {}", currentUser);
        return "test";
    }

    @GetMapping("/admin")
    @Transactional
    public String clientList(Model model) {
        model.addAttribute("users", userDetailsService.findAll());
        model.addAttribute("stocks", stockService.findAll());
        return "adminPage";
    }


    @PostMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        log.info("Deleting user by given id {}", id);
        userDetailsService.deleteById(id);
        return "redirect:/hello/admin";
    }


    @PostMapping("admin/makeAdmin/{id}")
    public String makeAdmin(@PathVariable("id") long id) {
        userDetailsService.makeAdminById(id);
        return "redirect:/hello/admin";
    }

    @GetMapping("admin/addStock")
    public String addStockPage(@ModelAttribute("stock") Stock stock,
                               Model model) {
        log.info("Adding stock...");
        model.addAttribute("stock", stock);
        return "addStock";
    }

    @PostMapping("admin/addStock")
    public String addStock(@ModelAttribute("stock") Stock stock) {
        log.info("Current stock {}", stock);
        stockService.save(stock);
        return "redirect:/hello/admin";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "errorPage";
    }

    @GetMapping("/reserve/{id}")
    public String reservePage(@PathVariable("id") long id,
                              @ModelAttribute("product") Product product) {
        product.setId(id);
        log.info("Reserving stock with id {}", id);
        return "reservePage";
    }

    @PostMapping("/reserve/{id}")
    public String reserve(@PathVariable long id, @ModelAttribute("product") Product product) {


        if (productService.addNewProduct(id, product)) {
            return "redirect:/hello";
        } else {
            return "redirect:/hello/error";
        }
    }
    @GetMapping("/infoPage/{id}")
    public String getInfoPage(@PathVariable("id") int id,Model model) {
        model.addAttribute("stocks", stockService.findStockById(id));
        model.addAttribute("products", productService.findProductsByStockId(id));
        return "stockInfo";
    }
}
