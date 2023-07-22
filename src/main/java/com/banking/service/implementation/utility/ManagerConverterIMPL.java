package com.banking.service.implementation.utility;


import com.banking.entity.Manager;
import com.banking.service.interfaces.utility.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * An implementation of the {@link Converter} interface for the {@link Manager} entity.
 * This class is responsible for converting and copying manager data between objects.
 */
@Slf4j
@Component
public class ManagerConverterIMPL implements Converter<Manager> {

    /**
     * Create a copy of the Manager entity.
     *
     * @param managerFromDB The Manager entity to copy from.
     * @return A new Manager entity with the same property values as the original.
     */
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

    /**
     * Convert and update the Manager entity with new property values from the Manager entity received from the front-end.
     *
     * @param managerFromDB The original Manager entity fetched from the database.
     * @param managerFromFE The Manager entity received from the front-end with updated property values.
     * @return The updated Manager entity.
     */
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