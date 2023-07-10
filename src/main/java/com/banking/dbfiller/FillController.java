package com.banking.dbfiller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fill")
@Slf4j
public class FillController {
    private final Filler filler;
    private final BankFiller bankFiller;

    @GetMapping("/all")
    public String fillAll(){
        log.info("get started");
        bankFiller.fillBank();
        filler.fillAll();
        return "done!";
    }
}
