package com.banking.service.interfaces;

import com.banking.entity.Credit;
import com.banking.entity.pojo.CreditData;

import java.util.List;
import java.util.UUID;

public interface CreditService {

//    Boolean getCredit(CreditData creditData);

    List<Credit> findAllCreditsByClientId(UUID clientId);

    List<Credit> findAll();

    List<Credit> findAllActive();

    Credit save(Credit credit);
}
