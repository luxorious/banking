package com.banking.service.interfaces;

import com.banking.entity.Document;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.UUID;

public interface DocumentService {

    Document create(Document document);

    Document findDocumentByClientId(UUID id);

    Document findDocumentById(Integer id);

    Document edit(UUID clientId, Document documentFE);

    Document editImageById(Integer id, MultipartFile image, boolean isPassport) throws IOException;

}
