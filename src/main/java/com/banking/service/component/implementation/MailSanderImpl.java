package com.banking.service.component.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MailSanderImpl {

    @Value(value = "mailSander.login")
    private String login;

    @Value(value = "mailSander.password")
    private String password;
}
