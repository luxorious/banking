package com.banking.entity.entityEnumerations;

public enum AgreementStatus {
    ACTIVE,                  //активний
    EXPIRED,                //термін дії минув
    TERMINATED,             //припинений
    PENDING,                //очікує на розгляд
    INACTIVE,               //неактивний
    RENEWED,                //поновлений
    AMENDED,                //змінений
    LAPSED,                 //застарілий
    ACTIVE_PENDING_RENEWAL, //активний — Очікує поновлення
    VOID                    //недійсна
}
