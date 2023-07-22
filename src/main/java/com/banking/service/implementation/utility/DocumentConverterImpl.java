package com.banking.service.implementation.utility;

import com.banking.entity.Document;
import com.banking.service.interfaces.utility.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * An implementation of the {@link Converter} interface for converting and copying Document objects.
 */
@Slf4j
@Component
public class DocumentConverterImpl implements Converter<Document> {
    /**
     * Create a copy of the Document object.
     *
     * @param documentFromDB The Document object to be copied.
     * @return A new copy of the Document object.
     */
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

    /**
     * Convert and update the fields of the Document object based on the data from the front-end.
     *
     * @param documentFromDB The original Document object fetched from the database.
     * @param documentFromFE The Document object containing updated data from the front-end.
     * @return The updated Document object after field conversion.
     */
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
