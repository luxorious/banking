package com.banking.service.interfaces.utility;

/**
 * This interface defines the contract for converting and copying objects.
 *
 * @param <T> The type of object to be converted or copied.
 */
public interface Converter<T> {
    /**
     * Copies the given object and returns a new copy of the object.
     *
     * @param t The object to be copied.
     * @return A new copy of the given object.
     */
    T copyObjects(T t);

    /**
     * Converts and copies the fields of the data from the database and data from the front-end (FE) objects.
     * The method updates the fields of the data from the database with the values from the front-end data.
     *
     * @param dataFromDB The object containing data from the database.
     * @param dataFromFE The object containing data from the front-end (FE).
     * @return The updated object with fields from the front-end data.
     */
    T convertFields(T dataFromDB, T dataFromFE);

}
