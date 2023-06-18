package com.banking.repository;

import com.banking.entity.Product;
import com.banking.entity.entityEnumerations.CurrencyCode;
import com.banking.entity.entityEnumerations.DeletedStatus;
import com.banking.entity.entityEnumerations.ProductStatus;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@NonNullApi
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findById(UUID uuid);

    List<Product> findProductsByStatus(ProductStatus status);

    List<Product> findProductsByCurrencyCode(CurrencyCode currencyCode);

    List<Product> findProductsByInterestRate(Double interestRate);

    List<Product> findProductsByLimit(Double limit);

    List<Product> findProductByDeletedStatus(DeletedStatus deletedStatus);
}
