package com.banking.mailservice;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface MailSender {
    JsonNode createMessage(String receiverEmail) throws UnirestException;

    JsonNode send(String receiverEmail);
}
