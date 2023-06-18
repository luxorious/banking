package com.banking.service.implementation;

import com.banking.entity.Account;
import com.banking.entity.Manager;
import com.banking.entity.Product;
import com.banking.entity.entityEnumerations.CurrencyCode;
import com.banking.entity.entityEnumerations.DeletedStatus;
import com.banking.entity.entityEnumerations.ProductStatus;
import com.banking.repository.ProductRepository;
import com.banking.service.interfaces.ProductService;
import com.banking.service.interfaces.utility.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Converter<Product> productConverter;

    @Override
    public Product createProduct(Product product) {
        log.info("product saved");
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(UUID uuid) {
        log.info("find products by id - " + uuid);
        return productRepository.findById(uuid);
    }

    @Override
    public List<Product> findProductsByStatus(ProductStatus status) {
        log.info("find products by where status - " + status);
        return productRepository.findProductsByStatus(status);
    }

    @Override
    public List<Product> findProductsByCurrencyCode(CurrencyCode currencyCode) {
        log.info("find products by currency code - " + currencyCode);
        return productRepository.findProductsByCurrencyCode(currencyCode);
    }

    @Override
    public List<Product> findProductsByInterestRate(Double interestRate) {
        return productRepository.findProductsByInterestRate(interestRate);
    }

    @Override
    public List<Product> findProductsByLimit(Double limit) {
        log.info("find all products where limit = " + limit);
        return productRepository.findProductsByLimit(limit);
    }

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

    @Override
    public List<Product> deleteProductsByStatus(ProductStatus status) {
        List<Product> products = productRepository.findProductsByStatus(status);
        products.forEach(product -> product.setDeletedStatus(DeletedStatus.DELETED));
        log.info("deleting products where product Status - " + status);
        return products;
    }

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

    @Override
    public List<Product> restoreAll() {
        List<Product> toRestore = productRepository.findProductByDeletedStatus(DeletedStatus.DELETED);
        toRestore.forEach(product -> product.setDeletedStatus(DeletedStatus.ACTIVE));
        productRepository.saveAll(toRestore);
        log.info("All products restored");
        return toRestore;
    }

    @Override
    public List<Product> showAllDeleted() {
        log.info("all deleted products");
        return productRepository.findProductByDeletedStatus(DeletedStatus.DELETED);
    }

    @Override
    public List<Product> showAllProductsForAdmin() {
        log.info("all products for admin");
        return productRepository.findAll();
    }
}
