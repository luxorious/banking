package com.banking.service.mailservice;

import com.banking.exception.BadAccountDataException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * The MailSenderImpl class is an implementation of the MailSender interface that sends emails
 * using the Mailgun API.
 */
@Slf4j
@Component
public class MailSenderImpl implements MailSender{

    @Value(value = "${mailSander.apiKey}")
    private String apiKey;

    @Value(value = "${mailSander.eMail}")
    private String eMail;

    @Value(value = "${mailSander.domainName}")
    private String domainName;

    /**
     * Creates and sends an email message using the Mailgun API.
     *
     * @param receiverEmail The email address of the message recipient.
     * @param text          The content of the email message.
     * @param subject       The subject of the email message.
     * @return The JSON response from the Mailgun API.
     * @throws UnirestException If an error occurs while making the API call.
     */
    @Override
    public JsonNode createMessage(String receiverEmail, String text,String subject) throws UnirestException {
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
                .basicAuth("api", apiKey)
                .queryString("from", eMail)
                .queryString("to", receiverEmail)
                .queryString("subject", subject)
                .queryString("text", text)
                .asJson();
        log.info("mail for {} have sent!", receiverEmail);
        return request.getBody();
    }
    /**
     * Sends an email message using the Mailgun API.
     *
     * @param receiverEmail The email address of the message recipient.
     * @param text          The content of the email message.
     * @param subject       The subject of the email message.
     */
    @Override
    public void send(String receiverEmail, String text,String subject) {
        try {
            JsonNode response = createMessage(receiverEmail, text, subject);
            log.info("message sent " + response.toString());
        } catch (UnirestException e) {
            log.info("some error " + e.getMessage());
            throw new BadAccountDataException("I can't send an email, maybe you forgot to buy more shipments");
        }
    }
}