package com.banking.service.interfaces.utility;

import java.util.List;
import java.util.Optional;

public interface ValidatorService<T> {
    T checkEntity(Optional<T> t);
    List<T> checkList(List<T> t);



}
