package com.banking.service.interfaces.utility;


public interface Converter<T> {

    T copyObjects(T t);
    T convertFields(T dataFromDB, T dataFromFE);

}
