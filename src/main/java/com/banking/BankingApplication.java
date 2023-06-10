package com.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingApplication {

    public static void main(String[] args) {
//        Account account = new Account();
//        account.setName("name");
//        account.setBalance(111.0);
//
//        System.out.println(account);

        SpringApplication.run(BankingApplication.class, args);
    }

}
