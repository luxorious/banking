package com.banking.service.implementation.utility;

import com.banking.entity.Document;
import com.banking.service.interfaces.utility.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class DocumentConverterImpl implements Converter<Document> {
    @Override
    public Document copyObjects(Document documentFromDB) {
        Document documentCopy = new Document();
        try {
            BeanUtils.copyProperties(documentCopy, documentFromDB);
        } catch (Exception e) {
            log.error("Wrong type of Account");
        }
        return documentCopy;
    }

    @Override
    public Document convertFields(Document documentFromDB, Document documentFromFE) {
        Document document = copyObjects(documentFromDB);

        if (documentFromFE.getPassport() != null &&
                documentFromDB.getPassport() != documentFromFE.getPassport()) {
            document.setPassport(documentFromFE.getPassport());
            log.info("client's passport was changed" + Arrays.toString(documentFromFE.getPassport()));
        }
        if (documentFromFE.getRegistration() != null &&
                documentFromDB.getRegistration() != documentFromFE.getRegistration()) {
            document.setRegistration(documentFromFE.getRegistration());
            log.info("client's passport was changed" + Arrays.toString(documentFromFE.getRegistration()));
        }

        return document;
    }
}
