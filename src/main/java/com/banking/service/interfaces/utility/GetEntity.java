package com.banking.service.interfaces.utility;

import java.util.Optional;

public interface GetEntity<R> {

    R getEntity(Optional<R> r);
}
