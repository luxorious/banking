package com.banking.service.implementation.utility;

import com.banking.entity.Account;
import com.banking.service.interfaces.utility.AccountChangerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountChangerServiceImpl implements AccountChangerService {
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
    public Account changeFields(Account accountFromDB, Account accountFromFE) {
        Account account = copyObjects(accountFromDB);
        if (!accountFromDB.getName().equalsIgnoreCase(accountFromFE.getName()) &&
                accountFromFE.getName() != null){
            account.setName(accountFromFE.getName());
        }

        if (accountFromDB.getType() != accountFromFE.getType() &&
                accountFromFE.getType() != null){
            account.setType(accountFromFE.getType());
        }

        if (accountFromDB.getStatus() != accountFromFE.getStatus() &&
                accountFromFE.getStatus() != null){
            account.setStatus(accountFromFE.getStatus());
        }

        if (accountFromDB.getCurrencyCode() != accountFromFE.getCurrencyCode() &&
                accountFromFE.getCurrencyCode() != null){
            account.setCurrencyCode(accountFromFE.getCurrencyCode());
        }

        return account;
    }
}
