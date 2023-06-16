package com.banking.controller;

import com.banking.service.interfaces.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/agreement")
public class AgreementController {

    private final AgreementService agreementService;
}
