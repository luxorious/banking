package com.banking.repository;

import com.banking.entity.Product;
import com.banking.entity.entityEnumerations.CurrencyCode;
import com.banking.entity.entityEnumerations.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Override
    Optional<Product> findById(UUID uuid);

    List<Product> findProductByStatus(ProductStatus status);

    List<Product> findProductByCurrencyCode(CurrencyCode currencyCode);

    List<Product> findProductByInterestRate(Double interestRate);

    List<Product> findProductByLimit(Double limit);
}
