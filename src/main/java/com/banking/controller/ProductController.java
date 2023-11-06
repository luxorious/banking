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
import java.util.UUID;

/**
 * The ProductController class is a RESTful controller that handles HTTP requests related to products.
 * It provides endpoints to create, retrieve, update, and delete products in the banking system.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Create a new product.
     *
     * @param product The product object to be created.
     * @param managerId The ID of the manager associated with the product.
     * @return The created product.
     */
    @PostMapping("/create/{managerId}")
    @ResponseStatus(HttpStatus.OK)
    public Product createProduct(@RequestBody Product product, @PathVariable UUID managerId) {
        return productService.createProduct(product, managerId);
    }

    /**
     * Find a product by its ID.
     *
     * @param id The ID of the product to find.
     * @return The product with the provided ID.
     */
    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product findById(@PathVariable UUID id) {
        return productService.findById(id);
    }

    /**
     * Find products with the provided status.
     *
     * @param status The status to filter the products.
     * @return A list of products that match the provided status.
     */
    @GetMapping("/find/all/status")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProductsByStatus(@RequestParam ProductStatus status) {
        return productService.findProductsByStatus(status);
    }

    /**
     * Find products with the provided currency code.
     *
     * @param currencyCode The currency code to filter the products.
     * @return A list of products that match the provided currency code.
     */
    @GetMapping("/find/all/currency-code")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProductsByCurrencyCode(@RequestParam CurrencyCode currencyCode) {
        return productService.findProductsByCurrencyCode(currencyCode);
    }

    /**
     * Find products with the provided interest rate.
     *
     * @param interestRate The interest rate to filter the products.
     * @return A list of products that match the provided interest rate.
     */
    @GetMapping("/find/all/interest-rate")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProductsByInterestRate(@RequestParam BigDecimal interestRate) {
        return productService.findProductsByInterestRate(interestRate);
    }

    /**
     * Find products with the provided limit.
     *
     * @param limit The limit to filter the products.
     * @return A list of products that match the provided limit.
     */
    @GetMapping("/find/all/limit")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findProductsByLimit(@RequestParam BigDecimal limit) {
        return productService.findProductsByLimit(limit);
    }

    /**
     * Update a product with the provided ID.
     *
     * @param id The ID of the product to update.
     * @param productFromFE The updated product object.
     * @return true if the update was successful, false otherwise.
     */
    @GetMapping("/update/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean updateProductById(@PathVariable UUID id, @RequestParam Product productFromFE) {
        return productService.updateProductById(id, productFromFE);
    }

    /**
     * Update the status of a product with the provided ID.
     *
     * @param id The ID of the product to update.
     * @param status The new status for the product.
     * @return The product with the updated status.
     */
    @GetMapping("/update/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateStatusById(@PathVariable UUID id, @RequestParam ProductStatus status) {
        return productService.updateStatusById(id, status);
    }

    /**
     * Delete a product by its ID.
     *
     * @param id The ID of the product to delete.
     * @return The deleted product.
     */
    @GetMapping("/delete/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product deleteProductById(@PathVariable UUID id) {
        return productService.deleteProductById(id);
    }

    /**
     * Delete products with the provided status.
     *
     * @param status The status to filter the products to be deleted.
     * @return A list of products that were deleted.
     */
    @GetMapping("/delete/status")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> deleteProductsByStatus(@RequestParam ProductStatus status) {
        return productService.deleteProductsByStatus(status);
    }
}
