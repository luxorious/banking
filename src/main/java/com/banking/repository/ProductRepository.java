package com.banking.repository;

import com.banking.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository{//} extends JpaRepository<Product, UUID> {

    Optional<Product> findProductById(UUID id);
}
