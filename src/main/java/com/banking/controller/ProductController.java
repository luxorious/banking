package com.banking.controller;

import com.banking.entity.Product;
import com.banking.entity.entityenumerations.CurrencyCode;
import com.banking.entity.entityenumerations.ProductStatus;
import com.banking.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Product createProduct(Product product){
        return productService.createProduct(product);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Product> findById(@PathVariable UUID id){
        return productService.findById(id);
    }

    @GetMapping("/find/all/status")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProductsByStatus(@RequestParam ProductStatus status){
        return productService.findProductsByStatus(status);
    }

    @GetMapping("/find/all/currency-code")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProductsByCurrencyCode(@RequestParam CurrencyCode currencyCode){
        return productService.findProductsByCurrencyCode(currencyCode);
    }

    @GetMapping("/find/all/interest-rate")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProductsByInterestRate(@RequestParam BigDecimal interestRate){
        return productService.findProductsByInterestRate(interestRate);
    }

    @GetMapping("/find/all/limit")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProductsByLimit(@RequestParam BigDecimal limit){
       return productService.findProductsByLimit(limit);
    }

    @GetMapping("/update/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean updateProductById(@PathVariable UUID id, @RequestParam Product productFromFE){
        return productService.updateProductById(id, productFromFE);
    }

    @GetMapping("/update/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateStatusById(@PathVariable UUID id, @RequestParam ProductStatus status){
        return productService.updateStatusById(id, status);
    }

    @GetMapping("/delete/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product deleteProductById(@PathVariable UUID id){
        return productService.deleteProductById(id);
    }

    @GetMapping("/delete/status")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> deleteProductsByStatus(@RequestParam ProductStatus status){
        return productService.deleteProductsByStatus(status);
    }
}
