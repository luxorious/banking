package com.banking.component.implementations;

import com.banking.component.interfaces.IBanGeneratorComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class IBanGeneratorComponentImpl implements IBanGeneratorComponent {
    @Override
    public String generate() {
        int controlNumber = 22;
        int bankCode = 895423;
        StringBuilder number = new StringBuilder("DE" + controlNumber + bankCode + "00000");
        for (int i = 0; i < 14; i++) {
            number.append(new Random().nextInt(10));//NOSONAR
        }
        return String.valueOf(number);
    }
}
