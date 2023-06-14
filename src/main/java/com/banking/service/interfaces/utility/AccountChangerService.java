package com.banking.service.interfaces.utility;

import com.banking.entity.Account;

public interface AccountChangerService {

    Account copyObjects(Account accountFromDB);
    Account changeFields(Account accountFromDB, Account accountFromFE);

}
