package com.banking.service.implementation.utility;


import com.banking.entity.Manager;
import com.banking.service.interfaces.utility.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ManagerConverterIMPL implements Converter<Manager> {


    @Override
    public Manager copyObjects(Manager managerFromDB) {
        Manager managerCopy = new Manager();
        try {
            BeanUtils.copyProperties(managerCopy, managerFromDB);
        } catch (Exception e) {
            log.error("Wrong type of agreement");
        }
        return managerCopy;
    }

    @Override
    public Manager convertFields(Manager managerFromDB, Manager managerFromFE) {
        Manager manager = copyObjects(managerFromDB);

        if (managerFromFE.getStatus() != null &&
                managerFromDB.getStatus() != managerFromFE.getStatus()) {
            manager.setStatus(managerFromFE.getStatus());
            log.info("manager status was changed to " + managerFromFE.getStatus());
        }

        if (managerFromFE.getFirstName() != null &&
                !managerFromDB.getFirstName().equals(managerFromFE.getFirstName())) {
            manager.setFirstName(managerFromFE.getFirstName());
            log.info("manager first name was changed to " + managerFromFE.getFirstName());
        }

        if (managerFromFE.getLastName() != null &&
                !managerFromDB.getLastName().equalsIgnoreCase(managerFromFE.getLastName())) {
            manager.setLastName(managerFromFE.getLastName());
            log.info("manager last name was changed to " + managerFromFE.getLastName());
        }

        if (managerFromFE.getDescription() != null &&
                !managerFromDB.getDescription().equalsIgnoreCase(managerFromFE.getDescription())) {
            manager.setDescription(managerFromFE.getDescription());
            log.info("manager description was changed to " + managerFromFE.getDescription());
        }

        if (managerFromFE.getDeletedStatus() != null &&
                managerFromDB.getDeletedStatus() != managerFromFE.getDeletedStatus()) {
            manager.setDeletedStatus(managerFromFE.getDeletedStatus());
            log.info("manager status was changed to " + managerFromFE.getDeletedStatus());
        }

        return manager;
    }
}