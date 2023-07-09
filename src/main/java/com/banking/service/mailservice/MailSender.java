package com.banking.service.mailservice;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface MailSender {
    JsonNode createMessage(String receiverEmail) throws UnirestException;

    void send(String receiverEmail);
}
