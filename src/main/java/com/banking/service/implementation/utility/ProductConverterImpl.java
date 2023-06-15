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

        if (!productFromDB.getName().equalsIgnoreCase(productFromFE.getName()) &&
                productFromFE.getName() != null){
            product.setName(productFromDB.getName());
            log.info("product name " + productFromDB.getName() + " was changed to " + productFromDB.getName());
        }

        if (productFromDB.getInterestRate() != productFromFE.getInterestRate() &&
                productFromFE.getInterestRate() != null){
            product.setInterestRate(productFromFE.getInterestRate());
            log.info("interest rate " + productFromDB.getInterestRate() +
                    " was changed to " + productFromFE.getInterestRate());
        }

        if (productFromDB.getStatus() != productFromFE.getStatus() &&
                productFromFE.getStatus() != null){
            product.setStatus(productFromFE.getStatus());
            log.info("account status " + productFromDB.getStatus() + " was changed to " + productFromFE.getStatus());
        }

        if (productFromDB.getLimit() != productFromFE.getLimit() &&
                productFromFE.getLimit() != null){
            product.setLimit(productFromFE.getLimit());
            log.info("limit " + productFromDB.getLimit() +
                    " was changed to " + productFromFE.getLimit());
        }

        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        return product;
    }
}

//    @Column(name = "updated_at", updatable = false, nullable = false, columnDefinition = "DATE")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Timestamp updatedAt;