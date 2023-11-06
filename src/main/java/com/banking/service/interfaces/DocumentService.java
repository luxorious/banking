package com.banking.service.interfaces;

import com.banking.entity.Document;

import java.util.UUID;

public interface DocumentService {
    /**
     * Saves a new or existing document to the database.
     *
     * @param document The document to be saved.
     * @return The saved document.
     */
    Document save(Document document);

    /**
     * Creates a new document for the specified client ID and associates it with the client.
     *
     * @param document The document to be created.
     * @param clientId The ID of the client associated with the document.
     * @return The created document.
     */
    Document create(Document document, UUID clientId);

    /**
     * Retrieves the document associated with the specified client ID.
     *
     * @param id The ID of the client whose document is to be retrieved.
     * @return The document associated with the specified client ID.
     */
    Document findDocumentByClientId(UUID id);

    /**
     * Edits the document associated with the specified client ID using the provided document data from the front-end.
     *
     * @param clientId   The ID of the client whose document is to be edited.
     * @param documentFE The document data from the front-end to update the existing document.
     * @return The updated document.
     */
    Document edit(UUID clientId, Document documentFE);
}
