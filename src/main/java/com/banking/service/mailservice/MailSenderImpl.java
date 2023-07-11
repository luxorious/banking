package com.banking.service.mailservice;

import com.banking.exception.BadAccountData;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MailSenderImpl implements MailSender{

    @Value(value = "${mailSander.apiKey}")
    private String apiKey;

    @Value(value = "${mailSander.eMail}")
    private String eMail;

    @Value(value = "${mailSander.domainName}")
    private String domainName;

    @Value(value = "${mailSander.subject}")
    private String subject;

    @Value(value = "${mailSander.text}")
    private String text;

    @Override
    public JsonNode createMessage(String receiverEmail) throws UnirestException {

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

    @Override
    public void send(String receiverEmail) {
        try {
            JsonNode response = createMessage(receiverEmail);
            log.info("message sent " + response.toString());
        } catch (UnirestException e) {
            log.info("some error " + e.getMessage());
            // какое здесь лучше визвать исключение?
            throw new BadAccountData("I can't send an email, maybe you forgot to buy more shipments");
        }
    }
}



