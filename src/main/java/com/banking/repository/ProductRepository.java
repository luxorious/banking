package com.banking.repository;

import com.banking.entity.Product;
import com.banking.entity.entityenumerations.CurrencyCode;
import com.banking.entity.entityenumerations.DeletedStatus;
import com.banking.entity.entityenumerations.ProductStatus;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@NonNullApi
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findById(UUID uuid);

    List<Product> findProductsByStatus(ProductStatus status);

    List<Product> findProductsByCurrencyCode(CurrencyCode currencyCode);

    List<Product> findProductsByInterestRate(BigDecimal interestRate);

    List<Product> findProductsByLimit(BigDecimal limit);

    List<Product> findProductByDeletedStatus(DeletedStatus deletedStatus);
}
