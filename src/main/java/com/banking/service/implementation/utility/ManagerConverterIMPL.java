package com.banking.service.implementation.utility;


import com.banking.entity.Agreement;
import com.banking.entity.Manager;
import com.banking.service.interfaces.utility.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.sql.Timestamp;

@Slf4j
@Component
public class ManagerConverterIMPL implements Converter<Manager> {


    @Override
    public Manager copyObjects(Manager managerFromDB) {
        Manager managerCopy = new Manager();
        try {
            BeanUtils.copyProperties(managerCopy, managerFromDB);
        } catch (Exception e){
            log.error("Wrong type of agreement");
        }
        return managerCopy;
    }

    @Override
    public Manager convertFields(Manager managerFromDB, Manager managerFromFE) {
        Manager manager = copyObjects(managerFromDB);

        if (managerFromDB.getStatus() != managerFromFE.getStatus() &&
                managerFromFE.getStatus() != null){
            manager.setStatus(managerFromFE.getStatus());
            log.info("manager status" + managerFromDB.getStatus() + " was changed to " + managerFromFE.getStatus());
        }

        if (!managerFromDB.getFirstName().equalsIgnoreCase(managerFromFE.getFirstName()) &&
                managerFromFE.getFirstName() != null){
            manager.setFirstName(managerFromFE.getFirstName());
            log.info("manager first name  " + managerFromDB.getFirstName() + " was changed to " + managerFromFE.getFirstName());
        }

        if (!managerFromDB.getLastName().equalsIgnoreCase(managerFromFE.getLastName()) &&
                managerFromFE.getLastName() != null){
            manager.setLastName(managerFromFE.getLastName());
            log.info("manager last name " + managerFromDB.getLastName() + " was changed to " + managerFromFE.getLastName());
        }

        if (!managerFromDB.getDescription().equalsIgnoreCase(managerFromFE.getDescription()) &&
                managerFromFE.getDescription() != null){
            manager.setDescription(managerFromFE.getDescription());
            log.info("manager description " + managerFromDB.getDescription() + " was changed to " + managerFromFE.getDescription());
        }

        return manager;
    }
}