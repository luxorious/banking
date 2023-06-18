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
        String message = " was changed to ";
        if (agreementFromFE.getInterestRate() != null &&
                !agreementFromDB.getInterestRate().equals(agreementFromFE.getInterestRate())){
            agreement.setInterestRate(agreementFromFE.getInterestRate());
            log.info("interest rate " +
                    message + agreementFromFE.getInterestRate());
        }

        if (agreementFromFE.getStatus() != null &&
                agreementFromDB.getStatus() != agreementFromFE.getStatus()){
            agreement.setStatus(agreementFromFE.getStatus());
            log.info("agreement status" +
                    message + agreementFromFE.getStatus());
        }

        if (agreementFromFE.getSum() != null &&
                !agreementFromDB.getSum().equals(agreementFromFE.getSum())){
            agreement.setSum(agreementFromFE.getSum());
            log.info("agreement sum " +
                    message + agreementFromFE.getStatus());
        }

        agreement.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return agreement;
    }
}
