package com.banking.repository;

import com.banking.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Optional<Document> findDocumentByClientId(UUID id);

    Optional<Document> findDocumentById(Integer id);
}
