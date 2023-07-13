package com.banking.service.interfaces;

import com.banking.entity.Credit;
import com.banking.entity.entityenumerations.CreditStatus;
import com.banking.entity.pojo.CreditData;

import java.util.List;
import java.util.UUID;

public interface CreditService {

    Boolean getCredit(CreditData creditData);

    Credit createCredit(CreditData creditData, UUID clientId);

    List<Credit> findAllCreditsByClientId(UUID clientId);
    List<Credit> findAllActiveByClientId(UUID clientId);

    List<Credit> findAll();

    List<Credit> findAllActive();

    Credit save(Credit credit);
}
