package com.banking.schedule;

import com.mashape.unirest.http.exceptions.UnirestException;

public interface SchedulePayment {

    /**
     * Performs the monthly credit payment for active credits.
     */
    void monthlyPayment();

    /**
     * Sends payment notifications to active credit clients.
     */
    void notification() throws UnirestException;

}
