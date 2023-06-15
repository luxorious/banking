package com.banking.service.implementation.utility;

import com.banking.entity.Agreement;
import com.banking.service.interfaces.utility.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Slf4j
@Component
public class AgreementConverterImpl implements Converter<Agreement> {

    @Override
    public Agreement copyObjects(Agreement agreementFromDB) {
        Agreement agreementCopy = new Agreement();
        try {
            BeanUtils.copyProperties(agreementCopy, agreementFromDB);
        } catch (Exception e){
            log.error("Wrong type of agreement");
        }
        return agreementCopy;
    }

    @Override
    public Agreement convertFields(Agreement agreementFromDB, Agreement agreementFromFE) {
        Agreement agreement = copyObjects(agreementFromDB);

        if (agreementFromDB.getInterestRate() != agreementFromFE.getInterestRate() &&
                agreementFromFE.getInterestRate() != null){
            agreement.setInterestRate(agreementFromFE.getInterestRate());
            log.info("interest rate " + agreementFromDB.getInterestRate() +
                    " was changed to " + agreementFromFE.getInterestRate());
        }

        if (agreementFromDB.getStatus() != agreementFromFE.getStatus() &&
                agreementFromFE.getStatus() != null){
            agreement.setStatus(agreementFromFE.getStatus());
            log.info("agreement status" + agreementFromDB.getStatus() + " was changed to " + agreementFromFE.getStatus());
        }

        if (agreementFromDB.getSum() != agreementFromFE.getSum() &&
                agreementFromFE.getSum() != null){
            agreement.setSum(agreementFromFE.getSum());
            log.info("agreement sum " + agreementFromDB.getStatus() + " was changed to " + agreementFromFE.getStatus());
        }

        agreement.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return agreement;
    }
}
