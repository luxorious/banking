package com.banking.service.implementation;

import com.banking.entity.Document;
import com.banking.exception.BadAccountDataException;
import com.banking.repository.DocumentRepository;
import com.banking.service.interfaces.DocumentService;
import com.banking.service.interfaces.utility.Converter;
import com.banking.service.interfaces.utility.GetEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * Service implementation for managing documents.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final GetEntity<Document> getDocument;
    private final Converter<Document> converter;

    /**
     * Saves a document to the repository.
     *
     * @param document The document to be saved.
     * @return The saved document.
     */
    @Override
    public Document save(Document document) {
        return documentRepository.save(document);
    }

    /**
     * Creates a new document for the specified client with the provided data.
     *
     * @param document The Document object containing document-related information.
     * @param clientId The UUID of the client associated with the document.
     * @return The created and saved Document object.
     */
    @Override
    public Document create(Document document, UUID clientId) {
        document.setClientId(clientId);
        log.info("document with id - " + document.getId() + " saved");
        return documentRepository.save(document);
    }

    /**
     * Finds the document associated with the specified client.
     *
     * @param id The UUID of the client to find the document for.
     * @return The document associated with the specified client.
     */
    @Override
    public Document findDocumentByClientId(UUID id) {
        return getDocument.getEntity(documentRepository.findDocumentByClientId(id));
    }

    /**
     * Finds the document with the specified ID.
     *
     * @param id The ID of the document to find.
     * @return The document with the specified ID.
     */
    @Override
    public Document findDocumentById(Integer id) {
        return getDocument.getEntity(documentRepository.findDocumentById(id));
    }

    /**
     * Edits the document associated with the specified client.
     *
     * @param clientId   The UUID of the client to edit the document for.
     * @param documentFE The updated Document object containing the new document information.
     * @return The updated Document object.
     * @throws BadAccountDataException if there was an error during the document update.
     */
    @Override
    public Document edit(UUID clientId, Document documentFE) {
        Document documentDB = getDocument.getEntity(documentRepository.findDocumentByClientId(clientId));
        Document updatedDocument = converter.convertFields(documentDB, documentFE);
        if (!documentDB.equals(documentFE)) {
            documentRepository.save(updatedDocument);
            log.info("document with id - '" + updatedDocument.getId() + "' updated");
            return updatedDocument;
        } else {
            log.error("something went wrong");
            throw new BadAccountDataException("Error");
        }
    }

    /**
     * Edits the image of the document with the specified ID.
     *
     * @param id         The ID of the document to edit the image for.
     * @param image      The new image data as a MultipartFile.
     * @param isPassport A boolean flag indicating whether the image is for the passport or registration.
     * @return The updated Document object.
     * @throws IOException if there was an error reading the image data.
     */
    @Override
    public Document editImageById(Integer id, MultipartFile image, boolean isPassport) throws IOException {
        byte[] imgBytes = image.getBytes();
        Document document = getDocument.getEntity(documentRepository.findDocumentById(id));
        if (isPassport) {
            document.setPassport(imgBytes);
        } else {
            document.setRegistration(imgBytes);
        }
        documentRepository.save(document);
        return document;
    }
}
