package com.banking.service.implementation.utility;

import com.banking.entity.Client;
import com.banking.service.interfaces.utility.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
/**
 * An implementation of the {@link Converter} interface for the {@link Client} entity.
 * This class is responsible for converting and copying client data between objects.
 */
@Slf4j
@Component
public class ClientConverterImpl implements Converter<Client> {    /**
 * Copy the properties from the source client object to a new client object.
 * @param clientFromDB The source client object.
 * @return A new client object with copied properties.
 */
    @Override
    public Client copyObjects(Client clientFromDB) {
        Client clientCopy = new Client();
        try {
            BeanUtils.copyProperties(clientCopy, clientFromDB);
        } catch (Exception e) {
            log.error("Wrong type of Account");
        }
        return clientCopy;

    }
    /**
     * Convert and update the properties of the source client object with the values from the frontend client object.
     * @param clientFromDB The source client object retrieved from the database.
     * @param clientFromFE The client object received from the frontend.
     * @return The updated client object with modified properties.
     */
    @Override
    public Client convertFields(Client clientFromDB, Client clientFromFE) {
        Client client = copyObjects(clientFromDB);
        String message = " was changed to ";

        if (clientFromFE.getStatus() != null && clientFromDB.getStatus() != clientFromFE.getStatus()) {
            client.setStatus(clientFromFE.getStatus());
            log.info("client's status" +
                    message + clientFromFE.getStatus());
        }

        if (clientFromFE.getTaxCode() != null &&
                !clientFromDB.getTaxCode().equalsIgnoreCase(clientFromFE.getTaxCode())) {
            client.setTaxCode(clientFromFE.getTaxCode());
            log.info("client's tax code " +
                    message + clientFromFE.getTaxCode());
        }

        if (clientFromFE.getFirstName() != null &&
                !clientFromDB.getFirstName().equalsIgnoreCase(clientFromFE.getFirstName())) {
            client.setFirstName(clientFromFE.getFirstName());
            log.info("client's last name " +
                    message + clientFromFE.getFirstName());
        }

        if (clientFromFE.getLastName() != null &&
                !clientFromDB.getLastName().equalsIgnoreCase(clientFromFE.getLastName())) {
            client.setLastName(clientFromFE.getLastName());
            log.info("client's last name " +
                    message + clientFromFE.getLastName());
        }

        if (clientFromFE.getEmail() != null &&
                !clientFromDB.getEmail().equalsIgnoreCase(clientFromFE.getEmail())) {
            client.setEmail(clientFromFE.getEmail());
            log.info("client's E-mail " +
                    message + clientFromFE.getEmail());
        }

        if (clientFromFE.getAddress() != null &&
                !clientFromDB.getAddress().equalsIgnoreCase(clientFromFE.getAddress())) {
            client.setAddress(clientFromFE.getAddress());
            log.info("client's address " +
                    message + clientFromFE.getAddress());
        }

        if (clientFromFE.getPhone() != null &&
                !clientFromDB.getPhone().equalsIgnoreCase(clientFromFE.getPhone())) {
            client.setPhone(clientFromFE.getPhone());
            log.info("client's phone " +
                    message + clientFromFE.getPhone());
        }

        client.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return client;
    }
}
