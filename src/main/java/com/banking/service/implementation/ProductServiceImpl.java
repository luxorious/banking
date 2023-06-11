package com.banking.service.implementation;

import com.banking.entity.Product;
import com.banking.entity.entityEnumerations.CurrencyCode;
import com.banking.entity.entityEnumerations.ProductStatus;
import com.banking.repository.ProductRepository;
import com.banking.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(UUID uuid) {
        return productRepository.findById(uuid);
    }

    @Override
    public List<Product> findProductByStatus(ProductStatus status) {
        return productRepository.findProductByStatus(status);
    }

    @Override
    public List<Product> findProductByCurrencyCode(CurrencyCode currencyCode) {
        return productRepository.findProductByCurrencyCode(currencyCode);
    }

    @Override
    public List<Product> findProductByInterestRate(Double interestRate) {
        return productRepository.findProductByInterestRate(interestRate);
    }

    @Override
    public List<Product> findProductByLimit(Double limit) {
        return productRepository.findProductByLimit(limit);
    }
}
