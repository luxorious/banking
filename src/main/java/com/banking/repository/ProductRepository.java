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

/**
 * Repository interface for managing Product entities.
 * This interface extends JpaRepository to provide basic CRUD operations for Product entities.
 */
@Repository
@NonNullApi
public interface ProductRepository extends JpaRepository<Product, UUID> {

    /**
     * Retrieves an optional Product entity based on the product ID.
     *
     * @param uuid The ID of the product to retrieve.
     * @return An optional Product entity with the specified ID.
     */
    Optional<Product> findById(UUID uuid);

    /**
     * Retrieves a list of Product entities based on the product status.
     *
     * @param status The status of the products to retrieve.
     * @return A list of Product entities with the specified status.
     */
    List<Product> findProductsByStatus(ProductStatus status);

    /**
     * Retrieves a list of Product entities based on the currency code.
     *
     * @param currencyCode The currency code of the products to retrieve.
     * @return A list of Product entities with the specified currency code.
     */
    List<Product> findProductsByCurrencyCode(CurrencyCode currencyCode);

    /**
     * Retrieves a list of Product entities based on the interest rate.
     *
     * @param interestRate The interest rate of the products to retrieve.
     * @return A list of Product entities with the specified interest rate.
     */
    List<Product> findProductsByInterestRate(BigDecimal interestRate);

    /**
     * Retrieves a list of Product entities based on the limit.
     *
     * @param limit The limit of the products to retrieve.
     * @return A list of Product entities with the specified limit.
     */
    List<Product> findProductsByLimit(BigDecimal limit);

    /**
     * Retrieves a list of Product entities based on the deleted status.
     *
     * @param deletedStatus The deleted status of the products to retrieve.
     * @return A list of Product entities with the specified deleted status.
     */
    List<Product> findProductByDeletedStatus(DeletedStatus deletedStatus);
}
