package com.banking.service.implementation;

import com.banking.entity.Document;
import com.banking.exception.BadAccountData;
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

@Slf4j
@RequiredArgsConstructor
@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final GetEntity<Document> getDocument;
    private final Converter<Document> converter;

    @Override
    public Document create(Document document) {
        log.info("document with id - " + document.getId() + " saved");
        return documentRepository.save(document);
    }

    @Override
    public Document findDocumentByClientId(UUID id) {
        return getDocument.getEntity(documentRepository.findDocumentByClientId(id));
    }

    @Override
    public Document findDocumentById(Integer id) {
        return getDocument.getEntity(documentRepository.findDocumentById(id));
    }

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
            throw new BadAccountData("Error");
        }
    }

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
