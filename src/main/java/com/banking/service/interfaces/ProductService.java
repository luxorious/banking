package com.banking.service.interfaces;

import com.banking.entity.Manager;
import com.banking.entity.Product;
import com.banking.entity.entityEnumerations.CurrencyCode;
import com.banking.entity.entityEnumerations.ManagerStatus;
import com.banking.entity.entityEnumerations.ProductStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    Product createProduct(Product product);

    Optional<Product> findById(UUID uuid);

    List<Product> findProductsByStatus(ProductStatus status);

    List<Product> findProductsByCurrencyCode(CurrencyCode currencyCode);

    List<Product> findProductsByInterestRate(Double interestRate);

    List<Product> findProductsByLimit(Double limit);

    Boolean updateProductById(UUID id, Product productFromFE);

    Product updateStatusById(UUID id, ProductStatus status);

    Product deleteProductById(UUID id);

    List<Product> deleteProductsByStatus(ProductStatus status);

    Product restoreById(UUID id);

    List<Product> restoreAll();

    List<Product> showAllDeleted();

    List<Product> showAllProductsForAdmin();
}
