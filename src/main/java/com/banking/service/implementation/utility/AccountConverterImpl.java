package com.banking.service.implementation.utility;

import com.banking.entity.Account;
import com.banking.service.interfaces.utility.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Slf4j
@Component
public class AccountConverterImpl implements Converter<Account> {
    @Override
    public Account copyObjects(Account accountFromDB) {
        Account accountCopy = new Account();
        try {
            BeanUtils.copyProperties(accountCopy, accountFromDB);
        } catch (Exception e){
            log.error("Wrong type of Account");
        }
        return accountCopy;
    }

    @Override
    public Account convertFields(Account accountFromDB, Account accountFromFE) {
        Account account = copyObjects(accountFromDB);
        if (!accountFromDB.getName().equalsIgnoreCase(accountFromFE.getName()) &&
                accountFromFE.getName() != null){
            account.setName(accountFromFE.getName());
            log.info("name " + accountFromDB.getName() + " was changed to " + accountFromFE.getName());
        }

        if (accountFromDB.getType() != accountFromFE.getType() &&
                accountFromFE.getType() != null){
            account.setType(accountFromFE.getType());
            log.info("account type " + accountFromDB.getType() + " was changed to " + accountFromFE.getType());
        }

        if (accountFromDB.getStatus() != accountFromFE.getStatus() &&
                accountFromFE.getStatus() != null){
            account.setStatus(accountFromFE.getStatus());
            log.info("account status " + accountFromDB.getStatus() + " was changed to " + accountFromFE.getStatus());
        }
//чи потрібно взагалі змінювати валюту???!
//        if (accountFromDB.getCurrencyCode() != accountFromFE.getCurrencyCode() &&
//                accountFromFE.getCurrencyCode() != null){
//            account.setCurrencyCode(accountFromFE.getCurrencyCode());
//            log.info("account currency code " + accountFromDB.getStatus() + " was changed to " + accountFromFE.getStatus());
//        }

        account.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return account;
    }
}
