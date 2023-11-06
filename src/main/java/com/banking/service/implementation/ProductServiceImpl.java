package com.banking.service.implementation;

import com.banking.entity.Product;
import com.banking.entity.entityenumerations.CurrencyCode;
import com.banking.entity.entityenumerations.DeletedStatus;
import com.banking.entity.entityenumerations.ProductStatus;
import com.banking.repository.ProductRepository;
import com.banking.service.interfaces.ProductService;
import com.banking.service.interfaces.utility.Converter;
import com.banking.service.interfaces.utility.ValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation for managing products.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Converter<Product> productConverter;
    private final ValidatorService<Product> validatorService;

    /**
     * Saves a product to the database.
     *
     * @param product The Product object to be saved.
     * @return The saved Product object.
     */
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    /**
     * Creates a new product with the specified manager ID.
     *
     * @param product   The Product object containing product-related information.
     * @param managerId The UUID of the manager associated with the product.
     * @return The created and saved Product object.
     */
    @Override
    public Product createProduct(Product product, UUID managerId) {
        product.setManagerId(managerId);
        log.info("product saved");
        return productRepository.save(product);
    }

    /**
     * Finds the product with the specified ID.
     *
     * @param uuid The UUID of the product to find.
     * @return The Product object with the specified ID, if found.
     */
    @Override
    public Product findById(UUID uuid) {
        log.info("find products by id - " + uuid);
        return validatorService.checkEntity(productRepository.findById(uuid));
    }

    /**
     * Finds all products with the specified status.
     *
     * @param status The status of the products to find.
     * @return The list of products with the specified status.
     */
    @Override
    public List<Product> findProductsByStatus(ProductStatus status) {
        log.info("find products by where status - " + status);
        return validatorService.checkList(productRepository.findProductsByStatus(status));
    }

    /**
     * Finds all products with the specified currency code.
     *
     * @param currencyCode The currency code of the products to find.
     * @return The list of products with the specified currency code.
     */
    @Override
    public List<Product> findProductsByCurrencyCode(CurrencyCode currencyCode) {
        log.info("find products by currency code - " + currencyCode);
        return validatorService.checkList(productRepository.findProductsByCurrencyCode(currencyCode));
    }

    /**
     * Finds all products with the specified interest rate.
     *
     * @param interestRate The interest rate of the products to find.
     * @return The list of products with the specified interest rate.
     */
    @Override
    public List<Product> findProductsByInterestRate(BigDecimal interestRate) {
        return validatorService.checkList(productRepository.findProductsByInterestRate(interestRate));
    }

    /**
     * Finds all products with the specified limit.
     *
     * @param limit The limit of the products to find.
     * @return The list of products with the specified limit.
     */
    @Override
    public List<Product> findProductsByLimit(BigDecimal limit) {
        log.info("find all products where limit = " + limit);
        return validatorService.checkList(productRepository.findProductsByLimit(limit));
    }

    /**
     * Updates the product with the specified ID using the data from the provided Product object.
     *
     * @param id            The UUID of the product to update.
     * @param productFromFE The updated Product object containing the new product information.
     * @return True if the product was updated successfully, false otherwise.
     */
    @Override
    public Boolean updateProductById(UUID id, Product productFromFE) {
        Optional<Product> productFromDB = productRepository.findById(id);
        if (productFromDB.isPresent()) {
            Product manager = productConverter.convertFields(productFromDB.get(), productFromFE);
            productRepository.save(manager);
            log.info("Manager info updated!");
            return true;
        } else {
            log.info("Manager with id - " + id + " not found ");
            return false;
        }
    }

    /**
     * Updates the status of the product with the specified ID.
     *
     * @param id     The UUID of the product to update.
     * @param status The new status of the product.
     * @return The updated Product object.
     */
    @Override
    public Product updateStatusById(UUID id, ProductStatus status) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product changedProduct = product.get();
            changedProduct.setStatus(status);
            productRepository.save(changedProduct);
            log.info("product status updated");
            return changedProduct;
        } else {
            log.error("product not found!");
            return new Product();
        }
    }

    /**
     * Deletes the product with the specified ID.
     *
     * @param id The UUID of the product to delete.
     * @return The deleted Product object.
     */
    @Override
    public Product deleteProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product changedProduct = product.get();
            changedProduct.setDeletedStatus(DeletedStatus.DELETED);
            productRepository.save(changedProduct);
            log.info("product deleted!");
            return changedProduct;
        } else {
            log.error("manager not found!");
            return new Product();
        }
    }

    /**
     * Deletes all products with the specified status.
     *
     * @param status The status of the products to delete.
     * @return The list of deleted products.
     */
    @Override
    public List<Product> deleteProductsByStatus(ProductStatus status) {
        List<Product> products = productRepository.findProductsByStatus(status);
        products.forEach(product -> product.setDeletedStatus(DeletedStatus.DELETED));
        log.info("deleting products where product Status - " + status);
        return products;
    }

    /**
     * Restores the product with the specified ID.
     *
     * @param id The UUID of the product to restore.
     * @return The restored Product object.
     */
    @Override
    public Product restoreById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product productToRestore = product.get();
            productToRestore.setDeletedStatus(DeletedStatus.ACTIVE);
            productRepository.save(productToRestore);
            log.info("product restored");
            return productToRestore;
        } else {
            log.error("product not found");
            return new Product();
        }
    }

    /**
     * Restores all deleted products.
     *
     * @return The list of restored products.
     */
    @Override
    public List<Product> restoreAll() {
        List<Product> toRestore = productRepository.findProductByDeletedStatus(DeletedStatus.DELETED);
        toRestore.forEach(product -> product.setDeletedStatus(DeletedStatus.ACTIVE));
        productRepository.saveAll(toRestore);
        log.info("All products restored");
        return toRestore;
    }

    /**
     * Shows all deleted products.
     *
     * @return The list of all deleted products.
     */
    @Override
    public List<Product> showAllDeleted() {
        log.info("all deleted products");
        return productRepository.findProductByDeletedStatus(DeletedStatus.DELETED);
    }

    /**
     * Shows all products for admin purposes.
     *
     * @return The list of all products for admin purposes.
     */
    @Override
    public List<Product> showAllProductsForAdmin() {
        log.info("all products for admin");
        return productRepository.findAll();
    }
}
