package com.banking.schedule;

import com.mashape.unirest.http.exceptions.UnirestException;

public interface SchedulePayment {

    void monthlyPayment();

    void notification() throws UnirestException;

}
