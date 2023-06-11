package com.banking.service.interfaces;

import com.banking.entity.Product;
import com.banking.entity.entityEnumerations.CurrencyCode;
import com.banking.entity.entityEnumerations.ProductStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    Product createProduct(Product product);

    Optional<Product> findById(UUID uuid);

    List<Product> findProductByStatus(ProductStatus status);

    List<Product> findProductByCurrencyCode(CurrencyCode currencyCode);

    List<Product> findProductByInterestRate(Double interestRate);

    List<Product> findProductByLimit(Double limit);
}
