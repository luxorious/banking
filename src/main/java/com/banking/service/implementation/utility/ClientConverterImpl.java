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
        } catch (Exception e){
            log.error("Wrong type of Account");
        }
        return clientCopy;

    }

    @Override
    public Client convertFields(Client clientFromDB, Client clientFromFE) {
        Client client = copyObjects(clientFromDB);

        if (clientFromDB.getStatus() != clientFromFE.getStatus() &&
                clientFromFE.getStatus() != null){
            client.setStatus(clientFromFE.getStatus());
            log.info("client's status" + clientFromDB.getStatus() + " was changed to " + clientFromFE.getStatus());
        }

        if (!clientFromDB.getTaxCode().equalsIgnoreCase(clientFromFE.getTaxCode()) &&
                clientFromFE.getTaxCode() != null){
            client.setTaxCode(clientFromFE.getTaxCode());
            log.info("client's tax code " + clientFromDB.getTaxCode() + " was changed to " + clientFromFE.getTaxCode());
        }

        if (!clientFromDB.getLastName().equalsIgnoreCase(clientFromFE.getLastName()) &&
                clientFromFE.getLastName() != null){
            client.setLastName(clientFromFE.getLastName());
            log.info("client's last name " + clientFromDB.getLastName() + " was changed to " + clientFromFE.getLastName());
        }

        if (!clientFromDB.getEmail().equalsIgnoreCase(clientFromFE.getEmail()) &&
                clientFromFE.getEmail() != null){
            client.setEmail(clientFromFE.getEmail());
            log.info("client's E-mail " + clientFromDB.getEmail() + " was changed to " + clientFromFE.getEmail());
        }

        if (!clientFromDB.getAddress().equalsIgnoreCase(clientFromFE.getAddress()) &&
                clientFromFE.getAddress() != null){
            client.setAddress(clientFromFE.getAddress());
            log.info("client's address " + clientFromDB.getAddress() + " was changed to " + clientFromFE.getAddress());
        }

        if (!clientFromDB.getPhone().equalsIgnoreCase(clientFromFE.getPhone()) &&
                clientFromFE.getPhone() != null){
            client.setPhone(clientFromFE.getPhone());
            log.info("client's phone " + clientFromDB.getPhone() + " was changed to " + clientFromFE.getPhone());
        }

        client.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return client;
    }
}
