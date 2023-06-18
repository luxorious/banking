package com.banking.service.implementation.utility;

import com.banking.entity.Product;
import com.banking.service.interfaces.utility.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Slf4j
@Component
public class ProductConverterImpl implements Converter<Product> {
    @Override
    public Product copyObjects(Product productFromDB) {
        Product productCopy = new Product();
        try {
            BeanUtils.copyProperties(productCopy, productFromDB);
        } catch (Exception e){
            log.error("Wrong type of agreement");
        }
        return productCopy;
    }

    @Override
    public Product convertFields(Product productFromDB, Product productFromFE) {
        Product product = copyObjects(productFromDB);

        if (productFromFE.getName() != null &&
                !productFromDB.getName().equals(productFromFE.getName())){
            product.setName(productFromDB.getName());
            log.info("product name " + productFromDB.getName() + " was changed to " + productFromDB.getName());
        }

        if (productFromFE.getInterestRate() != null &&
                !productFromDB.getInterestRate().equals(productFromFE.getInterestRate())){
            product.setInterestRate(productFromFE.getInterestRate());
            log.info("interest rate was changed to " + productFromFE.getInterestRate());
        }

        if (productFromFE.getStatus() != null &&
                productFromDB.getStatus() != productFromFE.getStatus()){
            product.setStatus(productFromFE.getStatus());
            log.info("account status was changed to " + productFromFE.getStatus());
        }

        if (productFromFE.getLimit() != null &&
                productFromDB.getLimit().equals(productFromFE.getLimit())){
            product.setLimit(productFromFE.getLimit());
            log.info("limit was changed to " + productFromFE.getLimit());
        }

        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return product;
    }
}
