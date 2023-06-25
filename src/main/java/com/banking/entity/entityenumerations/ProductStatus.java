package com.banking.entity.entityenumerations;

public enum ProductStatus {
    ACTIVE,                      //активний
    INACTIVE,                   //неактивний
    TEMPORARILY_UNAVAILABLE,    //тимчасово недоступний
    PENDING_APPROVAL,           //очікує на схвалення
    ON_HOLD,                    //на утриманні: цей статус вказує на те, що банк тимчасово призупинив пропозицію продукту
    PILOT                       //пілотний: цей статус вказує на те, що банк тестує продукт на обмеженому ринку
}
