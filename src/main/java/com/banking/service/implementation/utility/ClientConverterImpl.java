package com.banking.service.implementation.utility;

import com.banking.entity.Client;
import com.banking.service.interfaces.utility.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Slf4j
@Component
public class ClientConverterImpl implements Converter<Client> {
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
