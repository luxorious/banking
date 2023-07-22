package com.banking.service.interfaces;

import com.banking.entity.Product;
import com.banking.entity.entityenumerations.CurrencyCode;
import com.banking.entity.entityenumerations.ProductStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    /**
     * Saves the given product to the database.
     *
     * @param product The product to be saved.
     * @return The saved product.
     */
    Product save(Product product);

    /**
     * Creates a new product associated with the specified manager and saves it to the database.
     *
     * @param product   The product to be created.
     * @param managerId The ID of the manager associated with the product.
     * @return The created product.
     */
    Product createProduct(Product product, UUID managerId);

    /**
     * Retrieves the product with the specified ID.
     *
     * @param uuid The ID of the product to be retrieved.
     * @return The product with the specified ID.
     */
    Product findById(UUID uuid);

    /**
     * Retrieves a list of products with the specified status.
     *
     * @param status The status of the products to be retrieved (e.g., active, inactive).
     * @return The list of products with the specified status.
     */
    List<Product> findProductsByStatus(ProductStatus status);

    /**
     * Retrieves a list of products with the specified currency code.
     *
     * @param currencyCode The currency code of the products to be retrieved (e.g., USD, EUR).
     * @return The list of products with the specified currency code.
     */
    List<Product> findProductsByCurrencyCode(CurrencyCode currencyCode);

    /**
     * Retrieves a list of products with the specified interest rate.
     *
     * @param interestRate The interest rate of the products to be retrieved.
     * @return The list of products with the specified interest rate.
     */
    List<Product> findProductsByInterestRate(BigDecimal interestRate);

    /**
     * Retrieves a list of products with the specified limit.
     *
     * @param limit The limit of the products to be retrieved.
     * @return The list of products with the specified limit.
     */
    List<Product> findProductsByLimit(BigDecimal limit);

    /**
     * Updates the information of the product with the specified ID using the provided product data from the front-end.
     *
     * @param id            The ID of the product to be updated.
     * @param productFromFE The product data from the front-end to update the existing product.
     * @return True if the product was successfully updated, false otherwise.
     */
    Boolean updateProductById(UUID id, Product productFromFE);

    /**
     * Updates the status of the product with the specified ID.
     *
     * @param id     The ID of the product to update the status.
     * @param status The new status of the product (e.g., active, inactive).
     * @return The updated product with the new status.
     */
    Product updateStatusById(UUID id, ProductStatus status);

    /**
     * Deletes the product with the specified ID.
     *
     * @param id The ID of the product to be deleted.
     * @return The deleted product.
     */
    Product deleteProductById(UUID id);

    /**
     * Deletes all products with the specified status.
     *
     * @param status The status of the products to be deleted (e.g., active, inactive).
     * @return The list of deleted products.
     */
    List<Product> deleteProductsByStatus(ProductStatus status);

    /**
     * Restores the product with the specified ID by setting its status to active.
     *
     * @param id The ID of the product to be restored.
     * @return The restored product.
     */
    Product restoreById(UUID id);

    /**
     * Restores all products that were previously deleted.
     *
     * @return The list of restored products.
     */
    List<Product> restoreAll();

    /**
     * Retrieves a list of all products with a deleted status.
     *
     * @return The list of all products with a deleted status.
     */
    List<Product> showAllDeleted();

    /**
     * Retrieves a list of all products for administrative purposes.
     *
     * @return The list of all products for administrative purposes.
     */
    List<Product> showAllProductsForAdmin();
}
