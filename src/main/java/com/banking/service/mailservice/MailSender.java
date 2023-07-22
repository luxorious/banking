package com.banking.service.mailservice;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * MailSender interface that sends emails using the Mailgun API.
 */
public interface MailSender {
    /**
     * Creates and sends an email message using the Mailgun API.
     *
     * @param receiverEmail The email address of the message recipient.
     * @param text          The content of the email message.
     * @param subject       The subject of the email message.
     * @return The JSON response from the Mailgun API.
     * @throws UnirestException If an error occurs while making the API call.
     */
    JsonNode createMessage(String receiverEmail, String text, String subject) throws UnirestException;

    /**
     * Sends an email message using the Mailgun API.
     *
     * @param receiverEmail The email address of the message recipient.
     * @param text          The content of the email message.
     * @param subject       The subject of the email message.
     */
    void send(String receiverEmail, String text, String subject);
}
