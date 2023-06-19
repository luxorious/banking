package com.banking.service.implementation.utility;

import com.banking.entity.Account;
import com.banking.service.interfaces.utility.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Slf4j
@Component
public class AccountConverterImpl implements Converter<Account> {
    @Override
    public Account copyObjects(Account accountFromDB) {
        Account accountCopy = new Account();
        try {
            BeanUtils.copyProperties(accountFromDB, accountCopy);
        } catch (Exception e){
            log.error("Wrong type of Account");
        }

        return accountCopy;
    }


    @Override
    public Account convertFields(Account accountFromDB, Account accountFromFE) {
        String message = " was changed to ";//NOSONAR
        Account account = copyObjects(accountFromDB);
        if (accountFromFE.getName() != null &&
                !accountFromDB.getName().equalsIgnoreCase(accountFromFE.getName())){
            account.setName(accountFromFE.getName());
            log.info("account name " +
                    " was changed to " + accountFromFE.getName());
        }

        if (accountFromFE.getType() != null &&
                accountFromDB.getType() != accountFromFE.getType()){
            account.setType(accountFromFE.getType());
            log.info("account type " +
                    " was changed to " + accountFromFE.getType());//NOSONAR
        }

        if (accountFromFE.getStatus() != null &&
                accountFromDB.getStatus() != accountFromFE.getStatus()){
            account.setStatus(accountFromFE.getStatus());
            log.info("account status " +
                    message + accountFromFE.getStatus());
        }

        if (accountFromFE.getBalance() != null &&
        !accountFromDB.getBalance().equals(accountFromFE.getBalance())){
            account.setBalance(accountFromFE.getBalance());
            log.info("account balance " +
                    message + accountFromFE.getBalance());
        }


        account.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return account;
    }
}
