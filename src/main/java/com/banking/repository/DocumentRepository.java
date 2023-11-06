package com.banking.repository;

import com.banking.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing Document entities.
 * This interface extends JpaRepository to provide basic CRUD operations for Document entities.
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    /**
     * Retrieves an optional Document entity based on the client ID.
     *
     * @param id The ID of the client to retrieve the document for.
     * @return An optional Document entity associated with the specified client ID.
     */
    Optional<Document> findDocumentByClientId(UUID id);

    /**
     * Retrieves an optional Document entity based on the document ID.
     *
     * @param id The ID of the document to retrieve.
     * @return An optional Document entity with the specified ID.
     */
    Optional<Document> findDocumentById(Integer id);
}
