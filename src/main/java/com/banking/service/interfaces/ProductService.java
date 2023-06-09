package com.banking.service.interfaces;

import com.banking.entity.Product;
import com.banking.entity.entityenumerations.CurrencyCode;
import com.banking.entity.entityenumerations.ProductStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product createProduct(Product product);

    Product findById(UUID uuid);

    List<Product> findProductsByStatus(ProductStatus status);

    List<Product> findProductsByCurrencyCode(CurrencyCode currencyCode);

    List<Product> findProductsByInterestRate(BigDecimal interestRate);

    List<Product> findProductsByLimit(BigDecimal limit);

    Boolean updateProductById(UUID id, Product productFromFE);

    Product updateStatusById(UUID id, ProductStatus status);

    Product deleteProductById(UUID id);

    List<Product> deleteProductsByStatus(ProductStatus status);

    Product restoreById(UUID id);

    List<Product> restoreAll();

    List<Product> showAllDeleted();

    List<Product> showAllProductsForAdmin();
}
